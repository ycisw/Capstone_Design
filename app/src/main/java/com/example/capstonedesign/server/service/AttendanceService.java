package com.example.capstonedesign.server.service;

import com.example.capstonedesign.server.domain.attendance.Attendance;
import com.example.capstonedesign.server.domain.attendance.AttendanceStudentResult;
import com.example.capstonedesign.server.domain.attendance.StudentParentForAttendance;
import com.example.capstonedesign.server.domain.network.NetworkLogic;
import com.example.capstonedesign.server.repository.SingletonContainer;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 출석 관리 관련 서비스입니다.
 */
public class AttendanceService {

    /**
     * 현재 강사에 해당하는 학생과 학부모의 리스트를 얻을 수 있습니다. [ {student, parent}, ...]
     * @param logic
     */
    public static void studentParentForAttendances(NetworkLogic<List<StudentParentForAttendance>> logic) {
        SingletonContainer.getAttendanceApi().studentParentListForAttendance().enqueue(new Callback<List<StudentParentForAttendance>>() {
            @Override
            public void onResponse(Call<List<StudentParentForAttendance>> call, Response<List<StudentParentForAttendance>> response) {
                if (response.isSuccessful()) {
                    logic.getSuccessLogic().successLogic(response.body());
                    return;
                }

                logic.getFailedLogic().failedLogic(null);
            }

            @Override
            public void onFailure(Call<List<StudentParentForAttendance>> call, Throwable t) {
                logic.getFailedLogic().failedLogic(null);
            }
        });
    }

    public static void attendanceToday(List<Long> studentIdList, NetworkLogic<Void> logic) {
        SingletonContainer.getAttendanceApi().attendanceToday(studentIdList).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    logic.getSuccessLogic().successLogic(null);
                    return;
                }

                logic.getFailedLogic().failedLogic(null);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                logic.getFailedLogic().failedLogic(null);
            }
        });
    }

    public static void studentForm(Long studentId, NetworkLogic<AttendanceStudentResult> logic) {
        SingletonContainer.getAttendanceApi().studentForm(studentId).enqueue(new Callback<AttendanceStudentResult>() {
            @Override
            public void onResponse(Call<AttendanceStudentResult> call, Response<AttendanceStudentResult> response) {
                if (response.isSuccessful()) {
                    logic.getSuccessLogic().successLogic(response.body());
                    return;
                }
                logic.getFailedLogic().failedLogic(null);
            }

            @Override
            public void onFailure(Call<AttendanceStudentResult> call, Throwable t) {
                logic.getFailedLogic().failedLogic(null);
            }
        });
    }

    public static void studentUpdate(Attendance attendance, NetworkLogic<Void> logic) {
        HashMap<String, Object> keyValueMap = new HashMap<>();
        keyValueMap.put("id", attendance.getId());
        keyValueMap.put("dateAttendance", attendance.getDateAttendance());
        keyValueMap.put("inTime", attendance.getInTime());
        keyValueMap.put("outTime", attendance.getOutTime());
        keyValueMap.put("studentId", attendance.getStudentId());

        SingletonContainer.getAttendanceApi().studentUpdate(keyValueMap).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    logic.getSuccessLogic().successLogic(response.body());
                    return;
                }

                logic.getFailedLogic().failedLogic(null);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                logic.getFailedLogic().failedLogic(null);
            }
        });
    }
}
