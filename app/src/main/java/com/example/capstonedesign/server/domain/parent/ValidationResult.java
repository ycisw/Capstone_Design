package com.example.capstonedesign.server.domain.parent;

import com.google.gson.annotations.SerializedName;

/**
 * 학부모 로그인 인증 결과입니다.
 */
public class ValidationResult {
    @SerializedName("success")
    private boolean success;

    public ValidationResult(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
