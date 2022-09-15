package com.example.capstonedesign.server.domain.login;

import com.google.gson.annotations.SerializedName;

/**
 * 로그인 성공 데이터를 결과로 받아오는 클래스입니다.
 */
public class LoginResult {

    @SerializedName("success")
    private boolean success;

    public LoginResult(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
