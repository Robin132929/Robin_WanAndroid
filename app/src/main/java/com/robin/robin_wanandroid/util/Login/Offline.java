package com.robin.robin_wanandroid.util.Login;

public class Offline implements State {
    public static final int LOGOUT = 0x002;
    @Override
    public int getType() {
        return LOGOUT;
    }

    @Override
    public String getName() {
        return "";
    }


}
