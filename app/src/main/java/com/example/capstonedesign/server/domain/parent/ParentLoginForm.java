package com.example.capstonedesign.server.domain.parent;

/**
 * 학부모 로그인 화면에서 로그인 할 때 사용되는 데이터 클래스입니다.
 */
public class ParentLoginForm {

    private String parentPhone;
    private String studentPhone;

    /**
     * 학부모 로그인에 필요한 데이터를 넣어주세요.
     * @param parentPhone 부모님(본인) 휴대폰 번호입니다.
     * @param studentPhone 학생 휴대폰 번호입니다.
     */
    public ParentLoginForm(String parentPhone, String studentPhone) {
        this.parentPhone = parentPhone;
        this.studentPhone = studentPhone;
    }

    public String getParentPhone() {
        return parentPhone;
    }

    public void setParentPhone(String parentPhone) {
        this.parentPhone = parentPhone;
    }

    public String getStudentPhone() {
        return studentPhone;
    }

    public void setStudentPhone(String studentPhone) {
        this.studentPhone = studentPhone;
    }
}
