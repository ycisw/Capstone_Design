package com.example.capstonedesign;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.capstonedesign.server.domain.attendance.Attendance;
import com.example.capstonedesign.server.domain.attendance.AttendanceStudentResult;
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

        Intent intent = getIntent();
        Long studentId = intent.getLongExtra("studentId", 0L); //만약 값이 없으면 0L 값이 들어감

        ListView check_log =  findViewById(R.id.check_log);
        ListView check_date = findViewById(R.id.check_date);
        TextView attendace_date = findViewById(R.id.attendace_date); // 출석 날짜
        TextView attendace_record = findViewById(R.id.attendace_record); // 출석 기록


        List<String> data1 = new ArrayList<>();
        List<String> data2 = new ArrayList<>();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,data1); //현재 액티비티
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,data2);
        // 데이터를 담은 어댑터 생성
        check_date.setAdapter(adapter);
        check_log.setAdapter(adapter2);
//
//        data1.add("2022년 09월 28일 20시 00분");
//        data1.add("2022년 09월 27일 20시 00분");
//        data1.add("2022년 09월 26일 20시 00분");
//        data2.add("출석");
//        data2.add("출석");
//        data2.add("출석");
//        adapter.notifyDataSetChanged(); //
//        adapter2.notifyDataSetChanged();

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

        AttendanceService.studentForm(studentId, new NetworkLogic<AttendanceStudentResult>(
                result -> {
                    List<Attendance> attendances = result.getAttendances();
                    for (Attendance attendance : attendances) {
                        LocalDateTime inTime = attendance.getInTime();
                        if (inTime == null) {
                            data1.add("등원하지 않았음");
                            data2.add("결석");
                            continue;
                        }
                        data1.add(inTime.getYear() + "년 " + inTime.getMonthValue() + "월 " + inTime.getDayOfMonth() + "일 "
                                + inTime.getHour() + "시 " + inTime.getMinute() + "분");
                        data2.add("출석");
                    }

                    refresh(adapter, adapter2);
                },
                result -> {}
        ));
    }

    private void refresh(ArrayAdapter<String> adapter, ArrayAdapter<String> adapter2) {
        adapter.notifyDataSetChanged();
        adapter2.notifyDataSetChanged();
    }
}