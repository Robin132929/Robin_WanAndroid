package com.robin.robin_wanandroid.adapter.wanandroid;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.robin.robin_wanandroid.R;
import com.robin.robin_wanandroid.mvp.model.bean.FootPrintBean;
import com.robin.robin_wanandroid.mvp.model.bean.GetCollectBean;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class FootListAdapter extends BaseQuickAdapter<FootPrintBean.DataBean, BaseViewHolder> {

    public FootListAdapter(int layoutResId, @Nullable List<FootPrintBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, FootPrintBean.DataBean item) {
        helper.setText(R.id.tv_article_title, item.getName());
    }
}
