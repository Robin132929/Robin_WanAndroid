package com.robin.robin_wanandroid.adapter.wanandroid;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.robin.robin_wanandroid.R;
import com.robin.robin_wanandroid.mvp.model.bean.ProjectCategoryBean;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ProjectCategoryAdapter extends BaseQuickAdapter<ProjectCategoryBean.DataBean, BaseViewHolder> {

    public ProjectCategoryAdapter(int layoutResId, @Nullable List<ProjectCategoryBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, ProjectCategoryBean.DataBean item) {
        helper.setText(R.id.show_text,item.getName());
    }
}
