package com.example.capstonedesign.server.repository.login;

import com.example.capstonedesign.server.domain.login.LoginResult;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface LoginApi {

    /**
     * 로그인에 사용되는 API입니다.
     * @param loginFormMap LoginForm을 맵으로 변환한 형태입니다.
     * @return LoginResult를 반환할 수 있는 성공 실패 여부로 나뉘는 작업이 반환됩니다.
     */
    @FormUrlEncoded
    @Headers({"Accept: application/json;"})
    @POST("/login")
    Call<LoginResult> login(@FieldMap HashMap<String, String> loginFormMap);
}
