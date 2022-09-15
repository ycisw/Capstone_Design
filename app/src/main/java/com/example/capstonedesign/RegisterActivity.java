package com.example.capstonedesign;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.capstonedesign.server.domain.network.NetworkLogic;
import com.example.capstonedesign.server.domain.teacher.Teacher;
import com.example.capstonedesign.server.service.teacher.RegisterService;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText id = findViewById(R.id.register_id);
        EditText password = findViewById(R.id.register_password);
        EditText name = findViewById(R.id.register_name);

        Button registerButton = findViewById(R.id.register_register_button);

        registerButton.setOnClickListener(v -> {
            Teacher teacher = new Teacher(id.getText().toString(), password.getText().toString(), name.getText().toString());
            RegisterService.register(teacher, new NetworkLogic<>(
                    (teacherAddResult) -> {
                        Toast.makeText(this, "회원가입 성공", Toast.LENGTH_SHORT).show();
                        finish();
                    },
                    () -> {
                        Toast.makeText(this, "회원가입 실패", Toast.LENGTH_SHORT).show();
                        finish();
                    }
            ));
        });
    }
}
