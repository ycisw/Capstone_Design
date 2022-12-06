package com.example.capstonedesign;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.capstonedesign.server.domain.network.NetworkLogic;
import com.example.capstonedesign.server.domain.parent.ParentLoginForm;
import com.example.capstonedesign.server.service.ParentService;

/**
 * 학부모 로그인 화면입니다.
 */
public class ParentLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parent_login);


        EditText phone = findViewById(R.id.parent_login_phone);
        EditText validationCode = findViewById(R.id.parent_login_validation_code);

        Button sendValidationButton = findViewById(R.id.parent_login_send_validation);
        Button validationButton = findViewById(R.id.parent_login_validation);
        Button loginButton = findViewById(R.id.parent_login_login_button);
        ImageButton back = findViewById(R.id.back);
        validationCode.setVisibility(View.GONE);
        validationButton.setVisibility(View.GONE);

        sendValidationButton.setOnClickListener(v -> {
            sendValidation(phone);
            validationButton.setVisibility(View.VISIBLE);
            validationCode.setVisibility(View.VISIBLE);
        });

        validationButton.setOnClickListener(v -> {
            validation(validationCode);
        });

        loginButton.setOnClickListener(v -> {
            ParentLoginForm loginForm = new ParentLoginForm(phone.getText().toString());
            parentLogin(loginForm);
        });

        //뒤로가기 버튼
        back.setOnClickListener(v ->{
            finishAffinity();
            startActivity(new Intent(this, Start.class));
        });
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
        startActivity(new Intent(this, Start.class));
    }

    /**
     * 인증번호를 통해 인증합니다.
     * @param validationCode 수신한 인증번호
     */
    private void validation(EditText validationCode) {
        ParentService.validation(validationCode.getText().toString(), new NetworkLogic<>(
                //성공
                result -> {
                    toastSucess("인증 성공");
                },
                //실패
                result -> {
                    toastError("인증 실패");
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
                    toastAlert("인증번호 송신");
                },
                //실패
                none -> {
                    toastError("네트워크 통신이 원활하지 않습니다.");
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
                    toastSucess("로그인 성공");
                    startActivity(new Intent(this, InquireChild.class));
                },
                //로그인 실패시
                result -> {
                    toastError("회원정보가 일치하지 않습니다.");
                }
        ));
    }

    private void toastError(String message){
        LayoutInflater inflater = getLayoutInflater();
        View ToastLayout = inflater.inflate(R.layout.toast_error, (ViewGroup) findViewById(R.id.toast_error));

        Toast toast = new Toast(getApplicationContext());
        toast.setView(ToastLayout);
        toast.setDuration(Toast.LENGTH_SHORT);
        TextView text = ToastLayout.findViewById((R.id.TextView_toast_design));
        text.setText(message);
        toast.show();
    }

    private void toastSucess(String message){
        LayoutInflater inflater = getLayoutInflater();
        View ToastLayout = inflater.inflate(R.layout.toast_sucess, (ViewGroup) findViewById(R.id.toast_sucess));

        Toast toast = new Toast(getApplicationContext());
        toast.setView(ToastLayout);
        toast.setDuration(Toast.LENGTH_SHORT);
        TextView text = ToastLayout.findViewById((R.id.TextView_toast_design));
        text.setText(message);
        toast.show();
    }

    private void toastAlert(String message){
        LayoutInflater inflater = getLayoutInflater();
        View ToastLayout = inflater.inflate(R.layout.toast_alert, (ViewGroup) findViewById(R.id.toast_alert));

        Toast toast = new Toast(getApplicationContext());
        toast.setView(ToastLayout);
        toast.setDuration(Toast.LENGTH_SHORT);
        TextView text = ToastLayout.findViewById((R.id.TextView_toast_design));
        text.setText(message);
        toast.show();
    }
}
