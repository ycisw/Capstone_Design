package com.example.capstonedesign;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.capstonedesign.server.domain.network.NetworkLogic;
import com.example.capstonedesign.server.domain.student.StudentParent;
import com.example.capstonedesign.server.service.StudentService;
import com.example.capstonedesign.student.ListViewAdapter;

import java.util.LinkedList;
import java.util.List;

public class SearchParent extends AppCompatActivity {
    ListViewAdapter adapter;
    List<StudentParent> items = new LinkedList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_parent);

        ListView listView = findViewById(R.id.listView2);
        ImageButton back = findViewById(R.id.back5);
        adapter = new ListViewAdapter();
        listView.setAdapter(adapter);
        adapter.setActivity(this);

        back.setOnClickListener(v-> {
            finishAffinity();
            startActivity(new Intent(this, homepage.class));
        });

    }
    @Override
    public void onBackPressed(){
        finishAffinity();
        startActivity(new Intent(this,homepage.class));
    }
    private void updateStudent() {
        StudentService.student(new NetworkLogic<List<StudentParent>>(
                result -> {
                    adapter.getListViewItemList().clear();
                    for(StudentParent studentParent : result){
                        adapter.addItem(studentParent.getStudent().getId(),studentParent.getStudent().getName(),studentParent.getParent().getName(),studentParent.getParent().getPhone());
                    }
                    adapter.notifyDataSetChanged();
                },
                result -> {

                }
        ));
    }
}