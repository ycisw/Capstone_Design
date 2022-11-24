package com.example.capstonedesign.server.service;

import com.example.capstonedesign.server.domain.attendance.Attendance;
import com.example.capstonedesign.server.domain.attendance.AttendanceStudentResult;
import com.example.capstonedesign.server.domain.student.StudentParent;
import com.example.capstonedesign.server.domain.network.NetworkLogic;
import com.example.capstonedesign.server.repository.SingletonContainer;

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
     */
    public static void studentParentForAttendances(NetworkLogic<List<StudentParent>> logic) {
        SingletonContainer.getAttendanceApi().studentParentListForAttendance().enqueue(new Callback<List<StudentParent>>() {
            @Override
            public void onResponse(Call<List<StudentParent>> call, Response<List<StudentParent>> response) {
                if (response.isSuccessful()) {
                    logic.getSuccessLogic().successLogic(response.body());
                    return;
                }

                logic.getFailedLogic().failedLogic(null);
            }

            @Override
            public void onFailure(Call<List<StudentParent>> call, Throwable t) {
                logic.getFailedLogic().failedLogic(null);
            }
        });
    }

    /**
     * 학생들의 아이디들을 통해 등원 시간을 현재시간으로 등록합니다.
     * @param studentIdList 현재 시간으로 등원처리할 학생 아이디 리스트
     */
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

    /**
     * 하원 처리
     * @param studentIdList 현재 시간으로 하원 처리할 학생
     */
    public static void leaveAcademyToday(List<Long> studentIdList, NetworkLogic<Void> logic) {
        SingletonContainer.getAttendanceApi().leaveAcademyToday(studentIdList).enqueue(new Callback<Void>() {
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

    /**
     * 학생 한명의 출석 기록을 조회
     * @param studentId 조회할 학생 아이디
     */
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

    /**
     * 학생의 출석기록 수정
     * @param attendance 수정할 출석정보
     * @param logic 성공 실패 로직
     */
    public static void updateAttendance(Attendance attendance, NetworkLogic<Void> logic) {
        SingletonContainer.getAttendanceApi().updateAttendance(attendance).enqueue(new Callback<Void>() {
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

    /**
     * 학생의 출석 기록 삭제
     * @param attendanceId 삭제할 출석 아이디
     */
    public static void deleteAttendance(Long attendanceId, NetworkLogic<Void> logic) {
        SingletonContainer.getAttendanceApi().deleteAttendance(attendanceId).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    logic.getSuccessLogic().successLogic(response.body());
                    return;
                }

                logic.getFailedLogic().failedLogic(response.body());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                logic.getFailedLogic().failedLogic(null);
            }
        });
    }
}
