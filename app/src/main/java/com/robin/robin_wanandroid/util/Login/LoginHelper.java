package com.robin.robin_wanandroid.util.Login;

import android.content.Context;
import android.content.Intent;
import com.robin.robin_wanandroid.app.App;

public class LoginHelper {

    private State state;
    private Intent intent;
    private Context context;
    private RLogin rLogin;
    Linstener linstener;

    private LoginHelper() {
        state = new Offline();
    }

    public static LoginHelper getInstance() {
        return LoginHelperHolder.instance;
    }

    public void init(Context context,RLogin rLogin){
        this.context=context;
        this.rLogin=rLogin;
    }

    public boolean login() throws Exception {
        if (rLogin==null){
            throw  new Exception("RLogin can not be null");
        }
        return rLogin.login(context);
    }

    void addLinstener(Linstener linstener){
        this.linstener=linstener;
    }

    public State getState() {
        return state;
    }

    public void setState(State mstate) {
        if (state == null) {
            state = mstate;
        }

        if (mstate.getType() != state.getType()) {
            state = mstate;
            if (state.getType()== Online.LOGIN&&linstener!=null){
                linstener.LoginSuccess();
            }
        }
    }

    private static class LoginHelperHolder {
        private static LoginHelper instance = new LoginHelper();
    }

}
