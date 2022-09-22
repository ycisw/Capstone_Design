package com.example.capstonedesign.server.repository.teacher;

import retrofit2.Retrofit;

/**
 * RetrofitAPI를 생성해서 계속 공유할 수 있게 만들어 놓은 싱글톤 클래스입니다.
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
