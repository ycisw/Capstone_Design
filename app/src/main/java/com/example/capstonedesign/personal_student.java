package com.example.capstonedesign;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class personal_student extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_student);

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

        data1.add("2022년 09월 28일 20시 00분");
        data1.add("2022년 09월 27일 20시 00분");
        data1.add("2022년 09월 26일 20시 00분");
        data2.add("출석");
        data2.add("출석");
        data2.add("출석");
        adapter.notifyDataSetChanged(); //
        adapter2.notifyDataSetChanged();

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
    }
}