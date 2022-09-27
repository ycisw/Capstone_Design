package com.example.capstonedesign;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.capstonedesign.server.domain.attendance.StudentParentForAttendance;
import com.example.capstonedesign.server.domain.network.NetworkLogic;
import com.example.capstonedesign.server.service.AttendanceService;
import com.example.capstonedesign.student.ListViewAdapter;

import java.util.LinkedList;
import java.util.List;

public class Sub2 extends AppCompatActivity {
    Dialog sub2dialog;
    ListViewAdapter adapter;
    List<StudentParentForAttendance> items = new LinkedList<>();


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

        student_add.setOnClickListener(v->{
            showSub2Dialog();
        });
        adapter.addItem("1");
        adapter.addItem("2");
    }

    //커스텀 다이얼로그
   public void showSub2Dialog(){
        sub2dialog.show();

        Button noBtn = sub2dialog.findViewById(R.id.noBtn);
        EditText addSid = sub2dialog.findViewById(R.id.add_sid);
        Button createStudent = sub2dialog.findViewById(R.id.create_student);

        noBtn.setOnClickListener(v->{
            sub2dialog.dismiss();
        });

        createStudent.setOnClickListener(v->{
            adapter.addItem(addSid.getText().toString());
        });
    }
}