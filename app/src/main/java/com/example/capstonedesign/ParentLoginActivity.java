package com.example.capstonedesign;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.capstonedesign.server.domain.network.NetworkLogic;
import com.example.capstonedesign.server.domain.parent.ParentLoginForm;
import com.example.capstonedesign.server.domain.student.Student;
import com.example.capstonedesign.server.domain.student.StudentTeacher;
import com.example.capstonedesign.server.service.ParentService;

import java.util.List;

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
            startActivity(new Intent(this, startPage.class));
        });
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
        startActivity(new Intent(this, startPage.class));
    }

    /**
     * 인증번호를 통해 인증합니다.
     * @param validationCode 수신한 인증번호
     */
    private void validation(EditText validationCode) {
        ParentService.validation(validationCode.getText().toString(), new NetworkLogic<>(
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
//                    Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show();
                    //자녀 조회
                    ParentService.children(new NetworkLogic<List<Student>>(students -> {
                        StringBuilder sb = new StringBuilder();
                        for (Student student : students) {
                            sb.append(student.getName() + " ");
                        }
                        Toast.makeText(this, sb.toString(), Toast.LENGTH_SHORT).show();
                        //0번째 자녀 및 담당강사정보 조회
                        ParentService.student(students.get(0).getId(), new NetworkLogic<StudentTeacher>(
                                st -> {
                                    String studentName = st.getStudent().getName();
                                    String teacherName = st.getTeacher().getName();
                                    Toast.makeText(this, studentName + " " + teacherName, Toast.LENGTH_SHORT).show();
                                }, st -> {}
                        ));
                    }, students -> {}));
//                    startActivity(new Intent(this,personal_student.class));
                    //학부모 권한으로 접근 불가능한 페이지임 에러 발생
                },
                //로그인 실패시
                result -> {
                    Toast.makeText(this, "회원정보가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                }
        ));
    }
}
