package com.example.capstonedesign;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.capstonedesign.server.domain.network.NetworkLogic;
import com.example.capstonedesign.server.domain.student.StudentParent;
import com.example.capstonedesign.server.service.AttendanceService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class attendancecheck extends AppCompatActivity{
    ArrayAdapter<String> adapter1;
    ArrayAdapter<String> adapter2;
    ArrayList<String> listItem;
    ArrayList<String> listItem2;
    List<StudentParent> listItem3;
    HashSet<StudentParent> checkSet;
    String studentName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendancecheck);
        Button goButton = findViewById(R.id.goToTheHome);
        Button checkButton = findViewById(R.id.checkBT);
        ImageButton back_btn = findViewById(R.id.back_btn);

        listItem = new ArrayList<String>();
        listItem2 = new ArrayList<String>();
        listItem3 = new ArrayList<>();

        checkSet = new HashSet<>();

        adapter1 = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,listItem);
        adapter2 = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_multiple_choice,listItem2);
        ListView listView = findViewById(R.id.listView1);
        listView.setAdapter(adapter1);
        ListView listView2 = findViewById(R.id.listView2);
        listView2.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView2.setAdapter(adapter2);

        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            // 콜백매개변수는 순서대로 어댑터뷰, 해당 아이템의 뷰, 클릭한 순번, 항목의 아이디

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (checkSet.contains(listItem.get(i))) {
                    checkSet.remove(listItem.get(i));
                    return;
                }
                checkSet.add(listItem3.get(i));
            }
        });
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(attendancecheck.this, personal_student.class);
                intent.putExtra("studentId", listItem3.get(i).getStudent().getId());
                startActivity(intent);
            }
        });

        checkButton.setOnClickListener(v->{
            List<Long> studentIdList = new LinkedList<>();
            for (StudentParent checked : checkSet) {
                studentIdList.add(checked.getStudent().getId());
                studentName += checked.getStudent().getName() + ",";
            }
            Toast.makeText(this,studentName + "학생들이 등원했습니다.",Toast.LENGTH_SHORT).show();
            studentName = null;
            AttendanceService.attendanceToday(studentIdList, new NetworkLogic<>(
                    none -> {},
                    none -> {}
            ));
        });
        // 하원 버튼 눌렀을때
        goButton.setOnClickListener(v->{
            List<Long> studentIdList = new LinkedList<>();
            for (StudentParent checked : checkSet) {
                studentIdList.add(checked.getStudent().getId());
                studentName += checked.getStudent().getName() + ",";
            }
            Toast.makeText(this,studentName + "학생들이 하원했습니다.",Toast.LENGTH_SHORT).show();
            studentName = null;

            AttendanceService.leaveAcademyToday(studentIdList, new NetworkLogic<>(
                    none -> {},
                    none -> {}
            ));
        });
        // 등원버튼 눌렀을때
        AttendanceService.studentParentForAttendances(new NetworkLogic<List<StudentParent>>(
                result -> {
                    //성공
                    for (StudentParent studentParent : result) {
                        add(studentParent);
                    }
                    refresh();
                },
                result -> {}
        ));
    }

    private void refresh() {
        adapter1.notifyDataSetChanged();
        adapter2.notifyDataSetChanged();
    }

    private void add(StudentParent studentParent) {
        listItem3.add(studentParent); //실제 데이터
        listItem.add(studentParent.getStudent().getName()); //학생 이름 출력 하는 부분
        listItem2.add(""); //체크박스 옆에 텍스트 없애기
    }

}