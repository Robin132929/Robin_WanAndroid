package com.robin.robin_wanandroid.util.loading;

import android.view.View;

import com.robin.rbase.CommonUtils.Logger.Logger;

public class GloadingAdapter implements Gloading.Adapter {
    @Override
    public View getView(Gloading.Holder holder, View convertView, int status) {
        Logger.i("gloading getview "+convertView +" status "+status);
        GlobalLoadingStatusView loadingStatusView = null;
        if (convertView!=null&&convertView instanceof GlobalLoadingStatusView){
            loadingStatusView= (GlobalLoadingStatusView) convertView;
        }
        if (loadingStatusView==null){
            loadingStatusView=new GlobalLoadingStatusView(holder.getContext(), new Runnable() {
                @Override
                public void run() {

                }
            });
        }
        loadingStatusView.setStatus(status);
        return loadingStatusView;
    }
}
