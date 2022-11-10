package com.example.capstonedesign;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class parent_personalStudent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_personal_student);
        ImageButton back_btn = findViewById(R.id.back_btn);

        back_btn.setOnClickListener(view -> {
           startActivity(new Intent(this, ParentLoginActivity.class));
        });


    }
}