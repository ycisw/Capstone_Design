package com.example.capstonedesign.server.domain.login;

import com.google.gson.annotations.SerializedName;

public class LoginForm {

    @SerializedName("loginId")
    private String loginId;

    @SerializedName("password")
    private String password;
}
