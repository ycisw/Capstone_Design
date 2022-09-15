package com.example.capstonedesign.server.repository;

import com.example.capstonedesign.server.domain.login.LoginResult;
import com.example.capstonedesign.server.domain.teacher.TeacherAddResult;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * 안드로이드에서 HTTP를 사용하기 위해 Retrofit을 사용하게 되었습니다.
 * GET, POST, PUT, DELETE 등의 HTTP 메서드를 사용할 수 있습니다.
 */
public interface RetrofitAPI {

    /**
     * 로그인에 사용되는 API입니다.
     * @param loginFormMap LoginForm을 맵으로 변환한 형태입니다.
     * @return LoginResult를 반환할 수 있는 성공 실패 여부로 나뉘는 작업이 반환됩니다.
     */
    @FormUrlEncoded
    @Headers({"Accept: application/json;"})
    @POST("/login")
    Call<LoginResult> login(@FieldMap HashMap<String, String> loginFormMap);

    /**
     * 회원가입에 사용되는 API입니다.
     * @param teacherMap Teacher를 맵으로 변환한 형태입니다.
     * @return 위와 동일합니다.
     */
    @FormUrlEncoded
    @Headers({"Accept: application/json;"})
    @POST("/teachers/add")
    Call<TeacherAddResult> register(@FieldMap HashMap<String, String> teacherMap);
}
