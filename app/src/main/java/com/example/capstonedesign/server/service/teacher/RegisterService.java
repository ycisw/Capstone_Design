package com.example.capstonedesign.server.service.teacher;

import com.example.capstonedesign.server.domain.network.NetworkLogic;
import com.example.capstonedesign.server.domain.teacher.Teacher;
import com.example.capstonedesign.server.domain.teacher.TeacherAddResult;
import com.example.capstonedesign.server.repository.TeacherRepository;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterService {

    public static void register(Teacher teacher, NetworkLogic<TeacherAddResult> logic) {
        HashMap<String, String> keyValueMap = new HashMap<>();
        keyValueMap.put("id", teacher.getId());
        keyValueMap.put("password", teacher.getPassword());
        keyValueMap.put("name", teacher.getName());

        TeacherRepository.api.register(keyValueMap).enqueue(new Callback<TeacherAddResult>() {
            @Override
            public void onResponse(Call<TeacherAddResult> call, Response<TeacherAddResult> response) {
                if (response.isSuccessful() && response.body().isSuccess()) {
                    logic.getSuccessLogic().successLogic(response.body());
                    return;
                }

                logic.getFailedLogic().failedLogic();
            }

            @Override
            public void onFailure(Call<TeacherAddResult> call, Throwable t) {
                logic.getFailedLogic().failedLogic();
            }
        });
    }
}
