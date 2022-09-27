package com.example.capstonedesign;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.capstonedesign.server.domain.attendance.StudentParentForAttendance;
import com.example.capstonedesign.server.domain.network.NetworkLogic;
import com.example.capstonedesign.server.service.AttendanceService;
import com.example.capstonedesign.student.ListViewAdapter;

import java.util.LinkedList;
import java.util.List;

public class Sub2 extends AppCompatActivity {
    List<StudentParentForAttendance> items = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub2);

        ListView listView = findViewById(R.id.listView1);

        ListViewAdapter adapter = new ListViewAdapter();
        listView.setAdapter(adapter);

        adapter.addItem("1");
        adapter.addItem("2");
    }
}