package com.example.capstonedesign.server.repository.teacher;

import com.example.capstonedesign.server.domain.teacher.Teacher;
import com.example.capstonedesign.server.domain.teacher.TeacherAddForm;
import com.example.capstonedesign.server.domain.teacher.TeacherAddResult;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * 안드로이드에서 HTTP를 사용하기 위해 Retrofit을 사용하게 되었습니다.
 * GET, POST, PUT, DELETE 등의 HTTP 메서드를 사용할 수 있습니다.
 * 강사와 관련된 API들입니다.
 */
public interface TeacherApi {
    /**
     * 회원가입에 사용되는 API입니다.
     * @return 위와 동일합니다.
     */
    @Headers({"Accept: application/json;"})
    @POST("/teachers/add")
    Call<TeacherAddResult> register(@Body TeacherAddForm teacherAddForm);

    @Headers({"Accept: application/json;"})
    @POST("/teachers/profile")
    Call<Teacher> profile();
}
