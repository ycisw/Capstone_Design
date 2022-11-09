package com.example.capstonedesign.server.domain.teacher;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TeacherAddForm implements Serializable {
    @SerializedName("id")
    private String id;

    @SerializedName("password")
    private String password;

    @SerializedName("name")
    private String name;

    public TeacherAddForm() {
    }

    /**
     * @param id 로그인 할 때 사용하는 아이디입니다.
     * @param password 로그인 할 때 사용하는 비밀번호입니다.
     * @param name 닉네임이에요.
     */
    public TeacherAddForm(String id, String password, String name) {
        this.id = id;
        this.password = password;
        this.name = name;
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
}
