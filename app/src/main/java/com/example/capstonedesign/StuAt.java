package com.example.capstonedesign;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.capstonedesign.server.domain.attendance.Attendance;
import com.example.capstonedesign.server.domain.attendance.AttendanceStudentResult;
import com.example.capstonedesign.server.domain.network.NetworkLogic;
import com.example.capstonedesign.server.service.AttendanceService;
import com.example.capstonedesign.student.StuAtItem;
import com.example.capstonedesign.student.StuAtItemRecycleAdapter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class StuAt extends AppCompatActivity {
    private Long studentId;
    private TextView studentName;
    private ArrayList<StuAtItem> arrayList;
    private StuAtItemRecycleAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stu_at);

        Intent intent = getIntent();
        studentId = intent.getLongExtra("studentId", 0L); //만약 값이 없으면 0L 값이 들어감
        studentName = findViewById(R.id.student_name);

        ImageButton back_btn = findViewById(R.id.back_btn);
        Button deleteButton = findViewById(R.id.delete_attendance_button);


        arrayList = new ArrayList<>(); //adapter에 들어가는 리스트

        //recycler view 설정
        RecyclerView recyclerView = findViewById(R.id.attendance_delete_recyclerview); //recyclerview
        adapter = new StuAtItemRecycleAdapter(this, arrayList); //adapter
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); //이것들은 그냥 디자인 매니저들
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter); //adapter 세팅

        // 뒤로 가기 버튼
        back_btn.setOnClickListener(v->{
            finishAffinity();
            startActivity(new Intent(this, AtCheckStu.class));
        });

        refresh();

        deleteButton.setOnClickListener(v -> {
            HashSet<StuAtItem> checkSet = adapter.getCheckSet();
            for (StuAtItem item : checkSet) {
                Long attendanceId = item.getAttendance().getId();
                AttendanceService.deleteAttendance(attendanceId, new NetworkLogic<>(none -> {}, none -> {}));
            }
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    refresh();
                }
            }, 500);
        });
    }

    private void refresh() {
        arrayList.clear();
        AttendanceService.studentForm(studentId, new NetworkLogic<AttendanceStudentResult>(
                result -> {
                    studentName.setText(result.getStudent().getName() + " 학생 출석 기록"); // 학생 이름 추출
                    List<Attendance> attendances = result.getAttendances();

                    for (Attendance attendance : attendances) {
                        LocalDate date = attendance.getDateAttendance();
                        String dateString = date.getYear() + "년 " + date.getMonthValue() + "월 " + date.getDayOfMonth() + "일 출석";
                        arrayList.add(new StuAtItem(dateString, attendance)); // 학생 개인별 출석 날짜 추출

                    }
                    adapter.notifyDataSetChanged();
                },
                result -> {}
        ));
    }

    @Override
    public void onBackPressed(){
        finishAffinity();
        startActivity(new Intent(this, AtCheckStu.class));
    }
}