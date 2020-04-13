package com.robin.robin_wanandroid.util.Login;

import android.content.Context;
import android.content.Intent;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class Online implements State {
    public static final int LOGIN = 0x001;
    private String userName;

    public Online(String UserName) {
        userName=UserName;
    }

    @Override
    public int getType() {
        return LOGIN;
    }

    @Override
    public String getName() {
        return userName;
    }


}
