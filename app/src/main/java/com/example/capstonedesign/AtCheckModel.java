package com.example.capstonedesign;

import com.example.capstonedesign.server.domain.student.StudentParent;

/**
 * AttendanceCheck RecyclerView Adapter에서 사용할 모델
 */
public class AtCheckModel {
    private StudentParent studentParent; //학생과 학부모 정보를 서버로부터 받아올 것이다.
    private boolean select; //선택되었는지 체크박스를 통해 동기화

    public AtCheckModel(StudentParent studentParent) {
        this.studentParent = studentParent;
    }

    public AtCheckModel(StudentParent studentParent, boolean select) {
        this.studentParent = studentParent;
        this.select = select;
    }

    public StudentParent getStudentParent() {
        return studentParent;
    }

    public void setStudentParent(StudentParent studentParent) {
        this.studentParent = studentParent;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }
}
