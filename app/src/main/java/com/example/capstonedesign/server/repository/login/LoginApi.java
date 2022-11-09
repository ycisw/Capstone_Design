package com.example.capstonedesign.server.repository.login;

import com.example.capstonedesign.server.domain.login.LoginForm;
import com.example.capstonedesign.server.domain.login.LoginResult;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * 로그인과 관련된 API 입니다.
 */
public interface LoginApi {

    /**
     * 로그인에 사용되는 API입니다.
     * @param loginForm 로그인에 필요한 폼입니다.
     * @return LoginResult를 반환할 수 있는 성공 실패 여부로 나뉘는 작업이 반환됩니다.
     */
    @Headers({"Accept: application/json;"})
    @POST("/login")
    Call<LoginResult> login(@Body LoginForm loginForm);
}
