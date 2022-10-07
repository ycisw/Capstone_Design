package com.example.capstonedesign;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.capstonedesign.server.domain.network.NetworkLogic;
import com.example.capstonedesign.server.domain.parent.Parent;
import com.example.capstonedesign.server.domain.student.Student;
import com.example.capstonedesign.server.domain.student.StudentParent;
import com.example.capstonedesign.server.service.StudentService;
import com.example.capstonedesign.student.ListViewAdapter;
import com.example.capstonedesign.student.ListViewItem;

import java.time.LocalDate;
import java.util.List;

public class StudentProfile extends AppCompatActivity {
    Dialog dialog;
    StudentParent student1 = new StudentParent();

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
        TextView student_name = findViewById(R.id.student_name);

        StudentService.profile(sid, new NetworkLogic<StudentParent>(
                studentParent -> {
                    String name = studentParent.getStudent().getName();
                    student1.setStudent(studentParent.getStudent());
                    student_name.setText(student1.getStudent().getName());
                    student1.setParent(studentParent.getParent());
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
        Button update_student = dialog.findViewById(R.id.update_student);
        EditText update_name = dialog.findViewById(R.id.update_name);
        EditText update_pname = dialog.findViewById(R.id.update_pname);
        EditText update_pphone = dialog.findViewById(R.id.update_pphone);

        update_name.setText(student1.getStudent().getName());
        update_pname.setText(student1.getParent().getName());
        update_pphone.setText(student1.getParent().getPhone());

        update_student.setOnClickListener(v->{
            StudentService.update(new StudentParent(new Student(student1.getStudent().getId(), update_name.getText().toString(), "01090663150", 10000L, LocalDate.now(), -1L, -1L),
                new Parent(-1L, update_pname.getText().toString(), update_pphone.getText().toString())), new NetworkLogic<>(
                none -> {},
                none -> {}
        ));
            dialog.dismiss();
        });

        back.setOnClickListener(v->{
            dialog.dismiss();
        });
    }
}