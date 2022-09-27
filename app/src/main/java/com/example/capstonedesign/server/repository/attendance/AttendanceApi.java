package com.example.capstonedesign.server.repository.attendance;

import com.example.capstonedesign.server.domain.attendance.StudentParentForAttendance;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface AttendanceApi {

    @Headers({"Accept: application/json;"})
    @GET("/attendance")
    Call<List<StudentParentForAttendance>> studentParentListForAttendance();

    @FormUrlEncoded
    @Headers({"Accept: application/json;"})
    @POST("/attendance")
    Call<Void> attendanceToday(@Field("studentIdList") List<Long> studentIdList);
}
