package com.example.capstonedesign.server.domain.teacher;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * 강사 회원가입시 받아오는 결과입니다.
 * isSuccess() 메서드를 통해 성공 여부를 확인할 수 있습니다.
 */
public class TeacherAddResult implements Serializable {

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    public TeacherAddResult(boolean success, String message) {
        this.success = success;
        this.message = message;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
