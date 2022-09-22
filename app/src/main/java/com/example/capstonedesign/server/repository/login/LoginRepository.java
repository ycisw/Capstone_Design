package com.example.capstonedesign.server.repository.login;

import retrofit2.Retrofit;

public class LoginRepository {
    private LoginApi api;

    public LoginRepository(Retrofit retrofit) {
        api = retrofit.create(LoginApi.class);
    }

    public LoginApi getApi() {
        return api;
    }
}
