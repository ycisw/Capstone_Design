package com.example.capstonedesign;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.capstonedesign.server.domain.network.NetworkLogic;
import com.example.capstonedesign.server.domain.teacher.Teacher;
import com.example.capstonedesign.server.domain.teacher.TeacherAddResult;
import com.example.capstonedesign.server.domain.teacher.TeacherAddResultConst;
import com.example.capstonedesign.server.service.TeacherService;

/**
 * 회원 가입 화면입니다.
 * 아이디, 비밀번호, 이름을 입력받아 회원가입을 처리합니다.
 */
public class RegisterActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ImageButton btn_back = findViewById(R.id.btn_back);
        EditText id = findViewById(R.id.register_id);
        EditText password = findViewById(R.id.register_password);
        EditText confirm = findViewById(R.id.register_confirm);
        EditText name = findViewById(R.id.register_name);

        Button registerButton = findViewById(R.id.register_register_button);

        //회원 가입 버튼 클릭시
        registerButton.setOnClickListener(v -> {
            if (password.getText().toString().equals(confirm.getText().toString())) {
                Teacher teacher = new Teacher(id.getText().toString(),
                        password.getText().toString(), name.getText().toString());
                register(teacher); //회원가입
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
     * @param teacher 강사 데이터를 통해 회원 가입합니다.
     */
    private void register(Teacher teacher) {
        TeacherService.register(teacher, new NetworkLogic<TeacherAddResult>(
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
}
