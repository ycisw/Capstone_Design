package com.example.capstonedesign;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.capstonedesign.server.domain.network.NetworkLogic;
import com.example.capstonedesign.server.domain.parent.Parent;
import com.example.capstonedesign.server.domain.student.Student;
import com.example.capstonedesign.server.domain.student.StudentParent;
import com.example.capstonedesign.server.domain.teacher.Teacher;
import com.example.capstonedesign.server.service.StudentService;
import com.example.capstonedesign.server.service.TeacherService;
import com.example.capstonedesign.student.ListViewAdapter;
import com.example.capstonedesign.student.ListViewItem;
import com.google.android.material.textfield.TextInputEditText;

import org.w3c.dom.Text;

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
        TextView teacher_name = findViewById(R.id.teacher_name);
        TextView student_name = findViewById(R.id.student_name);
        TextView student_phone = findViewById(R.id.student_phone);
        TextView student_tuition = findViewById(R.id.student_tuition);
        TextView student_regDate = findViewById(R.id.student_regDate);
        TextView student_id = findViewById(R.id.student_id);

        TextView parent_name = findViewById(R.id.parent_name);
        TextView parent_phone = findViewById(R.id.parent_phone);

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
                    student_regDate.setText(student1.getStudent().getRegDate().toString());
                    student_id.setText(student1.getStudent().getId().toString());
                    student1.setParent(studentParent.getParent());
                    parent_name.setText(student1.getParent().getName());
                    parent_phone.setText(student1.getParent().getPhone());
                },
                none -> {}
        ));



        ImageButton back = findViewById(R.id.back2);
        Button student_update = findViewById(R.id.student_update);
        Button student_delete = findViewById(R.id.student_delete);

        back.setOnClickListener(v->{
            finishAffinity();
            startActivity(new Intent(this,Sub2.class));
        });

        student_update.setOnClickListener(v->{
            showProfileDialog();
        });

        student_delete.setOnClickListener(v->{
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle("정말로 삭제하시겠습니까?");
            dlg.setPositiveButton("네", (dialogInterface, i) -> {

                StudentService.withdraw(student1.getStudent().getId(), new NetworkLogic<>(
                     none -> {
                        Toast.makeText(this, "성공", Toast.LENGTH_SHORT).show();
                    },
                    none -> {}
                ));


                startActivity(new Intent(StudentProfile.this, Sub2.class));
            });

            dlg.setNegativeButton("아니요", (dialogInterface, i) -> {
                Intent intent = new Intent(this,StudentProfile.class);
                intent.putExtra("sid",student1.getStudent().getId());
                startActivity(intent);
            });

            dlg.show();
        });

    }

    @Override
    public void onBackPressed(){
        finishAffinity();
        startActivity(new Intent(this,Sub2.class));
    }


    public void showProfileDialog(){
        dialog.show();

        ImageButton back = dialog.findViewById(R.id.back4);
        Button update_student = dialog.findViewById(R.id.update_student);
        TextInputEditText update_sname = dialog.findViewById(R.id.update_sname);
        TextInputEditText update_sphone = dialog.findViewById(R.id.update_sphone);
        TextInputEditText update_tuition = dialog.findViewById(R.id.update_tuition);

        TextInputEditText update_pname = dialog.findViewById(R.id.update_pname);
        TextInputEditText update_pphone = dialog.findViewById(R.id.update_pphone);

        update_sname.setText(student1.getStudent().getName());
        update_sphone.setText(student1.getStudent().getPhone());
        update_tuition.setText(student1.getStudent().getTuition().toString());

        update_pname.setText(student1.getParent().getName());
        update_pphone.setText(student1.getParent().getPhone());

        try{
            update_student.setOnClickListener(v->{
                StudentService.update(new StudentParent(new Student(student1.getStudent().getId(), update_sname.getText().toString(), update_sphone.getText().toString(), Long.parseLong(update_tuition.getText().toString()), LocalDate.now(), -1L, -1L),
                        new Parent(-1L, update_pname.getText().toString(), update_pphone.getText().toString())), new NetworkLogic<>(
                        none -> {},
                        none -> {}
                ));
                Intent intent = new Intent(this,StudentProfile.class);
                intent.putExtra("sid",student1.getStudent().getId());
                startActivity(intent);
            });
        }catch(NullPointerException e){
            Toast.makeText(this,"다시 입력해주세요",Toast.LENGTH_SHORT);
        }


        back.setOnClickListener(v->{
            Intent intent = new Intent(this,StudentProfile.class);
            intent.putExtra("sid",student1.getStudent().getId());
            finishAffinity();
            startActivity(intent);
        });
    }
}