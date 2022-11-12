package com.example.capstonedesign.server.domain.student;

import com.example.capstonedesign.server.domain.teacher.Teacher;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class StudentTeacher implements Serializable {
    @SerializedName("student")
    private Student student;
    @SerializedName("teacher")
    private Teacher teacher;

    public StudentTeacher() {
    }

    public StudentTeacher(Student student, Teacher teacher) {
        this.student = student;
        this.teacher = teacher;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
