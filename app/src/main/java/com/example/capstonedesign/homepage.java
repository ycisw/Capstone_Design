package com.example.capstonedesign;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.capstonedesign.server.domain.attendance.Attendance;
import com.example.capstonedesign.server.domain.attendance.AttendanceStudentResult;
import com.example.capstonedesign.server.domain.network.NetworkLogic;
import com.example.capstonedesign.server.service.AttendanceService;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class homepage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        Button studentLink = findViewById(R.id.main_page_student_management_link);
        Button attendanceLink = findViewById(R.id.main_page_attendance_link);

        //학생관리 링크
        studentLink.setOnClickListener(v ->
                startActivity(new Intent(this, Sub2.class)));

        //출결관리 링크
//        attendanceLink.setOnClickListener(v ->
//                startActivity(new Intent(this, Sub2.class)));

//        AttendanceService.studentForm(8L, new NetworkLogic<AttendanceStudentResult>(
//                result -> {
//                    Toast.makeText(this, String.valueOf(result.getAttendances().get(0).getDateAttendance().getDayOfMonth()), Toast.LENGTH_SHORT).show();
//                },
//                result -> {}
//        ));

//        AttendanceService.studentUpdate(new Attendance(4L, LocalDate.of(2022, 9, 27),
//                LocalDateTime.of(2022, 9, 27, 9, 30, 10),
//                LocalDateTime.of(2022, 9, 27, 10, 0, 10),
//                8L), new NetworkLogic<>(
//                none -> {
//
//                },
//                none -> {
//
//                }
//        ));
    }
}
