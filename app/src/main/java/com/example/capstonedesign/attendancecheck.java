package com.example.capstonedesign;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;

public class attendancecheck extends AppCompatActivity{
    ArrayAdapter<String> adapter1;
    ArrayAdapter<String> adapter2;
    ArrayList<String> listItem;
    ArrayList<String> listItem2;
    HashSet<String> checkSet;
    String textV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendancecheck);
        Button test = findViewById(R.id.test);
        Button goButton = findViewById(R.id.goToTheHome);
        Button checkButton = findViewById(R.id.checkBT);
        TextView textView = findViewById(R.id.textView);
        TextView textView1 = findViewById(R.id.textView1);

        listItem = new ArrayList<String>();
        listItem2 = new ArrayList<String>();
        checkSet = new HashSet<>();
        add("홍길동");
        add("이순신");
        add("강감찬");
        add("조자룡");
        adapter1 = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,listItem);
        adapter2 = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_multiple_choice,listItem2);
        ListView listView1 = findViewById(R.id.listView1);
        listView1.setAdapter(adapter1);
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
                checkSet.add(listItem.get(i));
            }
        });
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),listItem.get(i).toString(),Toast.LENGTH_SHORT).show();
        }
    });
        test.setOnClickListener(v ->
                startActivity(new Intent(this, personal_student.class)));


        checkButton.setOnClickListener(v->{
            textView.setText("");
            for (String checkedItem : checkSet) {
                textView.append(checkedItem);
            }
        });

        goButton.setOnClickListener(v->{
            textView1.setText("");
            for (String checkedItem : checkSet) {
                textView1.append(checkedItem);
            }
        });
    }

    private void add(String student) {
        listItem.add(student);
        listItem2.add("");
    }

}