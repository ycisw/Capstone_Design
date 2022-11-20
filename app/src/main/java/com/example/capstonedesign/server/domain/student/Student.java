package com.example.capstonedesign.server.domain.student;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.time.LocalDate;

public class Student implements Serializable {
    @SerializedName("id")
    private Long id;

    @SerializedName("name")
    private String name;

    @SerializedName("phone")
    private String phone;

    @SerializedName("parentId")
    private Long parentId;

    @SerializedName("teacherLesson")
    private Long teacherLesson;

    public Student() {
    }

    public Student(Long id, String name, String phone, Long parentId, Long teacherLesson) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.parentId = parentId;
        this.teacherLesson = teacherLesson;
    }

    public Student(Long id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public Student(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getTeacherLesson() {
        return teacherLesson;
    }

    public void setTeacherLesson(Long teacherLesson) {
        this.teacherLesson = teacherLesson;
    }
}
