package com.example.capstonedesign.server.domain.attendance;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Attendance implements Serializable {
    @SerializedName("id")
    private Long id;
    @SerializedName("dateAttendance")
    private LocalDate dateAttendance;
    @SerializedName("inTime")
    private LocalDateTime inTime;
    @SerializedName("outTime")
    private LocalDateTime outTime;
    @SerializedName("confirm")
    private String confirm;

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public void setInTime(LocalDateTime inTime) {
        this.inTime = inTime;
    }

    public void setOutTime(LocalDateTime outTime) {
        this.outTime = outTime;
    }

    @SerializedName("studentId")
    private Long studentId;

    public Attendance() {
    }

    public Attendance(LocalDate dateAttendance, String confirm, Long studentId) {
        this.dateAttendance = dateAttendance;
        this.confirm = confirm;
        this.studentId = studentId;
    }

    public Attendance(Long id, LocalDate dateAttendance, LocalDateTime inTime, LocalDateTime outTime, String confirm, Long studentId) {
        this.id = id;
        this.dateAttendance = dateAttendance;
        this.inTime = inTime;
        this.outTime = outTime;
        this.confirm = confirm;
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
