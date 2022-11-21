package com.example.capstonedesign;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.capstonedesign.server.domain.network.NetworkLogic;
import com.example.capstonedesign.server.domain.student.StudentParent;
import com.example.capstonedesign.server.service.StudentService;
import com.example.capstonedesign.student.ListViewAdapter2;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class InquireParent extends AppCompatActivity {
    ListViewAdapter2 adapter;
    List<StudentParent> items = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inquire_parent);

        ListView listView = findViewById(R.id.listView2);
        ImageButton back = findViewById(R.id.back5);
        TextInputEditText search_parent = findViewById(R.id.search_parent);
        Button searchBtn = findViewById(R.id.search_btn);

        adapter = new ListViewAdapter2();
        listView.setAdapter(adapter);
        adapter.setActivity(this);

        back.setOnClickListener(v -> {
            finishAffinity();
            startActivity(new Intent(this, Main.class));
        });

        searchBtn.setOnClickListener(v -> {
            StudentService.student(new NetworkLogic<List<StudentParent>>(
                    result -> {
                        adapter.getListViewItemList().clear();

                        if (TextUtils.isEmpty(search_parent.getText().toString())) {   // 텍스트 공백 보내면 전체조회 실행
                            for (StudentParent studentParent : result) {
                                adapter.addItem(studentParent.getParent().getName(), studentParent.getParent().getPhone());
                            }
                        } else {
                            for (StudentParent studentParent : result) {
                                if (studentParent.getStudent().getName().contains(search_parent.getText().toString())) {
                                    adapter.addItem(studentParent.getParent().getName(), studentParent.getParent().getPhone());
                                    break;
                                }
                            }
                        }

                        adapter.notifyDataSetChanged();
                    },
                    result -> {

                    }
            ));
        });
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
        startActivity(new Intent(this, Main.class));
    }
}