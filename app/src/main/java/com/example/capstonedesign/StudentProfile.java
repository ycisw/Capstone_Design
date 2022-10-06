package com.example.capstonedesign;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.capstonedesign.server.domain.network.NetworkLogic;
import com.example.capstonedesign.server.domain.student.StudentParent;
import com.example.capstonedesign.server.service.StudentService;
import com.example.capstonedesign.student.ListViewAdapter;
import com.example.capstonedesign.student.ListViewItem;

import java.util.List;

public class StudentProfile extends AppCompatActivity {
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);
        //다이얼로그
        dialog = new Dialog(StudentProfile.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.activity_student_profile_dialog);

        //학생정보 출력
        long sid = getIntent().getLongExtra("sid",0);
        TextView sname = findViewById(R.id.sname);
        StudentParent student1 = new StudentParent();

        StudentService.profile(sid, new NetworkLogic<StudentParent>(
                studentParent -> {
                    String name = studentParent.getStudent().getName();
                    student1.setStudent(studentParent.getStudent());
                    student1.setParent(studentParent.getParent());

                    sname.setText(student1.getStudent().getName());

                    Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
                },
                none -> {}
        ));



        Button back = (Button) findViewById(R.id.back2);
        Button student_update = (Button) findViewById(R.id.student_update);

        back.setOnClickListener(v->{
            finish();
        });

        student_update.setOnClickListener(v->{
            showProfileDialog();
        });

    }

    public void showProfileDialog(){
        dialog.show();

        Button back = dialog.findViewById(R.id.back4);

        back.setOnClickListener(v->{
            dialog.dismiss();
        });
    }
}