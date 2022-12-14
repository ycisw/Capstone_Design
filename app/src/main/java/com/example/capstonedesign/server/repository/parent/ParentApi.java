package com.example.capstonedesign.server.repository.parent;

import com.example.capstonedesign.server.domain.PhoneValidationForm;
import com.example.capstonedesign.server.domain.attendance.Attendance;
import com.example.capstonedesign.server.domain.parent.ParentLoginForm;
import com.example.capstonedesign.server.domain.parent.ParentLoginResult;
import com.example.capstonedesign.server.domain.parent.ValidationResult;
import com.example.capstonedesign.server.domain.student.Student;
import com.example.capstonedesign.server.domain.student.StudentTeacher;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * 학부모님과 관련된 API 들입니다.
 */
public interface ParentApi {

    /**
     * 학부모 로그인에 사용되는 API 입니다.
     * @return ParentLoginResult를 반환할 수 있는 통신 성공 실패 작업입니다.
     */
    @Headers({"Accept: application/json;"})
    @POST("/parents/login")
    Call<ParentLoginResult> login(@Body ParentLoginForm parentLoginForm);

    /**
     * 학부모 로그인에 사용되는 번호를 인증하기위해 인증번호를 메시지로 보냅니다.
     * 60초 이내로 인증해야됩니다.
     * @param phoneValidationForm 해당 휴대폰 번호로 보내줍니다.
     * @return 결과는 없습니다.
     */
    @Headers({"Accept: application/json;"})
    @POST("/parents/sendvalidation")
    Call<Void> sendValidation(@Body PhoneValidationForm phoneValidationForm);

    /**
     * 휴대폰에 받은 인증 번호를 넣어서 전달합니다.
     * @param validationCode 받은 인증번호입니다.
     * @return 인증이 잘 되었는지 반환해줍니다.
     */
    @Headers({"Accept: application/json;"})
    @POST("/parents/validation")
    Call<ValidationResult> validation(@Body String validationCode);

    /**
     * 현재 학부모의 자녀들을 조회합니다.
     * @return 자녀 학생 리스트를 반환합니다.
     */
    @Headers({"Accept: application/json;"})
    @GET("/parents/students")
    Call<List<Student>> children();

    /**
     * 자녀와 담당강사의 정보를 조회합니다.
     * @param studentId 자녀의 학생아이디
     */
    @Headers({"Accept: application/json;"})
    @GET("/parents/student")
    Call<StudentTeacher> student(@Query("studentId") Long studentId);

    /**
     * 자녀의 출결기록을 조회합니다.
     * @param studentId 자녀의 학생아이디
     */
    @Headers({"Accept: application/json;"})
    @GET("/parents/student/attendances")
    Call<List<Attendance>> studentAttendances(@Query("studentId") Long studentId);
}
