package com.example.capstonedesign.server.domain.login;

import com.google.gson.annotations.SerializedName;

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
