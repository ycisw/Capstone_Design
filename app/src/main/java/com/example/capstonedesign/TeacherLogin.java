package com.example.capstonedesign;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.capstonedesign.server.domain.login.LoginForm;
import com.example.capstonedesign.server.domain.network.NetworkLogic;
import com.example.capstonedesign.server.service.LoginService;
import com.google.android.material.textfield.TextInputEditText;


public class TeacherLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_login);
        TextInputEditText id = findViewById(R.id.name);
        TextInputEditText pw = findViewById(R.id.password);
        Button loginButton = findViewById(R.id.login);
        Button registerButton = findViewById(R.id.register);
        ImageButton back = findViewById(R.id.btn_back);

        //로그인 버튼 클릭시
        loginButton.setOnClickListener(v -> {
            LoginForm loginForm = new LoginForm(id.getText().toString(), pw.getText().toString());
            login(loginForm); //로그인
        });

        //회원 가입버튼 클릭시 회원가입 화면으로 이동
        registerButton.setOnClickListener(v ->
                startActivity(new Intent(this, RegisterTeacher.class)));

        //뒤로가기 버튼
        back.setOnClickListener(v->{
            finishAffinity();
            startActivity(new Intent(this, Start.class));
        });



    }

    /**
     * 로그인
     * @param loginForm 아이디와 비밀번호를 통해 로그인합니다.
     *
     *ㅅ
     */
    private void login(LoginForm loginForm) {
        LoginService.login(loginForm, new NetworkLogic<>(
                //성공시 로직
                loginResult -> {
                    Toast.makeText(this,"로그인이 완료되었습니다.",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(TeacherLogin.this, Main.class)); //다음 화면으로 이동

                },
                //실패시 로직
                loginResult -> {
                    Toast.makeText(this, "회원정보가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                }
        ));
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
        startActivity(new Intent(this, Start.class));
    }
}
