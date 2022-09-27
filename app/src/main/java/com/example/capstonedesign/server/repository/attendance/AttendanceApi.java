package com.example.capstonedesign.server.repository.attendance;

import com.example.capstonedesign.server.domain.attendance.AttendanceStudentResult;
import com.example.capstonedesign.server.domain.student.StudentParent;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface AttendanceApi {

    @Headers({"Accept: application/json;"})
    @GET("/attendance")
    Call<List<StudentParent>> studentParentListForAttendance();

    @FormUrlEncoded
    @Headers({"Accept: application/json;"})
    @POST("/attendance")
    Call<Void> attendanceToday(@Field("studentIdList") List<Long> studentIdList);

    @Headers({"Accept: application/json;"})
    @GET("/attendance/student")
    Call<AttendanceStudentResult> studentForm(@Query("studentId") Long studentId);

    @FormUrlEncoded
    @Headers({"Accept: application/json;"})
    @POST("/attendance/student")
    Call<Void> studentUpdate(@FieldMap HashMap<String, Object> attendanceKeyValueMap);
}
