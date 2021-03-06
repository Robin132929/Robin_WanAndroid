package com.robin.robin_wanandroid.adapter.wanandroid;

import android.graphics.drawable.GradientDrawable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import com.robin.robin_wanandroid.R;
import com.robin.robin_wanandroid.app.App;
import com.robin.robin_wanandroid.mvp.model.bean.KnowledgeStructureBean;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class KnowledgeListChildAdapter extends BaseQuickAdapter<KnowledgeStructureBean.DataBean.ChildrenBean, BaseViewHolder> {
    public KnowledgeListChildAdapter(int layoutResId, @Nullable List<KnowledgeStructureBean.DataBean.ChildrenBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, KnowledgeStructureBean.DataBean.ChildrenBean item) {
        ((GradientDrawable)  helper.itemView.findViewById(R.id.show_text).getBackground()).setColor(item.isSelect() ? App.getmMyAppComponent().application().getResources().getColor(R.color.Red):App.getmMyAppComponent().application().getResources().getColor(R.color.Grey200));
        helper.setText(R.id.show_text,item.getName());

    }
}
