package com.example.capstonedesign.server.domain.login;

/**
 * 로그인 화면에서 로그인 할 때 사용되는 데이터 클래스입니다.
 */
public class LoginForm {

    private String loginId;
    private String password;

    /**
     * 로그인할때 필요한 데이터를 넣어주세요.
     * @param loginId 로그인시 아이디
     * @param password 로그인시 비밀번호
     */
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
