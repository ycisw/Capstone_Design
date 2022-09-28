package com.example.capstonedesign;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Checkable;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.capstonedesign.student.ListViewAdapter;

import java.util.ArrayList;

public class attendancecheck extends AppCompatActivity{
    ArrayAdapter<String> adapter;
    ArrayList<String> listItem;
    ArrayList<String> checkArray;
    String textV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendancecheck);

        Button checkButton = findViewById(R.id.checkBT);
        TextView textView = findViewById(R.id.textView);

        listItem = new ArrayList<String>();
        listItem.add("홍길동");
        listItem.add("이순신");
        listItem.add("강감찬");
        listItem.add("조자룡");
        adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_multiple_choice,listItem);
        checkArray = new ArrayList<String>();
        ListView listView = findViewById(R.id.listView1);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            // 콜백매개변수는 순서대로 어댑터뷰, 해당 아이템의 뷰, 클릭한 순번, 항목의 아이디
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                checkArray.add(listItem.get(i).toString());
            }
        });

        checkButton.setOnClickListener(v->{
            for(int i=0;i<checkArray.size();i++){
                textView.append(checkArray.get(i).toString());
            }
        });

    }

}