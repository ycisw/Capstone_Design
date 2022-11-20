package com.example.capstonedesign;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.capstonedesign.server.domain.network.NetworkLogic;
import com.example.capstonedesign.server.domain.student.StudentTeacher;
import com.example.capstonedesign.server.service.ParentService;

public class parent_student_info extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_student_info);
        //학생정보 출력
        long sid = getIntent().getLongExtra("sid", 0);
        TextView teacher_name = findViewById(R.id.teacher_name);
        TextView teacher_phone = findViewById(R.id.teacher_phone);
        TextView student_name = findViewById(R.id.student_name);
        TextView student_phone = findViewById(R.id.student_phone);
        TextView student_id = findViewById(R.id.student_id);
        Button studentButton = findViewById(R.id.student_attendance);
        ImageButton back = findViewById(R.id.back2);

        setStudentInfo(sid, teacher_name, teacher_phone, student_name, student_phone, student_id);

        studentButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, parent_student_attendance.class);
            intent.putExtra("studentId", sid);
            intent.putExtra("name", student_name.getText().toString());
            startActivity(intent);
        });

        back.setOnClickListener(v -> {
            finishAffinity();
            startActivity(new Intent(this, Parent_Student.class));
        });
    }

    private void setStudentInfo(long sid, TextView teacher_name, TextView teacher_phone, TextView student_name, TextView student_phone, TextView student_id) {
        ParentService.student(sid, new NetworkLogic<StudentTeacher>(
                studentTeacher -> {
                    teacher_name.setText(studentTeacher.getTeacher().getName());
                    teacher_phone.setText(studentTeacher.getTeacher().getPhone());
                    student_name.setText(studentTeacher.getStudent().getName());
                    student_phone.setText(studentTeacher.getStudent().getPhone());
                    student_id.setText(studentTeacher.getStudent().getId().toString());
                }, none -> {}
        ));
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
        startActivity(new Intent(this, Parent_Student.class));
    }
}