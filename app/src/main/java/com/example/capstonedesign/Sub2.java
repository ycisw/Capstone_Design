package com.example.capstonedesign;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.capstonedesign.server.domain.network.NetworkLogic;
import com.example.capstonedesign.server.domain.parent.Parent;
import com.example.capstonedesign.server.domain.student.Student;
import com.example.capstonedesign.server.domain.student.StudentParent;
import com.example.capstonedesign.server.service.StudentService;
import com.example.capstonedesign.student.ListViewAdapter;

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
        ListView listView = findViewById(R.id.listView1);

        adapter = new ListViewAdapter();
        listView.setAdapter(adapter);
        adapter.setActivity(this);

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

        Button noBtn = sub2dialog.findViewById(R.id.noBtn);
        EditText addName = sub2dialog.findViewById(R.id.add_name);
        EditText addPname = sub2dialog.findViewById(R.id.add_pname);
        EditText addPphone = sub2dialog.findViewById(R.id.add_pphone);
        Button createStudent = sub2dialog.findViewById(R.id.create_student);

        noBtn.setOnClickListener(v->{
            sub2dialog.dismiss();
        });

        createStudent.setOnClickListener(v->{
            StudentService.save(new StudentParent(new Student(0L,addName.getText().toString(),"",0L, LocalDate.now(),0L,0L),new Parent(0L,addPname.getText().toString(),addPphone.getText().toString())),new NetworkLogic<>(
                    none -> {},
                    none -> {}
            ));
            sub2dialog.dismiss();
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    updateStudent();
                }
            }, 500);
        });
    }
}