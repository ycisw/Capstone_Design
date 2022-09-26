package com.example.capstonedesign.server.repository.parent;

import com.example.capstonedesign.server.domain.parent.ParentLoginResult;
import com.example.capstonedesign.server.domain.parent.ValidationResult;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Field;
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

    /**
     * 학부모 로그인에 사용되는 번호를 인증하기위해 인증번호를 메시지로 보냅니다.
     * 60초 이내로 인증해야됩니다.
     * @param phone 해당 휴대폰 번호로 보내줍니다.
     * @return 결과는 없습니다.
     */
    @FormUrlEncoded
    @Headers({"Accept: application/json;"})
    @POST("/parents/sendvalidation")
    Call<Void> sendValidation(@Field("phone") String phone);

    /**
     * 휴대폰에 받은 인증 번호를 넣어서 전달합니다.
     * @param validationCode 받은 인증번호입니다.
     * @return 인증이 잘 되었는지 반환해줍니다.
     */
    @FormUrlEncoded
    @Headers({"Accept: application/json;"})
    @POST("/parents/validation")
    Call<ValidationResult> validation(@Field("validationCode") String validationCode);
}
