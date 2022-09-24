package com.example.capstonedesign.server.repository.parent;

import retrofit2.Retrofit;

/**
 * 학부모 API를 얻어내는 클래스입니다.
 */
public class ParentRepository {
    private ParentApi api;

    public ParentRepository(Retrofit retrofit) {
        api = retrofit.create(ParentApi.class);
    }

    public ParentApi getApi() {
        return api;
    }
}
