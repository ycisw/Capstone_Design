package com.example.capstonedesign.server.domain.attendance;

import com.example.capstonedesign.server.domain.student.Student;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class AttendanceStudentResult implements Serializable {
    @SerializedName("student")
    private Student student;
    @SerializedName("attendances")
    private List<Attendance> attendances;

    public AttendanceStudentResult() {
    }

    public AttendanceStudentResult(Student student, List<Attendance> attendances) {
        this.student = student;
        this.attendances = attendances;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<Attendance> getAttendances() {
        return attendances;
    }

    public void setAttendances(List<Attendance> attendances) {
        this.attendances = attendances;
    }
}
