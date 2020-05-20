package com.robin.robin_wanandroid.adapter.gank;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.robin.rbase.CommonUtils.Logger.Logger;
import com.robin.robin_wanandroid.R;
import com.robin.robin_wanandroid.app.App;
import com.robin.robin_wanandroid.mvp.model.bean.GankAndroidBean;
import com.robin.robin_wanandroid.util.GlideUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class GankAndroidAdapter extends BaseQuickAdapter<GankAndroidBean.ResultsBean, BaseViewHolder> {

    public GankAndroidAdapter(int layoutResId, @Nullable List<GankAndroidBean.ResultsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, GankAndroidBean.ResultsBean item) {
        helper.setText(R.id.tv_article_title, item.getDesc());
        Logger.i("image is null " + item.getImages().size() + " images is empty " + item.getImages().isEmpty());

        if (item.getImages().isEmpty()) {
            helper.setGone(R.id.iv_article_pic, false);
            helper.setGone(R.id.iv_article_pic1, false);
            helper.setGone(R.id.iv_article_pic2, false);
        } else {
            int size =item.getImages().size();
            if (size == 1) {
                helper.setGone(R.id.iv_article_pic, true);
                GlideUtils.showBannerImage(App.getmMyAppComponent().application(), helper.getView(R.id.iv_article_pic), item.getImages().get(0));
            }
            if (size == 2) {
                helper.setGone(R.id.iv_article_pic, true);
                helper.setGone(R.id.iv_article_pic1, true);
                GlideUtils.showBannerImage(App.getmMyAppComponent().application(), helper.getView(R.id.iv_article_pic), item.getImages().get(0));
                GlideUtils.showBannerImage(App.getmMyAppComponent().application(), helper.getView(R.id.iv_article_pic1), item.getImages().get(1));
            }
            if (size >= 3) {
                helper.setGone(R.id.iv_article_pic, true);
                helper.setGone(R.id.iv_article_pic1, true);
                helper.setGone(R.id.iv_article_pic2, true);
                GlideUtils.showBannerImage(App.getmMyAppComponent().application(), helper.getView(R.id.iv_article_pic), item.getImages().get(0));
                GlideUtils.showBannerImage(App.getmMyAppComponent().application(), helper.getView(R.id.iv_article_pic1), item.getImages().get(1));
                GlideUtils.showBannerImage(App.getmMyAppComponent().application(), helper.getView(R.id.iv_article_pic2), item.getImages().get(2));
            }
        }
        helper.setText(R.id.tv_article_author, item.getWho());

    }
}
