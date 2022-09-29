package com.example.capstonedesign.server.repository.student;

import com.example.capstonedesign.server.domain.student.StudentParent;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface StudentApi {

    @Headers({"Accept: application/json;"})
    @GET("/student")
    Call<List<StudentParent>> student();

    @Headers({"Accept: application/json;"})
    @POST("/student/add")
    Call<Void> save(@Body StudentParent studentParent);

    @FormUrlEncoded
    @Headers({"Accept: application/json;"})
    @POST("/student/profile")
    Call<StudentParent> profile(@Field("studentId") Long studentId);

    @FormUrlEncoded
    @Headers({"Accept: application/json;"})
    @POST("/student/withdraw")
    Call<Void> withdraw(@Field("studentId") Long studentId);
}
