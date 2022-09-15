package com.example.capstonedesign.server.repository;

import com.example.capstonedesign.server.domain.login.LoginResult;
import com.example.capstonedesign.server.domain.teacher.TeacherAddResult;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface RetrofitAPI {

    @FormUrlEncoded
    @Headers({"Accept: application/json;"})
    @POST("/login")
    Call<LoginResult> login(@FieldMap HashMap<String, String> loginFormMap);

    @FormUrlEncoded
    @Headers({"Accept: application/json;"})
    @POST("/teachers/add")
    Call<TeacherAddResult> register(@FieldMap HashMap<String, String> teacherMap);
}
