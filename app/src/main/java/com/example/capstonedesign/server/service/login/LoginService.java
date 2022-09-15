package com.example.capstonedesign.server.service.login;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.capstonedesign.server.domain.login.LoginForm;
import com.example.capstonedesign.server.domain.login.LoginResult;
import com.example.capstonedesign.server.domain.network.NetworkLogic;
import com.example.capstonedesign.server.repository.TeacherRepository;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginService {

    public static void login(LoginForm loginForm, NetworkLogic<LoginResult> logic) {
        HashMap<String, String> keyValueMap = new HashMap<>();
        keyValueMap.put("loginId", loginForm.getLoginId());
        keyValueMap.put("password", loginForm.getPassword());

        TeacherRepository.api.login(keyValueMap)
                .enqueue(new Callback<LoginResult>() {
                    @Override
                    public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                        if (response.isSuccessful() && response.body().isSuccess()) {
                            logic.getSuccessLogic().successLogic(response.body());
                        }
                        logic.getFailedLogic().failedLogic();
                    }

                    @Override
                    public void onFailure(Call<LoginResult> call, Throwable t) {
                        logic.getFailedLogic().failedLogic();
                    }
                });
    }
}
