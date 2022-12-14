package com.example.capstonedesign;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button studentLink = findViewById(R.id.main_page_student_management_link);
        Button attendanceLink = findViewById(R.id.main_page_attendance_link);
        Button searchLink = findViewById(R.id.main_page_search_parent_link);
        ImageButton back_btn = findViewById(R.id.back_btn);


        back_btn.setOnClickListener(view -> {
            finishAffinity();
            startActivity(new Intent(this, TeacherLogin.class));
        });

        //학생관리 링크
        studentLink.setOnClickListener(v ->
                startActivity(new Intent(this, InquireStu.class)));

        //출결관리 링크
        attendanceLink.setOnClickListener(v ->
                startActivity(new Intent(this, AtCheckStu.class)));

        //학부모검색 링크
        searchLink.setOnClickListener(v->
                startActivity(new Intent(this, InquireParent.class)));
    }
    @Override
    public void onBackPressed(){
        finishAffinity();
        startActivity(new Intent(this, TeacherLogin.class));
    }
}