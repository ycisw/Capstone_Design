package com.example.capstonedesign.server.repository;

import com.example.capstonedesign.server.domain.network.NetworkConst;

import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.concurrent.TimeUnit;

import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * RetrofitAPI를 생성해서 계속 공유할 수 있게 만들어 놓은 싱글톤 클래스입니다.
 */
public class TeacherRepository {

    private static final TeacherRepository instance = new TeacherRepository();
    public static RetrofitAPI api;

    private TeacherRepository() {
        //쿠키 & 세션을 사용하여 강사 회원의 로그인 세션을 관리합니다.
        CookieManager cookieManager = new CookieManager(null, CookiePolicy.ACCEPT_ORIGINAL_SERVER);

        //OkHttp3를 통해 쿠키를 유지하며 http를 주고 받을 수 있습니다.
        OkHttpClient client = new OkHttpClient().newBuilder()
                .cookieJar(new JavaNetCookieJar(cookieManager))
                .connectTimeout(1, TimeUnit.MINUTES) //네트워크 연결이 느려 다시 전송할 때까지의 시간들입니다.
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build();

        //Retrofit에 OkHttp3와 Gson을 사용하여 동작시킵니다.
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(NetworkConst.BASE_URL) //해당 주소는 aws ec2 인스턴스 접속 주소입니다.
                .addConverterFactory(GsonConverterFactory.create()) //Gson은 JSON과 객체 사이를 상호 변환할 수 있게 해줍니다.
                .client(client)
                .build();

        api = retrofit.create(RetrofitAPI.class);
    }
}
