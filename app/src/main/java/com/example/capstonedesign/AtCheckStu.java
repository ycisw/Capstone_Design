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
import android.widget.Toast;

import com.example.capstonedesign.server.domain.network.NetworkLogic;
import com.example.capstonedesign.server.domain.student.StudentParent;
import com.example.capstonedesign.server.service.AttendanceService;

import java.util.ArrayList;
import java.util.List;

public class AtCheckStu extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.at_check_stu);
        Button goButton = findViewById(R.id.goToTheHome); //하원 버튼
        Button checkButton = findViewById(R.id.checkBT); //등원 버튼
        ImageButton back_btn = findViewById(R.id.back_btn); //뒤로가기 버튼

        ArrayList<AtCheckModel> arrayList = new ArrayList<>(); //adapter에 들어가는 리스트

        //recycler view 설정
        RecyclerView recyclerView = findViewById(R.id.attendance_check_recyclerview); //recyclerview
        AtCheckAdapter adapter = new AtCheckAdapter(this, arrayList); //adapter
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); //이것들은 그냥 디자인 매니저들
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter); //adapter 세팅

        //뒤로가기 이미지버튼
        back_btn.setOnClickListener(v->{
            finishAffinity();
            startActivity(new Intent(this, Main.class));
        });

        //학생 데이터 불러오기
        AttendanceService.studentParentForAttendances(new NetworkLogic<List<StudentParent>>(
                result -> {
                    //성공
                    //학생과 부모 정보를 가져와서 어뎁터에 추가
                    for (StudentParent studentParent : result) {
                        arrayList.add(new AtCheckModel(studentParent));
                    }
                    adapter.notifyDataSetChanged();
                },
                result -> {}
        ));

        //등원 버튼 눌렀을 때
        checkButton.setOnClickListener(v -> {
            List<Long> studentIdList = new ArrayList<>();
            StringBuilder sb = new StringBuilder(); //토스트 출력때문에 사용
            for (StudentParent checked : adapter.getCheckSet()) { //체크된 요소들 어뎁터에 있음
                studentIdList.add(checked.getStudent().getId());
                sb.append(checked.getStudent().getName());
                sb.append(",");
            }
            Toast.makeText(this,sb.toString() + "학생들이 등원했습니다.",Toast.LENGTH_SHORT).show();
            AttendanceService.attendanceToday(studentIdList, new NetworkLogic<>( //등원처리
                    none -> {},
                    none -> {}
            ));
        });

        // 하원 버튼 눌렀을 때
        goButton.setOnClickListener(v->{
            List<Long> studentIdList = new ArrayList<>();
            StringBuilder sb = new StringBuilder();
            for (StudentParent checked : adapter.getCheckSet()) {
                studentIdList.add(checked.getStudent().getId());
                sb.append(checked.getStudent().getName());
                sb.append(",");
            }
            Toast.makeText(this,sb.toString() + "학생들이 하원했습니다.",Toast.LENGTH_SHORT).show();
            AttendanceService.leaveAcademyToday(studentIdList, new NetworkLogic<>( //하원처리
                    none -> {},
                    none -> {}
            ));
        });
    }

    @Override
    public void onBackPressed(){
        finishAffinity();
        startActivity(new Intent(this, Main.class));
    }
}
