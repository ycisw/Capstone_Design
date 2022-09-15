package com.example.capstonedesign.server.domain.login;

import com.google.gson.annotations.SerializedName;

public class LoginResult {

    @SerializedName("isSuccess")
    private boolean isSuccess;

    public LoginResult() {
    }

    public LoginResult(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }
}
