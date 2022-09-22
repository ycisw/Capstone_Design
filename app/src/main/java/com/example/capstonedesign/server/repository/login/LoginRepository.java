package com.example.capstonedesign.server.repository.login;

import retrofit2.Retrofit;

/**
 * 로그인 API를 얻어내는 클래스입니다.
 */
public class LoginRepository {
    private LoginApi api;

    public LoginRepository(Retrofit retrofit) {
        api = retrofit.create(LoginApi.class);
    }

    public LoginApi getApi() {
        return api;
    }
}
