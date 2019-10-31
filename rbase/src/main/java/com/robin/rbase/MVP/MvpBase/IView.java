package com.robin.rbase.MVP.MvpBase;

import android.app.Activity;
import android.content.Intent;

import com.robin.rbase.CommonUtils.ArmsUtils;

import androidx.annotation.NonNull;

import static com.robin.rbase.CommonUtils.Utils.Preconditions.checkNotNull;

public interface IView {
    /**
     * 显示加载
     */
    void showLoading();

    /**
     * 隐藏加载
     */
    void hideLoading();

    void showError();

    /**
     * 跳转 {@link Activity}
     *
     * @param intent {@code intent} 不能为 {@code null}
     */
     default void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        //TODO
        ArmsUtils.startActivity(intent);
    }

    /**
     * 杀死自己
     */
     default void killMyself(){

    }

}
