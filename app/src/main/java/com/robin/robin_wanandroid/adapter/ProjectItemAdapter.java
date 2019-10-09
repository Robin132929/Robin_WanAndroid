package com.robin.robin_wanandroid.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.robin.robin_wanandroid.mvp.model.bean.ProjectItemBean;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ProjectItemAdapter extends BaseQuickAdapter<ProjectItemBean.DataBean.DatasBean, BaseViewHolder> {

    public ProjectItemAdapter(int layoutResId, @Nullable List<ProjectItemBean.DataBean.DatasBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, ProjectItemBean.DataBean.DatasBean item) {

    }
}
