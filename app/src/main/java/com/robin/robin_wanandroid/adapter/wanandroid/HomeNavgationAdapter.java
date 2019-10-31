package com.robin.robin_wanandroid.adapter.wanandroid;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.robin.robin_wanandroid.R;
import com.robin.robin_wanandroid.mvp.model.bean.MainArticleBean;
import com.robin.robin_wanandroid.mvp.model.bean.NavgationBean;

import androidx.annotation.NonNull;

public class HomeNavgationAdapter extends BaseQuickAdapter<NavgationBean, BaseViewHolder> {
    public HomeNavgationAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, NavgationBean item) {
//        helper.setText(R.id.nav_title,item.getTitle());
    }
}
