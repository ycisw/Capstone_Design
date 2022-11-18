package com.example.capstonedesign;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class startPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);
        Button parents =findViewById(R.id.parents);
        Button teacher =findViewById(R.id.teacher);

        teacher.setOnClickListener(v ->
                startActivity(new Intent(this, MainActivity.class)));
        overridePendingTransition(0, 0);

        parents.setOnClickListener(v ->
            startActivity(new Intent(this, ParentLoginActivity.class)));
            overridePendingTransition(0, 0);
    }


}
