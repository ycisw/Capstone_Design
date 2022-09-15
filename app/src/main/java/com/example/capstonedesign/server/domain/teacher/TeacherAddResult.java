package com.example.capstonedesign.server.domain.teacher;

import com.google.gson.annotations.SerializedName;

/**
 * 강사 회원가입시 받아오는 결과입니다.
 */
public class TeacherAddResult {

    @SerializedName("success")
    private boolean success;

    public TeacherAddResult(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
