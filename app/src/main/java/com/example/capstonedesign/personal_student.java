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

import java.time.LocalDate;
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
        ListView attendance_data = findViewById(R.id.attendacne_data);

        ImageButton back_btn = findViewById(R.id.back_btn);

        List<String> data1 = new ArrayList<>();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,data1); //현재 액티비티
        // 데이터를 담은 어댑터 생성
        attendance_data.setAdapter(adapter);




        attendance_data.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Toast.makeText(getApplicationContext(),"수정 기능 구현 중", Toast.LENGTH_SHORT).show();

            }
        });
        // 뒤로 가기 버튼
        back_btn.setOnClickListener(v->{
            finishAffinity();
            startActivity(new Intent(this, attendancecheck.class));
        });

        AttendanceService.studentForm(studentId, new NetworkLogic<AttendanceStudentResult>(
                result -> {
                    student_name.setText(result.getStudent().getName() + " 학생 출석 기록"); // 학생 이름 추출
                    List<Attendance> attendances = result.getAttendances();

                    for (Attendance attendance : attendances) {
                        LocalDate date = attendance.getDateAttendance();
                        data1.add(date.getYear() + "년 " + date.getMonthValue() + "월 " + date.getDayOfMonth() + "일" + " " + attendance.getConfirm()); // 학생 개인별 출석 날짜 추출
                    }

                    refresh(adapter);
                },
                result -> {}
        ));
    }

    @Override
    public void onBackPressed(){
        finishAffinity();
        startActivity(new Intent(this, attendancecheck.class));
    }

    private void refresh(ArrayAdapter<String> adapter) {
        adapter.notifyDataSetChanged();
    }
}