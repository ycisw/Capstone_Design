package com.example.capstonedesign.server.service;

import com.example.capstonedesign.server.domain.network.NetworkLogic;
import com.example.capstonedesign.server.domain.teacher.Teacher;
import com.example.capstonedesign.server.domain.teacher.TeacherAddResult;
import com.example.capstonedesign.server.repository.SingletonContainer;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 강사와 관련된 서비스입니다.
 */
public class TeacherService {

    /**
     * 회원가입 할때 사용하는 메서드입니다.
     * @param teacher 회원 가입에 필요한 강사 데이터입니다.
     * @param logic 회원 가입 성공시와 실패시의 로직을 넣어주세요.
     */
    public static void register(Teacher teacher, NetworkLogic<TeacherAddResult> logic) {
        //Teacher를 Map으로 변환하는 과정입니다.
        HashMap<String, String> keyValueMap = new HashMap<>();
        keyValueMap.put("id", teacher.getId());
        keyValueMap.put("password", teacher.getPassword());
        keyValueMap.put("name", teacher.getName());

        SingletonContainer.getTeacherApi().register(keyValueMap).enqueue(new Callback<TeacherAddResult>() {
            //네트워크 통신 성공시의 로직
            @Override
            public void onResponse(Call<TeacherAddResult> call, Response<TeacherAddResult> response) {
                if (response.isSuccessful() && response.body().isSuccess()) {
                    //회원가입 성공시의 로직
                    logic.getSuccessLogic().successLogic(response.body());
                    return;
                }

                //회원가입 실패시의 로직
                logic.getFailedLogic().failedLogic(response.body());
            }

            //네트워크 통신 실패시의 로직
            @Override
            public void onFailure(Call<TeacherAddResult> call, Throwable t) {
                logic.getFailedLogic().failedLogic(new TeacherAddResult(false,
                        "통신이 원활하지 않아 회원가입에 실패하였습니다."));
            }
        });
    }
}