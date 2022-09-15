package com.example.capstonedesign.server.domain.login;

/**
 * 로그인 화면에서 로그인 할 때 사용되는 데이터 클래스입니다.
 */
public class LoginForm {

    private String loginId;
    private String password;

    public LoginForm(String loginId, String password) {
        this.loginId = loginId;
        this.password = password;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
