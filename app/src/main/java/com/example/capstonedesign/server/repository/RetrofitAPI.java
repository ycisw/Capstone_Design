package com.example.capstonedesign.server.repository;

import com.example.capstonedesign.server.domain.login.LoginForm;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitAPI {

    @FormUrlEncoded
    @POST("/login")
    Call<LoginForm> login();
}
