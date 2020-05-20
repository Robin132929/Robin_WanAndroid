package com.robin.robin_wanandroid.adapter.wanandroid;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.robin.robin_wanandroid.R;
import com.robin.robin_wanandroid.mvp.model.bean.wendaBean;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class WendaListAdapter extends BaseQuickAdapter<wendaBean.DataBean.DatasBean, BaseViewHolder> {

    public WendaListAdapter(int layoutResId, @Nullable List<wendaBean.DataBean.DatasBean> data) {
        super(R.layout.item_collect_list, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, wendaBean.DataBean.DatasBean item) {
        helper.setText(R.id.tv_article_title,item.getTitle());

    }
}
