package com.example.capstonedesign;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.capstonedesign.server.domain.network.NetworkLogic;
import com.example.capstonedesign.server.domain.parent.ParentLoginForm;
import com.example.capstonedesign.server.service.ParentService;

/**
 * 학부모 로그인 화면입니다.
 */
public class ParentLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_login);

        EditText parentPhone = findViewById(R.id.parent_login_parent_phone);
        EditText studentPhone = findViewById(R.id.parent_login_student_phone);

        Button loginButton = findViewById(R.id.parent_login_login_button);

        loginButton.setOnClickListener(v -> {
            ParentLoginForm loginForm =
                    new ParentLoginForm(parentPhone.getText().toString(), studentPhone.getText().toString());
            parentLogin(loginForm);
        });
    }

    private void parentLogin(ParentLoginForm loginForm) {
        ParentService.login(loginForm, new NetworkLogic<>(
                //로그인 성공시
                result -> {
                    Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show();
                },
                //로그인 실패시
                result -> {
                    Toast.makeText(this, "로그인 실패", Toast.LENGTH_SHORT).show();
                }
        ));
    }
}
