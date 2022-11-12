package com.example.capstonedesign;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.capstonedesign.server.domain.network.NetworkLogic;
import com.example.capstonedesign.server.domain.student.StudentParent;
import com.example.capstonedesign.server.service.StudentService;
import com.example.capstonedesign.server.service.TeacherService;

public class parent_student_info extends AppCompatActivity {
    StudentParent student1 = new StudentParent();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_student_info);
        //학생정보 출력
        long sid = getIntent().getLongExtra("sid",0);
        TextView teacher_name = findViewById(R.id.teacher_name);
        TextView student_name = findViewById(R.id.student_name);
        TextView student_phone = findViewById(R.id.student_phone);
        TextView student_tuition = findViewById(R.id.student_tuition);
        TextView student_id = findViewById(R.id.student_id);
        Button studenButton = findViewById(R.id.student_attendance);

        TeacherService.profile(new NetworkLogic<>(
                teacher -> {
                    teacher_name.setText(teacher.getName());
                },
                none -> {}
        ));

        StudentService.profile(sid, new NetworkLogic<StudentParent>(
                studentParent -> {
                    student1.setStudent(studentParent.getStudent());
                    student_name.setText(student1.getStudent().getName());
                    student_phone.setText(student1.getStudent().getPhone());
                    student_tuition.setText(student1.getStudent().getTuition().toString());
                    student_id.setText(student1.getStudent().getId().toString());
                    student1.setParent(studentParent.getParent());
                },
                none -> {}
        ));



        ImageButton back = findViewById(R.id.back2);

        back.setOnClickListener(v->{
            finishAffinity();
            startActivity(new Intent(this,Sub2.class));
        });






    }

    @Override
    public void onBackPressed(){
        finishAffinity();
        startActivity(new Intent(this,Sub2.class));
    }
}