package com.example.capstonedesign;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.capstonedesign.server.domain.attendance.Attendance;
import com.example.capstonedesign.server.domain.attendance.AttendanceStudentResult;
import com.example.capstonedesign.server.domain.network.NetworkLogic;
import com.example.capstonedesign.server.domain.parent.Parent;
import com.example.capstonedesign.server.domain.student.Student;
import com.example.capstonedesign.server.domain.student.StudentParent;
import com.example.capstonedesign.server.service.AttendanceService;
import com.example.capstonedesign.server.service.StudentService;
import com.example.capstonedesign.server.service.TeacherService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class homepage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        Button studentLink = findViewById(R.id.main_page_student_management_link);
        Button attendanceLink = findViewById(R.id.main_page_attendance_link);
        ImageButton back_btn = findViewById(R.id.back_btn);


        back_btn.setOnClickListener(view -> {
            startActivity(new Intent(this, MainActivity.class));
        });

        //학생관리 링크
        studentLink.setOnClickListener(v ->
                startActivity(new Intent(this, Sub2.class)));

        //출결관리 링크
        attendanceLink.setOnClickListener(v ->
                startActivity(new Intent(this, attendancecheck.class)));

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

//        List<Long> studentIdList = new ArrayList<>();
//        studentIdList.add(8L); //김성규
//        studentIdList.add(12L); //이상원
//        AttendanceService.attendanceToday(studentIdList, new NetworkLogic<>(
//                none -> {},
//                none -> {}
//        ));

//        List<Long> studentIdList = new ArrayList<>();
//        studentIdList.add(8L); //김성규
//        studentIdList.add(12L); //이상원
//        AttendanceService.leaveAcademyToday(studentIdList, new NetworkLogic<>(
//                none -> {},
//                none -> {}
//        ));



        //학생 관리 테스트
//        StudentService.student(new NetworkLogic<List<StudentParent>>(
//                result -> {
//                    Toast.makeText(this, String.valueOf(result.get(0).getParent().getName()), Toast.LENGTH_SHORT).show();
//                },
//                result -> {
//
//                }
//        ));

//        StudentService.save(new StudentParent(new Student(-1L, "이상원", "01029495920", 10000L,
//                LocalDate.now(), -1L, -1L),
//                new Parent(0L, "이상원", "01029495920")), new NetworkLogic<>(
//                none -> {
//                    Toast.makeText(this, "성공", Toast.LENGTH_SHORT).show();
//                },
//                none -> {
//                    Toast.makeText(this, "실패", Toast.LENGTH_SHORT).show();
//                }
//        ));

//        TeacherService.profile(new NetworkLogic<>(
//                teacher -> {
//                    Toast.makeText(this, teacher.getName(), Toast.LENGTH_SHORT).show();
//                },
//                none -> {}
//        ));

//        StudentService.profile(8L, new NetworkLogic<StudentParent>(
//                studentParent -> {
//                    String name = studentParent.getStudent().getName();
//                    Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
//                },
//                none -> {}
//        ));

//        StudentService.withdraw(13L, new NetworkLogic<>(
//                none -> {
//                    Toast.makeText(this, "성공", Toast.LENGTH_SHORT).show();
//                },
//                none -> {}
//        ));

//        StudentService.update(new StudentParent(new Student(8L, "김성규학생", "01090663150", 10000L, LocalDate.now(), -1L, -1L),
//                new Parent(-1L, "김성규부모", "01090663150")), new NetworkLogic<>(
//                none -> {},
//                none -> {}
//        ));
    }
}
