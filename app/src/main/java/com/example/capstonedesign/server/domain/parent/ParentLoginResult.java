package com.example.capstonedesign.server.domain.parent;

import com.google.gson.annotations.SerializedName;

/**
 * 학부모 로그인 성공 여부를 결과로 받아오는 클래스입니다.
 * isSuccess() 메서드를 통해 여부를 알 수 있어요.
 */
public class ParentLoginResult {

    @SerializedName("success")
    private boolean success;

    public ParentLoginResult(boolean success) {
        this.success = success;
    }

    /**
     * @return 학부모 로그인 성공 여부
     */
    public boolean isSuccess() {
        return success;
    }
}
