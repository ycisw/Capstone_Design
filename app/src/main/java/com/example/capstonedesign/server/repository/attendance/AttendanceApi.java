package com.example.capstonedesign.server.repository.attendance;

import com.example.capstonedesign.server.domain.attendance.Attendance;
import com.example.capstonedesign.server.domain.attendance.AttendanceStudentResult;
import com.example.capstonedesign.server.domain.student.StudentParent;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface AttendanceApi {

    @Headers({"Accept: application/json;"})
    @GET("/attendance")
    Call<List<StudentParent>> studentParentListForAttendance();

    @Headers({"Accept: application/json;"})
    @POST("/attendance")
    Call<Void> attendanceToday(@Body List<Long> studentIdList);

    @Headers({"Accept: application/json;"})
    @POST("/attendance/leave")
    Call<Void> leaveAcademyToday(@Body List<Long> studentIdList);

    @Headers({"Accept: application/json;"})
    @GET("/attendance/student")
    Call<AttendanceStudentResult> studentForm(@Query("studentId") Long studentId);

    @Headers({"Accept: application/json;"})
    @POST("/attendance/student")
    Call<Void> updateAttendance(@Body Attendance attendance);

    @Headers({"Accept: application/json;"})
    @POST("/attendance/student/delete")
    Call<Void> deleteAttendance(@Body Long attendanceId);
}
