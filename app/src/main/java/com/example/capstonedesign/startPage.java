package com.example.capstonedesign;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

public class startPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);

        ImageButton parents =findViewById(R.id.parents);
        ImageButton teacher =findViewById(R.id.teacher);

        parents.setOnClickListener(v ->
            startActivity(new Intent(this, ParentLoginActivity.class)));

        teacher.setOnClickListener(v ->
            startActivity(new Intent(this, MainActivity.class)));
    }
}
