package com.example.capstonedesign.server.domain.login;

import java.io.Serializable;

/**
 * 로그인 화면에서 로그인 할 때 사용되는 데이터 클래스입니다.
 */
public class LoginForm implements Serializable {

    private String id;
    private String password;

    /**
     * 로그인할때 필요한 데이터를 넣어주세요.
     * @param loginId 로그인시 아이디
     * @param password 로그인시 비밀번호
     */
    public LoginForm(String loginId, String password) {
        this.id = loginId;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
