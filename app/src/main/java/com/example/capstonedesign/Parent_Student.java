package com.example.capstonedesign;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Parent_Student extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_student);
        Intent intent = getIntent();
        Long studentId = intent.getLongExtra("studentId",0L);
        ImageButton btn_back = findViewById(R.id.btn_back);
        ListView student_info_list = findViewById(R.id.student_info);

        List<String> student_info = new ArrayList<>();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,student_info);
        student_info_list.setAdapter(adapter);


        btn_back.setOnClickListener(view -> {
            finish();
        });






    }


}