package com.example.capstonedesign;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.capstonedesign.server.domain.attendance.Attendance;
import com.example.capstonedesign.server.domain.attendance.AttendanceStudentResult;
import com.example.capstonedesign.server.domain.network.NetworkLogic;
import com.example.capstonedesign.server.service.AttendanceService;
import com.example.capstonedesign.student.StuAtItem;
import com.example.capstonedesign.student.StuAtItemAdapter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StuAt extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stu_at);
        String name = null;
        Intent intent = getIntent();
        Long studentId = intent.getLongExtra("studentId", 0L); //만약 값이 없으면 0L 값이 들어감
        TextView  student_name = findViewById(R.id.student_name);
        ListView attendance_data = findViewById(R.id.attendacne_data);

        ImageButton back_btn = findViewById(R.id.back_btn);


       // ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,data1); //현재 액티비티

        StuAtItemAdapter adapter;
        adapter = new StuAtItemAdapter();
        // 데이터를 담은 어댑터 생성
        attendance_data.setAdapter(adapter);


        // 뒤로 가기 버튼
        back_btn.setOnClickListener(v->{
            finishAffinity();
            startActivity(new Intent(this, AtCheckStu.class));
        });

        AttendanceService.studentForm(studentId, new NetworkLogic<AttendanceStudentResult>(
                result -> {
                    student_name.setText(result.getStudent().getName() + " 학생 출석 기록"); // 학생 이름 추출
                    List<Attendance> attendances = result.getAttendances();

                    for (Attendance attendance : attendances) {
                        LocalDate date = attendance.getDateAttendance();
                        adapter.addItem(date.getYear() + "년 " + date.getMonthValue() + "월 " + date.getDayOfMonth() + "일 출석"); // 학생 개인별 출석 날짜 추출

                    }

                    refresh(adapter);
                },
                result -> {}
        ));
    }

    @Override
    public void onBackPressed(){
        finishAffinity();
        startActivity(new Intent(this, AtCheckStu.class));
    }

    private void refresh(StuAtItemAdapter adapter) {
        adapter.notifyDataSetChanged();
    }
}