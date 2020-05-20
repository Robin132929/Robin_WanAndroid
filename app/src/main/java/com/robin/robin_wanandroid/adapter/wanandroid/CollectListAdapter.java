package com.robin.robin_wanandroid.adapter.wanandroid;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.robin.robin_wanandroid.R;
import com.robin.robin_wanandroid.mvp.model.bean.GetCollectBean;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CollectListAdapter extends BaseQuickAdapter<GetCollectBean.DataBean.DatasBean, BaseViewHolder> {

    public CollectListAdapter(int layoutResId, @Nullable List<GetCollectBean.DataBean.DatasBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, GetCollectBean.DataBean.DatasBean item) {
        helper.setText(R.id.tv_article_title, item.getTitle());
        helper.setImageResource(R.id.iv_like, R.drawable.ic_like);
//                .addOnClickListener(R.id.iv_like);
        helper.setText(R.id.tv_article_author,item.getAuthor());
        helper.setText(R.id.tv_article_date,item.getNiceDate());
//        helper.addOnClickListener(R.id.iv_like);
    }
}
