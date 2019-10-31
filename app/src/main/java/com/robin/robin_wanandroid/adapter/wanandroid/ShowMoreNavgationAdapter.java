package com.robin.robin_wanandroid.adapter.wanandroid;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.robin.robin_wanandroid.R;
import com.robin.robin_wanandroid.mvp.model.bean.NavgationBean;

import java.util.List;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ShowMoreNavgationAdapter extends BaseQuickAdapter<NavgationBean.DataBean.ArticlesBean, BaseViewHolder> {
    public ShowMoreNavgationAdapter(int layoutResId, @Nullable List<NavgationBean.DataBean.ArticlesBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, NavgationBean.DataBean.ArticlesBean item) {
        helper.setText(R.id.tv_nav_title,item.getTitle());
        Random mRandom=new Random();
        switch (mRandom.nextInt(6)) {
            case 0:
                helper.setImageResource(R.id.iv_nav_img,R.drawable.tianquan);
                break;
            case 1:
                helper.setImageResource(R.id.iv_nav_img,R.drawable.binggan);
                break;
            case 2:
                helper.setImageResource(R.id.iv_nav_img,R.drawable.caomei);
                break;
            case 3:
                helper.setImageResource(R.id.iv_nav_img,R.drawable.niuyouguo);
                break;
            case 4:
                helper.setImageResource(R.id.iv_nav_img,R.drawable.buding);
                break;
            case 5:
                helper.setImageResource(R.id.iv_nav_img,R.drawable.dangao);
                break;
            default:
                break;

        }


    }
}
