package com.robin.robin_wanandroid.util;

import android.text.TextUtils;

import com.robin.rbase.CommonUtils.Utils.PreferUtil;
import com.robin.robin_wanandroid.app.App;
import com.robin.robin_wanandroid.util.Login.LoginHelper;
import com.robin.robin_wanandroid.util.Login.Online;

public class CacheUtil {
   public static String getCookie(String url){
       if (!TextUtils.isEmpty(url)) {
           return (String) PreferUtil.get(App.getmMyAppComponent().application(), url, "");
       }
       return "";
   }

    public static boolean isLogin(){
        return LoginHelper.getInstance().getState().getType()== Online.LOGIN;
    }

    public static void setCookie(String url,String cookie){
       if (!TextUtils.isEmpty(url))
        PreferUtil.persist(App.getmMyAppComponent().application(),url,cookie);
    }
}
