package com.robin.robin_wanandroid.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

public class GlideUtils {
    private static boolean isLoadImage=SettingUtil.isNoPhoto();
    public static void showBannerImage(Context context, ImageView imageView, String url){
        if(true) {
            RequestOptions requestOptions = new RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).centerInside();
            Glide.with(context).load(url).apply(requestOptions).into(imageView);
        }
    }
}
