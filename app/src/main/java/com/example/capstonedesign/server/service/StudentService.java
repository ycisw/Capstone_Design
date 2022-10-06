package com.example.capstonedesign.server.service;

import com.example.capstonedesign.server.domain.network.NetworkLogic;
import com.example.capstonedesign.server.domain.student.StudentParent;
import com.example.capstonedesign.server.repository.SingletonContainer;

import java.time.LocalDate;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentService {

    /**
     * 학부모와 학생 정보 조회
     * @param logic
     */
    public static void student(NetworkLogic<List<StudentParent>> logic) {
        SingletonContainer.getStudentApi().student().enqueue(new Callback<List<StudentParent>>() {
            @Override
            public void onResponse(Call<List<StudentParent>> call, Response<List<StudentParent>> response) {
                if (response.isSuccessful()) {
                    logic.getSuccessLogic().successLogic(response.body());
                    return;
                }

                logic.getFailedLogic().failedLogic(response.body());
            }

            @Override
            public void onFailure(Call<List<StudentParent>> call, Throwable t) {
                logic.getFailedLogic().failedLogic(null);
            }
        });
    }

    /**
     * 학생 추가 기능
     * @param studentParent 학생이랑 학부모 데이터를 입력해야 합니다.
     * @param logic 성공 실패 로직
     */
    public static void save(StudentParent studentParent, NetworkLogic<Void> logic) {
        studentParent.getStudent().setId(-1L);
        studentParent.getStudent().setParentId(-1L);
        studentParent.getStudent().setRegDate(LocalDate.now());
        studentParent.getStudent().setTeacherLesson(-1L);
        studentParent.getParent().setId(-1L);

        SingletonContainer.getStudentApi().save(studentParent).enqueue(new Callback<Void>() {
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

    /**
     * 학생과 부모데이터를 조회합니다.
     * @param studentId 조회하려는 학생 아이디
     * @param logic 성공 실패 로직
     */
    public static void profile(Long studentId, NetworkLogic<StudentParent> logic) {
        SingletonContainer.getStudentApi().profile(studentId).enqueue(new Callback<StudentParent>() {
            @Override
            public void onResponse(Call<StudentParent> call, Response<StudentParent> response) {
                if (response.isSuccessful()) {
                    logic.getSuccessLogic().successLogic(response.body());
                    return;
                }

                logic.getFailedLogic().failedLogic(response.body());
            }

            @Override
            public void onFailure(Call<StudentParent> call, Throwable t) {
                logic.getFailedLogic().failedLogic(null);
            }
        });
    }

    /**
     * 학생 회원 탈퇴
     * @param studentId 탈퇴하고자 하는 아이디
     * @param logic 성공 실패 로직
     */
    public static void withdraw(Long studentId, NetworkLogic<Void> logic) {
        SingletonContainer.getStudentApi().withdraw(studentId).enqueue(new Callback<Void>() {
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

    /**
     * 학생 부모 데이터 업데이트
     * @param studentParent 업데이트 시킬 학생 부모 데이터
     * @param logic
     */
    public static void update(StudentParent studentParent, NetworkLogic<Void> logic) {
        studentParent.getStudent().setParentId(-1L);
        studentParent.getStudent().setRegDate(LocalDate.now());
        studentParent.getStudent().setTeacherLesson(-1L);
        studentParent.getParent().setId(-1L);

        SingletonContainer.getStudentApi().update(studentParent).enqueue(new Callback<Void>() {
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
