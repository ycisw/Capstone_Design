package com.example.capstonedesign;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

public class homepage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        Button studentLink = findViewById(R.id.main_page_student_management_link);
        Button attendanceLink = findViewById(R.id.main_page_attendance_link);
        ImageButton back_btn = findViewById(R.id.back_btn);


        back_btn.setOnClickListener(view -> {
            startActivity(new Intent(this, MainActivity.class));
        });

        //학생관리 링크
        studentLink.setOnClickListener(v ->
                startActivity(new Intent(this, Sub2.class)));

        //출결관리 링크
        attendanceLink.setOnClickListener(v ->
                startActivity(new Intent(this, attendancecheck.class)));
    }
    @Override
    public void onBackPressed(){
        finishAffinity();
        startActivity(new Intent(this,MainActivity.class));
    }
}
