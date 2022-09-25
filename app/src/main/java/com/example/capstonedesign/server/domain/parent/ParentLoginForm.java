package com.example.capstonedesign.server.domain.parent;

/**
 * 학부모 로그인 화면에서 로그인 할 때 사용되는 데이터 클래스입니다.
 */
public class ParentLoginForm {

    private String phone;
    private String name;

    /**
     * 학부모 로그인에 필요한 데이터 입니다.
     * @param phone 학부모 휴대폰 번호
     * @param name 학부모 이름
     */
    public ParentLoginForm(String phone, String name) {
        this.phone = phone;
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
