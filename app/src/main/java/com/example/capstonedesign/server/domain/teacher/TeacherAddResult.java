package com.example.capstonedesign.server.domain.teacher;

import com.google.gson.annotations.SerializedName;

/**
 * 강사 회원가입시 받아오는 결과입니다.
 * isSuccess() 메서드를 통해 성공 여부를 확인할 수 있습니다.
 */
public class TeacherAddResult {

    @SerializedName("success")
    private boolean success;

    public TeacherAddResult(boolean success) {
        this.success = success;
    }

    /**
     * @return 회원 가입 성공 여부입니다.
     */
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
