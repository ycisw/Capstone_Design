package com.example.capstonedesign;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class StudentProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);

        String name = getIntent().getStringExtra("name");
        TextView sname = (TextView) findViewById(R.id.sname);
        sname.setText(name);
        Button back = (Button) findViewById(R.id.back2);

        back.setOnClickListener(v->{
            finish();
        });

    }
}