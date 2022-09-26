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

        EditText phone = findViewById(R.id.parent_login_phone);
        EditText validationCode = findViewById(R.id.parent_login_validation_code);

        Button sendValidationButton = findViewById(R.id.parent_login_send_validation);
        Button validationButton = findViewById(R.id.parent_login_validation);
        Button loginButton = findViewById(R.id.parent_login_login_button);

        sendValidationButton.setOnClickListener(v -> {
            sendValidation(phone);
        });

        validationButton.setOnClickListener(v -> {
            validation(validationCode);
        });

        loginButton.setOnClickListener(v -> {
            ParentLoginForm loginForm = new ParentLoginForm(phone.getText().toString());
            parentLogin(loginForm);
        });
    }

    /**
     * 인증번호를 통해 인증합니다.
     * @param validationCode 수신한 인증번호
     */
    private void validation(EditText validationCode) {
        ParentService.validation(validationCode.getText().toString(), new NetworkLogic<>(
                //성공
                result -> {
                    Toast.makeText(this, "인증성공", Toast.LENGTH_SHORT).show();
                },
                //실패
                result -> {
                    Toast.makeText(this, "인증 실패", Toast.LENGTH_SHORT).show();
                }
        ));
    }

    /**
     * 인증번호를 휴대폰으로 보냅니다.
     * @param phone 해당 휴대폰 번호입니다.
     */
    private void sendValidation(EditText phone) {
        ParentService.sendValidation(phone.getText().toString(), new NetworkLogic<>(
                //성공
                none -> {
                    Toast.makeText(this, "인증번호 송신", Toast.LENGTH_SHORT).show();
                },
                //실패
                none -> {
                    Toast.makeText(this, "네트워크 통신이 원활하지 않습니다.", Toast.LENGTH_SHORT).show();
                }
        ));
    }

    /**
     * 학부모 로그인, 먼저 인증번호가 필요합니다.
     * @param loginForm 휴대폰 번호 하나로 로그인
     */
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
