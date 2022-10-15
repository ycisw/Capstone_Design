package com.example.capstonedesign;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;

import android.widget.ListView;
import com.google.android.material.textfield.TextInputEditText;
import com.example.capstonedesign.server.domain.network.NetworkLogic;
import com.example.capstonedesign.server.domain.parent.Parent;
import com.example.capstonedesign.server.domain.student.Student;
import com.example.capstonedesign.server.domain.student.StudentParent;
import com.example.capstonedesign.server.service.StudentService;
import com.example.capstonedesign.student.ListViewAdapter;
import com.google.android.material.textfield.TextInputEditText;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Sub2 extends AppCompatActivity {
    Dialog sub2dialog;
    ListViewAdapter adapter;
    List<StudentParent> items = new LinkedList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub2);

        sub2dialog = new Dialog(Sub2.this);
        sub2dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        sub2dialog.setContentView(R.layout.activity_sub2_dialog);

        Button student_add = findViewById(R.id.student_add);
        ImageButton back = findViewById(R.id.back1);
        ListView listView = findViewById(R.id.listView1);

        adapter = new ListViewAdapter();
        listView.setAdapter(adapter);
        adapter.setActivity(this);

        back.setOnClickListener(v->{
            startActivity(new Intent(this,homepage.class));
        });

        student_add.setOnClickListener(v->{
            showSub2Dialog();
        });
        updateStudent();
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

    //커스텀 다이얼로그
   public void showSub2Dialog(){
        sub2dialog.show();
        ImageButton back = sub2dialog.findViewById(R.id.back3);
       TextInputEditText addSname = sub2dialog.findViewById(R.id.add_sname);
       TextInputEditText addSphone = sub2dialog.findViewById(R.id.add_sphone);
       TextInputEditText addTuition = sub2dialog.findViewById(R.id.add_tuition);
       TextInputEditText addPname = sub2dialog.findViewById(R.id.add_pname);
       TextInputEditText addPphone = sub2dialog.findViewById(R.id.add_pphone);
        Button createStudent = sub2dialog.findViewById(R.id.create_student);

        back.setOnClickListener(v->{
            startActivity(new Intent(this,Sub2.class));
        });

        createStudent.setOnClickListener(v->{
            StudentService.save(new StudentParent(new Student(0L,addSname.getText().toString(),addSphone.getText().toString(),Long.parseLong(addTuition.getText().toString()), LocalDate.now(),0L,0L),new Parent(0L,addPname.getText().toString(),addPphone.getText().toString())),new NetworkLogic<>(
                    none -> {},
                    none -> {}
            ));
            startActivity(new Intent(this,Sub2.class));
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    updateStudent();
                }
            }, 500);
        });
    }
}