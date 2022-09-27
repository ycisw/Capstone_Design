package com.example.capstonedesign.server.service;

import com.example.capstonedesign.server.domain.network.NetworkLogic;
import com.example.capstonedesign.server.domain.parent.ParentLoginForm;
import com.example.capstonedesign.server.domain.parent.ParentLoginResult;
import com.example.capstonedesign.server.domain.parent.ValidationResult;
import com.example.capstonedesign.server.repository.SingletonContainer;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 학부모와 관련된 서비스입니다.
 */
public class ParentService {

    /**
     * 학부모 로그인에 사용하는 메서드입니다.
     * @param parentLoginForm 로그인에 필요한 정보입니다.
     * @param logic 로그인 성공시와 실패시의 로직을 넣어주시면 됩니다.
     */
    public static void login(ParentLoginForm parentLoginForm, NetworkLogic<ParentLoginResult> logic) {
        //ParentLoginForm을 Map으로 전환하는 과정입니다.
        HashMap<String, String> keyValueMap = new HashMap<>();
        keyValueMap.put("phone", parentLoginForm.getPhone());

        SingletonContainer.getParentApi().login(keyValueMap).enqueue(new Callback<ParentLoginResult>() {
            //네트워크 통신 성공시
            @Override
            public void onResponse(Call<ParentLoginResult> call, Response<ParentLoginResult> response) {
                if (response.isSuccessful() && response.body().isSuccess()) {
                    //로그인 성공시의 로직
                    logic.getSuccessLogic().successLogic(response.body());
                    return;
                }
                //로그인 실패시의 로직
                logic.getFailedLogic().failedLogic(response.body());
            }

            //네트워크 통신 실패시
            @Override
            public void onFailure(Call<ParentLoginResult> call, Throwable t) {
                //로그인 실패시의 로직
                logic.getFailedLogic().failedLogic(new ParentLoginResult(false));
            }
        });
    }

    /**
     * 인증번호 문자를 보내주는 서비스입니다.
     * 60초 이내에 인증해야합니다.
     * @param phone 해당 번호에 문자를 보내줘요
     * @param logic 성공 실패 관련 로직이에요.
     */
    public static void sendValidation(String phone, NetworkLogic<Void> logic) {
        SingletonContainer.getParentApi().sendValidation(phone).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    logic.getSuccessLogic().successLogic(response.body());
                    return;
                }
                logic.getFailedLogic().failedLogic(response.body());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                logic.getFailedLogic().failedLogic(null);
            }
        });
    }

    /**
     * 수신한 인증번호를 넣고 인증합니다.
     * @param validationCode 인증번호입니다.
     * @param logic 성공 실패 로직입니다.
     */
    public static void validation(String validationCode, NetworkLogic<ValidationResult> logic) {
        SingletonContainer.getParentApi().validation(validationCode).enqueue(new Callback<ValidationResult>() {
            @Override
            public void onResponse(Call<ValidationResult> call, Response<ValidationResult> response) {
                if (response.isSuccessful() && response.body().isSuccess()) {
                    logic.getSuccessLogic().successLogic(response.body());
                    return;
                }

                logic.getFailedLogic().failedLogic(response.body());
            }

            @Override
            public void onFailure(Call<ValidationResult> call, Throwable t) {
                logic.getFailedLogic().failedLogic(new ValidationResult(false));
            }
        });
    }
}