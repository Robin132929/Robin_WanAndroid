package com.robin.robin_wanandroid.delegate;

import android.content.Context;

import com.robin.rbase.CommonUtils.Logger.Logger;

import me.jessyan.rxerrorhandler.handler.listener.ResponseErrorListener;

public class ResponseErrorListenerImpl implements ResponseErrorListener {
    @Override
    public void handleResponseError(Context context, Throwable t) {
        Logger.i(t.getMessage());
    }
}
