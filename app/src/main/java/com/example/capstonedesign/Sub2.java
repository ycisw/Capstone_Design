package com.example.capstonedesign;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.capstonedesign.server.domain.attendance.StudentParentForAttendance;
import com.example.capstonedesign.server.domain.network.NetworkLogic;
import com.example.capstonedesign.server.service.AttendanceService;
import com.example.capstonedesign.student.ListViewAdapter;

import java.util.LinkedList;
import java.util.List;

public class Sub2 extends AppCompatActivity {
    List<StudentParentForAttendance> items = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub2);

        ListView listView = findViewById(R.id.listView1);

        ListViewAdapter adapter = new ListViewAdapter();
        listView.setAdapter(adapter);

//        adapter.addItem("1");
//        adapter.addItem("2");

        //서버에서 받아온 리스트를 어답터에 추가
        addItems(adapter);
    }

    /**
     * 서버에서 받아온 리스트를 통해 학생과 학부모 데이터를 알 수 있습니다.
     * 이를 통해 어답터에 추가할 수 있어요. 예시로 조금 바꿔놓았어요.
     * @param adapter 요게 사용하시던 어답터예요. 조금 바꿔놓았어요.
     */
    private void addItems(ListViewAdapter adapter) {
        AttendanceService.studentParentForAttendances(new NetworkLogic<List<StudentParentForAttendance>>(

                //성공시
                list -> {
                    adapter.setListViewItemList(list); //리스트 받아서 일일이 넣어주는 거보다, 리스트를 넣어주는게
                    //더 성능이 좋을 것 같아서 넣었어요.
                    adapter.notifyDataSetChanged(); //리스트뷰 데이터 추가된거 알려주는거에요.
                },
                //실패시
                list -> {
                    Toast.makeText(this, "통신실패", Toast.LENGTH_SHORT).show();
                }
        ));
    }
}