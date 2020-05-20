package com.robin.robin_wanandroid.adapter.wanandroid;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.robin.robin_wanandroid.R;
import com.robin.robin_wanandroid.mvp.model.bean.WechatTitleBean;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class WechatTitleAdapter extends BaseQuickAdapter<WechatTitleBean.DataBean,BaseViewHolder> {

    public WechatTitleAdapter(int layoutResId, @Nullable List<WechatTitleBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, WechatTitleBean.DataBean item) {
         helper.setText(R.id.show_title,item.getName());
    }
}
