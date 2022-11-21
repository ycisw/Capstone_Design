package com.example.capstonedesign;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;


public class Start extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);
        Button parents =findViewById(R.id.parents);
        Button teacher =findViewById(R.id.teacher);

        teacher.setOnClickListener(v ->
                startActivity(new Intent(this, TeacherLogin.class)));
        overridePendingTransition(0, 0);

        parents.setOnClickListener(v ->
            startActivity(new Intent(this, ParentLogin.class)));
            overridePendingTransition(0, 0);
    }


}
