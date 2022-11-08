package com.example.capstonedesign.server.domain.login;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * 로그인 성공 데이터를 결과로 받아오는 클래스입니다.
 * isSuccess() 메서드를 통해 성공 여부를 받아올 수 있어요.
 */
public class LoginResult implements Serializable {

    @SerializedName("success")
    private boolean success;

    public LoginResult(boolean success) {
        this.success = success;
    }

    /**
     * @return 로그인 성공 여부 반환
     */
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
