package com.example.capstonedesign.server.repository;

import com.example.capstonedesign.server.domain.network.NetworkConst;
import com.example.capstonedesign.server.repository.login.LoginApi;
import com.example.capstonedesign.server.repository.login.LoginRepository;
import com.example.capstonedesign.server.repository.parent.ParentApi;
import com.example.capstonedesign.server.repository.parent.ParentRepository;
import com.example.capstonedesign.server.repository.teacher.TeacherApi;
import com.example.capstonedesign.server.repository.teacher.TeacherRepository;

import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.concurrent.TimeUnit;

import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 여러 서비스들이 사용하는 API들을 한 곳에서 관리할 수 있도록
 * 싱글톤 컨테이너를 만들었습니다.
 * 해당 클래스들은 싱글톤 패턴을 구현하지 않게 되어 확장에 용이해졌습니다.
 * 하나의 Retrofit 클라이언트로 여러 서비스 api들이 쿠키 값 등을 공유할 수 있습니다.
 */
public class SingletonContainer {
    private static final SingletonContainer instance = new SingletonContainer();

    private Retrofit retrofit;

    private static TeacherRepository teacherRepository;
    public static TeacherApi getTeacherApi() {
        return teacherRepository.getApi();
    }

    private static LoginRepository loginRepository;
    public static LoginApi getLoginApi() {
        return loginRepository.getApi();
    }

    private static ParentRepository parentRepository;
    public static ParentApi getParentApi() {
        return parentRepository.getApi();
    }


    /**
     * 각 api를 얻어오는 클래스들을 초기화 해주는 작업입니다.
     */
    private void init() {
        teacherRepository = new TeacherRepository(retrofit);
        loginRepository = new LoginRepository(retrofit);
        parentRepository = new ParentRepository(retrofit);
    }

    private SingletonContainer() {
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
        retrofit = new Retrofit.Builder()
                .baseUrl(NetworkConst.BASE_URL) //해당 주소는 aws ec2 인스턴스 접속 주소입니다.
                .addConverterFactory(GsonConverterFactory.create()) //Gson은 JSON과 객체 사이를 상호 변환할 수 있게 해줍니다.
                .client(client)
                .build();

        init();
    }
}
