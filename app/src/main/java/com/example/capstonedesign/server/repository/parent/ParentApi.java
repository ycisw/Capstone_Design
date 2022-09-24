package com.example.capstonedesign.server.repository.parent;

import com.example.capstonedesign.server.domain.parent.ParentLoginResult;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * 학부모님과 관련된 API 들입니다.
 */
public interface ParentApi {

    /**
     * 학부모 로그인에 사용되는 API 입니다.
     * @param parentLoginFormMap ParentLoginForm을 맵으로 변환한 형태입니다.
     * @return ParentLoginResult를 반환할 수 있는 통신 성공 실패 작업입니다.
     */
    @FormUrlEncoded
    @Headers({"Accept: application/json;"})
    @POST("/parents/login")
    Call<ParentLoginResult> login(@FieldMap HashMap<String, String> parentLoginFormMap);
}
