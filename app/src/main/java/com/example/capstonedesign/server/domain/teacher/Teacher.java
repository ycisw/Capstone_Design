package com.example.capstonedesign.server.domain.teacher;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * 강사 데이터입니다.
 */
public class Teacher implements Serializable {
    @SerializedName("lesson")
    private Long lesson;

    @SerializedName("name")
    private String name;

    @SerializedName("phone")
    private String phone;

    public Teacher() {
    }

    /**
     * @param lesson 강사번호입니다.
     * @param name 닉네임입니다.
     * @param phone 휴대폰 번호입니다.
     */
    public Teacher(Long lesson, String name, String phone) {
        this.lesson = lesson;
        this.name = name;
        this.phone = phone;
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

    public Long getLesson() {
        return lesson;
    }

    public void setLesson(Long lesson) {
        this.lesson = lesson;
    }
}
