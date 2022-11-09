package com.example.capstonedesign;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.capstonedesign.server.domain.attendance.AttendanceStudentResult;
import com.example.capstonedesign.server.domain.login.LoginForm;
import com.example.capstonedesign.server.domain.network.NetworkLogic;
import com.example.capstonedesign.server.service.LoginService;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout parent_layout = (LinearLayout)findViewById(R.id.parent_layout);
        TextInputEditText id = findViewById(R.id.name);
        TextInputEditText pw = findViewById(R.id.password);
        Button loginButton = findViewById(R.id.login);
        Button registerButton = findViewById(R.id.register);
        ImageButton back = findViewById(R.id.back);

        //로그인 버튼 클릭시
        loginButton.setOnClickListener(v -> {
            LoginForm loginForm = new LoginForm(id.getText().toString(), pw.getText().toString());
            login(loginForm); //로그인
        });

        //회원 가입버튼 클릭시 회원가입 화면으로 이동
        registerButton.setOnClickListener(v ->
                startActivity(new Intent(this, RegisterActivity.class)));

        //뒤로가기 버튼
        back.setOnClickListener(v->{
            finish();
        });
        // 화면 클릭시 키보드 내려가기
        parent_layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                hideKeyboard();
                return false;
            }
        });


    }
    // 키보드 숨기는 메소드
    void hideKeyboard(){
        InputMethodManager inputManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
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
                    startActivity(new Intent(MainActivity.this, homepage.class)); //다음 화면으로 이동

                },
                //실패시 로직
                loginResult -> {
                    Toast.makeText(this, "회원정보가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                }
        ));
    }
}
