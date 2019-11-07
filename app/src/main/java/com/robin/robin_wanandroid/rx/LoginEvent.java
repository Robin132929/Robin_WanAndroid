package com.robin.robin_wanandroid.rx;

public class LoginEvent {
    public boolean isLogin;
    public String name;

    public LoginEvent(boolean isLogin, String name) {
        this.isLogin = isLogin;
        this.name = name;
    }
}
