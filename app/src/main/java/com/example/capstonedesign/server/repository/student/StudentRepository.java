package com.example.capstonedesign.server.repository.student;

import retrofit2.Retrofit;

public class StudentRepository {
    private StudentApi api;

    public StudentRepository(Retrofit retrofit) {
        api = retrofit.create(StudentApi.class);
    }

    public StudentApi getApi() {
        return api;
    }
}
