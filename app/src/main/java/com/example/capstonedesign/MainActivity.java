package com.example.capstonedesign;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.capstonedesign.server.domain.login.LoginForm;
import com.example.capstonedesign.server.domain.network.NetworkLogic;
import com.example.capstonedesign.server.service.LoginService;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText id = findViewById(R.id.name);
        EditText pw = findViewById(R.id.password);
        Button loginButton = findViewById(R.id.login);
        Button registerButton = findViewById(R.id.register);

        //로그인 버튼 클릭시
        loginButton.setOnClickListener(v -> {
            LoginForm loginForm = new LoginForm(id.getText().toString(), pw.getText().toString());
            login(loginForm); //로그인
        });

        //회원 가입버튼 클릭시 회원가입 화면으로 이동
        registerButton.setOnClickListener(v ->
                startActivity(new Intent(this, RegisterActivity.class)));
    }

    /**
     * 로그인
     * @param loginForm 아이디와 비밀번호를 통해 로그인합니다.
     */
    private void login(LoginForm loginForm) {
        LoginService.login(loginForm, new NetworkLogic<>(
                //성공시 로직
                loginResult -> {
                    Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, homepage.class)); //다음 화면으로 이동
                },
                //실패시 로직
                loginResult -> {
                    Toast.makeText(this, "로그인 실패", Toast.LENGTH_SHORT).show();
                }
        ));
    }
}
