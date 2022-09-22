package com.example.capstonedesign.server.repository.teacher;

import retrofit2.Retrofit;

/**
 * 강사 API를 얻어내는 클래스입니다.
 */
public class TeacherRepository {
    private TeacherApi api;

    public TeacherRepository(Retrofit retrofit) {
        api = retrofit.create(TeacherApi.class);
    }

    public TeacherApi getApi() {
        return api;
    }
}
