package com.example.capstonedesign;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.capstonedesign.server.domain.network.NetworkLogic;
import com.example.capstonedesign.server.domain.student.Student;
import com.example.capstonedesign.server.service.ParentService;
import com.example.capstonedesign.student.InquireChildAdapter;

import java.util.ArrayList;
import java.util.List;

public class InquireChild extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inquire_child);
        Intent intent = getIntent();
        ImageButton btn_back = findViewById(R.id.btn_back);
        ListView student_info_list = findViewById(R.id.student_info);

        List<String> student_info = new ArrayList<>();
        List<Student> subList = new ArrayList<>();

       // ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,student_info);
        InquireChildAdapter adapter;
        adapter = new InquireChildAdapter();

        student_info_list.setAdapter(adapter);

        student_info_list.setOnItemClickListener((adapterView, view, i, l) -> {
            Student student = subList.get(i);
            Intent nextIntent = new Intent(this, ChildInfo.class);
            nextIntent.putExtra("sid", student.getId());
            startActivity(nextIntent);
        });

        addChildren(student_info, subList, adapter);

        btn_back.setOnClickListener(view -> {
            finishAffinity();
            startActivity(new Intent(this, ParentLogin.class));
        });
    }

    private void addChildren(List<String> student_info, List<Student> subList, InquireChildAdapter adapter) {
        ParentService.children(new NetworkLogic<List<Student>>(students -> {
            for (Student student : students) {
                adapter.addItem(student.getName());
            }
            adapter.notifyDataSetChanged();
        }, none -> {}));
    }



    @Override
    public void onBackPressed() {
        finishAffinity();
        startActivity(new Intent(this, ParentLogin.class));
    }
}
