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

import java.util.ArrayList;
import java.util.List;

public class Parent_Student extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_student);
        Intent intent = getIntent();
        ImageButton btn_back = findViewById(R.id.btn_back);
        ListView student_info_list = findViewById(R.id.student_info);

        List<String> student_info = new ArrayList<>();
        List<Student> subList = new ArrayList<>();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,student_info);
        student_info_list.setAdapter(adapter);

        student_info_list.setOnItemClickListener((adapterView, view, i, l) -> {
            Student student = subList.get(i);
            Intent nextIntent = new Intent(this, parent_student_info.class);
            nextIntent.putExtra("sid", student.getId());
            startActivity(nextIntent);
        });

        addChildren(student_info, subList, adapter);

        btn_back.setOnClickListener(view -> {
            finishAffinity();
            startActivity(new Intent(this, ParentLoginActivity.class));
        });
    }

    private void addChildren(List<String> student_info, List<Student> subList, ArrayAdapter<String> adapter) {
        ParentService.children(new NetworkLogic<List<Student>>(students -> {
            for (Student student : students) {
                add(student_info, subList, student);
            }
            adapter.notifyDataSetChanged();
        }, none -> {}));
    }

    private void add(List<String> student_info, List<Student> subList, Student student) {
        student_info.add(student.getName());
        subList.add(student);
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
        startActivity(new Intent(this, ParentLoginActivity.class));
    }
}
