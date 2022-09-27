package com.example.capstonedesign.server.repository.attendance;

import retrofit2.Retrofit;

public class AttendanceRepository {
    private AttendanceApi api;

    public AttendanceRepository(Retrofit retrofit) {
        api = retrofit.create(AttendanceApi.class);
    }

    public AttendanceApi getApi() {
        return api;
    }
}
