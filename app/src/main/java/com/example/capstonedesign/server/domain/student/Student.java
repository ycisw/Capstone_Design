package com.example.capstonedesign.server.domain.student;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;

public class Student {
    @SerializedName("id")
    private Long id;

    @SerializedName("name")
    private String name;

    @SerializedName("phone")
    private String phone;

    @SerializedName("tuition")
    private Long tuition;

    @SerializedName("regDate")
    private LocalDate regDate;

    @SerializedName("parentId")
    private Long parentId;

    @SerializedName("teacherLesson")
    private Long teacherLesson;

    public Student() {
    }

    public Student(Long id, String name, String phone, Long tuition, LocalDate regDate, Long parentId, Long teacherLesson) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.tuition = tuition;
        this.regDate = regDate;
        this.parentId = parentId;
        this.teacherLesson = teacherLesson;
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

    public Long getTuition() {
        return tuition;
    }

    public void setTuition(Long tuition) {
        this.tuition = tuition;
    }

    public LocalDate getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDate regDate) {
        this.regDate = regDate;
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
