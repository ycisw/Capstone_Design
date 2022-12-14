package com.example.capstonedesign;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;

import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
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

public class InquireStu extends AppCompatActivity {
    Dialog sub2dialog;
    ListViewAdapter adapter;
    List<StudentParent> items = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inquire_stu);

        sub2dialog = new Dialog(InquireStu.this);
        sub2dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        sub2dialog.setContentView(R.layout.reg_stu_dialog);

        Button student_add = findViewById(R.id.student_add);
        ImageButton back = findViewById(R.id.back1);
        ListView listView = findViewById(R.id.listView1);

        adapter = new ListViewAdapter();
        listView.setAdapter(adapter);
        adapter.setActivity(this);



        back.setOnClickListener(v->{
            finishAffinity();
            startActivity(new Intent(this, Main.class));
        });

        student_add.setOnClickListener(v->{
            showSub2Dialog();
        });
        updateStudent();
    }

    @Override
    public void onBackPressed(){
        finishAffinity();
        startActivity(new Intent(this, Main.class));
    }
    private void updateStudent() {
        StudentService.student(new NetworkLogic<List<StudentParent>>(
                result -> {
                    adapter.getListViewItemList().clear();
                    for(StudentParent studentParent : result){
                        adapter.addItem(studentParent.getStudent().getId(),studentParent.getStudent().getName(),studentParent.getParent().getName());
                    }
                    adapter.notifyDataSetChanged();
                },
                result -> {

                }
        ));
    }

    //????????? ???????????????
   public void showSub2Dialog(){
        sub2dialog.show();
        ImageButton back = sub2dialog.findViewById(R.id.back3);
       TextInputEditText addSname = sub2dialog.findViewById(R.id.add_sname);
       TextInputEditText addSphone = sub2dialog.findViewById(R.id.add_sphone);
       TextInputEditText addPname = sub2dialog.findViewById(R.id.add_pname);
       TextInputEditText addPphone = sub2dialog.findViewById(R.id.add_pphone);
        Button createStudent = sub2dialog.findViewById(R.id.create_student);
        back.setOnClickListener(v->{
            finishAffinity();
            startActivity(new Intent(this, InquireStu.class));
        });
       createStudent.setOnClickListener(v->{
           if(!addSname.getText().toString().equals("")&&!addSphone.getText().toString().equals("")&&!addPname.getText().toString().equals("")&&!addPphone.getText().toString().equals("")) {
               StudentService.save(new StudentParent(new Student(addSname.getText().toString(), addSphone.getText().toString()), new Parent(addPname.getText().toString(), addPphone.getText().toString())), new NetworkLogic<>(
                       none -> {
                       },
                       none -> {
                       }
               ));
               Toast.makeText(this, addSname.getText() + "?????? ?????? ??????", Toast.LENGTH_SHORT).show();
               startActivity(new Intent(this, InquireStu.class));

               new Timer().schedule(new TimerTask() {
                   @Override
                   public void run() {
                       updateStudent();
                   }
               }, 500);
           }else{
               Toast.makeText(this,"?????? ????????? ??? ??????????????? ?????????.",Toast.LENGTH_SHORT);
           }
       });

    }

}