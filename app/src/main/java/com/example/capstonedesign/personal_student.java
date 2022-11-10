package com.example.capstonedesign;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.capstonedesign.server.domain.attendance.Attendance;
import com.example.capstonedesign.server.domain.attendance.AttendanceStudentResult;
import com.example.capstonedesign.server.domain.login.LoginForm;
import com.example.capstonedesign.server.domain.network.NetworkLogic;
import com.example.capstonedesign.server.service.AttendanceService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class personal_student extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_student);
        String name = null;
        Intent intent = getIntent();
        Long studentId = intent.getLongExtra("studentId", 0L); //만약 값이 없으면 0L 값이 들어감
        TextView  student_name = findViewById(R.id.student_name);
        ListView check_log =  findViewById(R.id.check_log);
        ListView check_date = findViewById(R.id.check_date);
        TextView attendace_date = findViewById(R.id.attendace_date); // 출석 날짜
        TextView attendace_record = findViewById(R.id.attendace_record); // 출석 기록
        ImageButton back_btn = findViewById(R.id.back_btn);

        List<String> data1 = new ArrayList<>();
        List<String> data2 = new ArrayList<>();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,data1); //현재 액티비티
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,data2);
        // 데이터를 담은 어댑터 생성
        check_date.setAdapter(adapter);
        check_log.setAdapter(adapter2);


        check_log.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Toast.makeText(getApplicationContext(),"수정 기능 구현 중", Toast.LENGTH_SHORT).show();

            }
        });

        check_date.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Toast.makeText(getApplicationContext(),"수정 기능 구현 중", Toast.LENGTH_SHORT).show();

            }
        });

        back_btn.setOnClickListener(v->{
            finishAffinity();
            startActivity(new Intent(this, attendancecheck.class));
        });

        AttendanceService.studentForm(studentId, new NetworkLogic<AttendanceStudentResult>(
                result -> {
                    student_name.setText(result.getStudent().getName()); // 학생 이름 추출
                    List<Attendance> attendances = result.getAttendances();

                    for (Attendance attendance : attendances) {
                        LocalDateTime inTime = attendance.getInTime(); // 학생 개인별 출석 기록 추출
                        if (inTime == null) { // 출석 기록이 null이면 결석 처리
                            data1.add("등원하지 않았음");
                            data2.add("결석");
                            continue;
                        }
                        data1.add(inTime.getYear() + "년 " + inTime.getMonthValue() + "월 " + inTime.getDayOfMonth() + "일 "
                                + inTime.getHour() + "시 " + inTime.getMinute() + "분"); // 학생 개인별 출석 날짜 추출
                        data2.add("출석"); // 출석처리
                    }

                    refresh(adapter, adapter2);
                },
                result -> {}
        ));
    }

    @Override
    public void onBackPressed(){
        finishAffinity();
        startActivity(new Intent(this, attendancecheck.class));
    }

    private void refresh(ArrayAdapter<String> adapter, ArrayAdapter<String> adapter2) {
        adapter.notifyDataSetChanged();
        adapter2.notifyDataSetChanged();
    }
}