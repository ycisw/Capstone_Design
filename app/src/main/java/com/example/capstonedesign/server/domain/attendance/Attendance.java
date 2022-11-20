package com.example.capstonedesign.server.domain.attendance;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.time.LocalDate;

public class Attendance implements Serializable {
    @SerializedName("id")
    private Long id;
    @SerializedName("dateAttendance")
    private LocalDate dateAttendance;

    @SerializedName("studentId")
    private Long studentId;

    public Attendance() {
    }

    public Attendance(Long id, LocalDate dateAttendance, Long studentId) {
        this.id = id;
        this.dateAttendance = dateAttendance;
        this.studentId = studentId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateAttendance() {
        return dateAttendance;
    }

    public void setDateAttendance(LocalDate dateAttendance) {
        this.dateAttendance = dateAttendance;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }
}
