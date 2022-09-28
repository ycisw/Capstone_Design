package com.example.capstonedesign.server.repository.student;

import com.example.capstonedesign.server.domain.student.StudentParent;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
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
}
