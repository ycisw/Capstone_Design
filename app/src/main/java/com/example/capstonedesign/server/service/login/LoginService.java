package com.example.capstonedesign.server.service.login;

import android.content.Intent;
import android.widget.Toast;

import com.example.capstonedesign.MainActivity;
import com.example.capstonedesign.homepage;
import com.example.capstonedesign.server.domain.login.LoginForm;
import com.example.capstonedesign.server.domain.login.LoginResult;
import com.example.capstonedesign.server.repository.RetrofitAPI;
import com.example.capstonedesign.server.repository.TeacherRepository;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginService {


    public static boolean login(LoginForm loginForm) {
        HashMap<String, String> keyValueMap = new HashMap<>();
        keyValueMap.put("loginId", loginForm.getLoginId());
        keyValueMap.put("password", loginForm.getPassword());

        LoginResult result = new LoginResult();

        TeacherRepository.api.login(keyValueMap)
                .enqueue(new Callback<LoginResult>() {
                    @Override
                    public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                        if (response.isSuccessful() && response.body().isSuccess()) {
                            result.setSuccess(true);
                            return;
                        }

                        result.setSuccess(false);
                    }

                    @Override
                    public void onFailure(Call<LoginResult> call, Throwable t) {
                        result.setSuccess(false);
                    }
                });

        return result.isSuccess();
    }
}
