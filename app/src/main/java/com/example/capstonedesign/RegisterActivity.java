package com.example.capstonedesign;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.capstonedesign.server.domain.network.NetworkLogic;
import com.example.capstonedesign.server.domain.teacher.Teacher;
import com.example.capstonedesign.server.domain.teacher.TeacherAddForm;
import com.example.capstonedesign.server.domain.teacher.TeacherAddResult;
import com.example.capstonedesign.server.domain.teacher.TeacherAddResultConst;
import com.example.capstonedesign.server.service.ParentService;
import com.example.capstonedesign.server.service.TeacherService;

/**
 * 회원 가입 화면입니다.
 * 아이디, 비밀번호, 이름을 입력받아 회원가입을 처리합니다.
 */
public class RegisterActivity extends AppCompatActivity {
int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ImageButton btn_back = findViewById(R.id.btn_back); //뒤로가기 이미지버튼
        EditText id = findViewById(R.id.register_id); //로그인 아이디
        EditText password = findViewById(R.id.register_password); //비밀번호
        EditText confirm = findViewById(R.id.register_confirm); //비밀번호 확인
        EditText name = findViewById(R.id.register_name); //이름
        EditText phone = findViewById(R.id.register_phone); //휴대폰 번호
        Button sendValidationButton = findViewById(R.id.register_send_validation); //휴대폰 번호에 인증 발송

        EditText validationCode = findViewById(R.id.register_validation_code); //인증번호입력
        Button validationButton = findViewById(R.id.register_validation); //인증
        validationCode.setVisibility(View.GONE); //인증번호 입력란 숨겨놓기
        validationButton.setVisibility(View.GONE); //인증버튼 숨겨놓기

        //인증번호 발송 버튼
        sendValidationButton.setOnClickListener(v -> {
            sendValidation(phone); //인증번호 발송
            validationButton.setVisibility(View.VISIBLE); //숨겨진 두 항목 보이기
            validationCode.setVisibility(View.VISIBLE);
        });

        //인증번호 입력후 인증
        validationButton.setOnClickListener(v -> {
            validation(validationCode);
        });

        Button registerButton = findViewById(R.id.register_register_button);
        //회원 가입 버튼 클릭시
        registerButton.setOnClickListener(v -> {
            if (password.getText().toString().equals(confirm.getText().toString())) {
                TeacherAddForm teacherAddForm = new TeacherAddForm(id.getText().toString(),
                        password.getText().toString(), name.getText().toString(),
                        phone.getText().toString());
                register(teacherAddForm); //회원가입
            } else {
                Toast.makeText(this, "비밀번호가 일치하지 않습니다", Toast.LENGTH_SHORT).show();
                finish();
            }
        });


        //뒤로가기 버튼 클릭시
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * 회원가입
     * @param teacherAddForm 강사 데이터를 통해 회원 가입합니다.
     */
    private void register(TeacherAddForm teacherAddForm) {
        TeacherService.register(teacherAddForm, new NetworkLogic<TeacherAddResult>(
                //성공시 로직
                teacherAddResult -> {
                    Toast.makeText(this, "회원가입 성공", Toast.LENGTH_SHORT).show();
                    finish();
                },
                //실패시 로직
                teacherAddResult -> {
                    TeacherAddResult result = (TeacherAddResult) teacherAddResult;

                    String message = result.getMessage().equals(TeacherAddResultConst.DUPLICATE_ID) ? //중복된 아이디
                            "이미 등록된 아이디입니다." : //출력할 메시지
                            result.getMessage(); //예상치 못한 에러로 인한 메시지
                    Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
                    finish();
                }
        ));
    }

    /**
     * 인증번호를 휴대폰으로 보냅니다.
     * @param phone 해당 휴대폰 번호입니다.
     */
    private void sendValidation(EditText phone) {
        TeacherService.sendValidation(phone.getText().toString(), new NetworkLogic<>(
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
     * 인증번호를 통해 인증합니다.
     * @param validationCode 수신한 인증번호
     */
    private void validation(EditText validationCode) {
        TeacherService.validation(validationCode.getText().toString(), new NetworkLogic<>(
                //성공
                result -> {
                    Toast.makeText(this, "인증 성공", Toast.LENGTH_SHORT).show();
                },
                //실패
                result -> {
                    Toast.makeText(this, "인증 실패", Toast.LENGTH_SHORT).show();
                }
        ));
    }
}
