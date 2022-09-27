package com.example.capstonedesign.server.domain.student;

import com.example.capstonedesign.server.domain.parent.Parent;
import com.example.capstonedesign.server.domain.student.Student;
import com.google.gson.annotations.SerializedName;

public class StudentParent {

    @SerializedName("student")
    private Student student;
    @SerializedName("parent")
    private Parent parent;

    public StudentParent() {
    }

    public StudentParent(Student student, Parent parent) {
        this.student = student;
        this.parent = parent;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }
}
