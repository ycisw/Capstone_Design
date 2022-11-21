package com.example.capstonedesign;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.capstonedesign.server.domain.attendance.Attendance;
import com.example.capstonedesign.server.domain.network.NetworkLogic;
import com.example.capstonedesign.server.service.ParentService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ChildAt extends AppCompatActivity {
    private Long studentId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.child_at);
        studentId = getIntent().getLongExtra("studentId", 0L); //만약 값이 없으면 0L 값이 들어감
        TextView student_name = findViewById(R.id.student_name);
        ListView attendance_data = findViewById(R.id.attendacne_data);

        ImageButton back_btn = findViewById(R.id.back_btn);

        student_name.setText(getIntent().getStringExtra("name"));

        List<String> dates = new ArrayList<>();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,dates); //현재 액티비티
        // 데이터를 담은 어댑터 생성
        attendance_data.setAdapter(adapter);

        ParentService.studentAttendances(studentId, new NetworkLogic<List<Attendance>>(
                attendances -> {
                    for (Attendance attendance : attendances) {
                        LocalDate dateAttendance = attendance.getDateAttendance();
                        String date = dateAttendance.getYear() + "년 " + dateAttendance.getMonthValue() + "월 " +
                                dateAttendance.getDayOfMonth() + "일";
                        dates.add(date);
                    }
                    adapter.notifyDataSetChanged();
                }, none -> {}
        ));

        // 뒤로 가기 버튼
        back_btn.setOnClickListener(v->{
            finishAffinity();
            Intent intent = new Intent(this, ChildInfo.class);
            intent.putExtra("sid", studentId);
            startActivity(intent);
        });
    }

    @Override
    public void onBackPressed(){
        finishAffinity();
        Intent intent = new Intent(this, ChildInfo.class);
        intent.putExtra("sid", studentId);
        startActivity(intent);
    }
}