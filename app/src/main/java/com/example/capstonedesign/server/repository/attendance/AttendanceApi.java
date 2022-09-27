package com.example.capstonedesign.server.repository.attendance;

import com.example.capstonedesign.server.domain.attendance.StudentParentForAttendance;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface AttendanceApi {

    @Headers({"Accept: application/json;"})
    @GET("/attendance")
    Call<List<StudentParentForAttendance>> studentParentListForAttendance();
}
