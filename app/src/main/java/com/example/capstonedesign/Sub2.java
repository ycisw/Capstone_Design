package com.example.capstonedesign;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.capstonedesign.student.ListViewAdapter;

public class Sub2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub2);

        ListView listView = findViewById(R.id.listView1);

        ListViewAdapter adapter = new ListViewAdapter();
        listView.setAdapter(adapter);

        adapter.addItem("1");
        adapter.addItem("2");
    }
}