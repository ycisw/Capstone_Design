package com.example.capstonedesign.server.service;

import com.example.capstonedesign.server.domain.network.NetworkLogic;
import com.example.capstonedesign.server.domain.student.StudentParent;
import com.example.capstonedesign.server.repository.SingletonContainer;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentService {

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

    public static void save(StudentParent studentParent, NetworkLogic<Void> logic) {
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

    public static void update(StudentParent studentParent, NetworkLogic<Void> logic) {
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
