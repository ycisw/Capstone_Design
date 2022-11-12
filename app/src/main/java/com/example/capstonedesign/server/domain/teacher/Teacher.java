package com.example.capstonedesign.server.domain.teacher;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * 강사 데이터입니다.
 */
public class Teacher implements Serializable {
    @SerializedName("id")
    private String id;

    @SerializedName("password")
    private String password;

    @SerializedName("name")
    private String name;

    @SerializedName("phone")
    private String phone;

    public Teacher() {
    }

    /**
     * @param id 로그인 할 때 사용하는 아이디입니다.
     * @param password 로그인 할 때 사용하는 비밀번호입니다.
     * @param name 닉네임입니다.
     * @param phone 휴대폰 번호입니다.
     */
    public Teacher(String id, String password, String name, String phone) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
