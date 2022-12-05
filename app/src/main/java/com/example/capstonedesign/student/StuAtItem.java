package com.example.capstonedesign.student;

import com.example.capstonedesign.server.domain.attendance.Attendance;

public class StuAtItem{
    private String date;
    private Attendance attendance;
    private boolean select;

    public StuAtItem() {
    }

    public StuAtItem(String date, Attendance attendance) {
        this.date = date;
        this.attendance = attendance;
    }

    public StuAtItem(String date, Attendance attendance, boolean select) {
        this.date = date;
        this.attendance = attendance;
        this.select = select;
    }

    public String getDate(){
        return date;
    }
    public void setDate(String date){
        this.date = date;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public Attendance getAttendance() {
        return attendance;
    }

    public void setAttendance(Attendance attendance) {
        this.attendance = attendance;
    }
}
