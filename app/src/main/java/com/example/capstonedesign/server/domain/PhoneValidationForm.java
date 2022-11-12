package com.example.capstonedesign.server.domain;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * 휴대전화번호 검증에 사용되는 입력폼입니다.
 */
public class PhoneValidationForm implements Serializable {
    @SerializedName("phone")
    private String phone;

    public PhoneValidationForm(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
