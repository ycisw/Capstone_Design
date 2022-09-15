package com.example.capstonedesign.server.repository;

import java.net.CookieManager;
import java.net.CookiePolicy;

import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TeacherRepository {

    private static final TeacherRepository instance = new TeacherRepository();
    public static RetrofitAPI api;

    private TeacherRepository() {
        CookieManager cookieManager = new CookieManager(null, CookiePolicy.ACCEPT_ORIGINAL_SERVER);

        OkHttpClient client = new OkHttpClient().newBuilder()
                .cookieJar(new JavaNetCookieJar(cookieManager))
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://3.37.89.178")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        api = retrofit.create(RetrofitAPI.class);
    }
}
