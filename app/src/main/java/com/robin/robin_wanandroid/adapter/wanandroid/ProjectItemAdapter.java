package com.robin.robin_wanandroid.adapter.wanandroid;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.robin.rbase.CommonUtils.Utils.ContextUtil;
import com.robin.robin_wanandroid.R;
import com.robin.robin_wanandroid.mvp.model.bean.ProjectItemBean;
import com.robin.robin_wanandroid.util.GlideUtils;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ProjectItemAdapter extends BaseQuickAdapter<ProjectItemBean.DataBean.DatasBean, BaseViewHolder> {

    public ProjectItemAdapter(int layoutResId, @Nullable List<ProjectItemBean.DataBean.DatasBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, ProjectItemBean.DataBean.DatasBean item) {
        helper.setText(R.id.tv_article_author, item.getAuthor());
        helper.setText(R.id.tv_article_date, item.getNiceDate());
        helper.setText(R.id.tv_article_title, item.getTitle());
        if (item.getEnvelopePic().isEmpty() || (item.getEnvelopePic() == null)) {
            helper.setGone(R.id.iv_article_thumbnail, false);
        }else {
            GlideUtils.showBannerImage(ContextUtil.getAppContext(),helper.getView(R.id.iv_article_thumbnail),item.getEnvelopePic());
        }
    }
}
