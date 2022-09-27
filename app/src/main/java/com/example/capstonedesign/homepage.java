package com.example.capstonedesign;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.capstonedesign.server.domain.network.NetworkLogic;
import com.example.capstonedesign.server.service.AttendanceService;

import java.util.LinkedList;
import java.util.List;

public class homepage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        Button dial = (Button)findViewById(R.id.dial);
//        Button internet = (Button)findViewById(R.id.internet);
        Button attendanceLink = findViewById(R.id.main_page_attendance_link); //출결관리 버튼으로 교체했습니다.

        dial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Intent.ACTION_DIAL, Uri.parse("tel:01029495920"));
                startActivity(intent);
            }
        });

//        internet.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent1 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://m.google.co.kr"));
//                startActivity(intent1);
//            }
//        });

        //출결관리 링크
        attendanceLink.setOnClickListener(v ->
                startActivity(new Intent(this, Sub2.class)));


//        List<Long> studentIdList = new LinkedList<>();
//        studentIdList.add(8L); //김성규 학생
//        AttendanceService.attendanceToday(studentIdList, new NetworkLogic<>(
//                none -> {},
//                none -> {}
//        ));
    }
}
