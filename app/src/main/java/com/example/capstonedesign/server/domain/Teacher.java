package com.example.capstonedesign.server.domain;

import com.google.gson.annotations.SerializedName;

public class Teacher {
    @SerializedName("teacherId")
    private Long teacherId;

    @SerializedName("loginId")
    private String loginId;

    @SerializedName("name")
    private String name;

    @SerializedName("password")
    private String password;

    public Teacher() {
    }

    public Teacher(Long teacherId, String loginId, String name, String password) {
        this.teacherId = teacherId;
        this.loginId = loginId;
        this.name = name;
        this.password = password;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
