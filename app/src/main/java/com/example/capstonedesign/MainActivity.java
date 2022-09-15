package com.example.capstonedesign;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.capstonedesign.server.domain.login.LoginForm;
import com.example.capstonedesign.server.domain.login.LoginResult;
import com.example.capstonedesign.server.repository.RetrofitAPI;
import com.example.capstonedesign.server.repository.TeacherRepository;
import com.example.capstonedesign.server.service.login.LoginService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText id = findViewById(R.id.name);
        EditText pw = findViewById(R.id.password);
        Button loginButton = findViewById(R.id.login);

//        loginButton.setOnClickListener(view -> {
//            boolean isSuccess = LoginService.login(new LoginForm(id.getText().toString(), pw.getText().toString()));
//
//            if (isSuccess) {
//                Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(MainActivity.this, homepage.class));
//            } else {
//                Toast.makeText(this, "로그인 실패", Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}
