package com.example.capstonedesign.server.service;

import com.example.capstonedesign.server.domain.login.LoginForm;
import com.example.capstonedesign.server.domain.login.LoginResult;
import com.example.capstonedesign.server.domain.network.NetworkLogic;
import com.example.capstonedesign.server.repository.SingletonContainer;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 로그인을 사용할 경우 해당 서비스를 사용하면됩니다.
 */
public class LoginService {

    /**
     * 로그인 할때 사용하는 메서드입니다.
     * @param loginForm 로그인에 필요한 정보입니다.
     * @param logic 로그인 성공시와 실패시의 로직을 넣어주시면 됩니다.
     */
    public static void login(LoginForm loginForm, NetworkLogic<LoginResult> logic) {
        //LoginForm을 Map으로 전환하는 과정입니다.
        HashMap<String, String> keyValueMap = new HashMap<>();
        keyValueMap.put("loginId", loginForm.getLoginId());
        keyValueMap.put("password", loginForm.getPassword());

        SingletonContainer.getLoginApi().login(keyValueMap).enqueue(new Callback<LoginResult>() {
            //네트워크 통신 성공시의 로직
            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                if (response.isSuccessful() && response.body().isSuccess()) {
                    //로그인 성공시의 로직
                    logic.getSuccessLogic().successLogic(response.body());
                    return;
                }
                //로그인 실패시의 로직
                logic.getFailedLogic().failedLogic(response.body());
            }

            //네트워크 통신 실패시의 로직
            @Override
            public void onFailure(Call<LoginResult> call, Throwable t) {
                logic.getFailedLogic().failedLogic(new LoginResult(false));
            }
        });
    }
}
