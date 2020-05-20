package com.robin.robin_wanandroid.adapter.wanandroid;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.robin.robin_wanandroid.R;
import com.robin.robin_wanandroid.mvp.model.bean.MineItemBean;

import java.util.List;

import androidx.annotation.NonNull;

public class MineAdapter extends BaseSectionQuickAdapter<MineItemBean, BaseViewHolder> {

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param layoutResId      The layout resource id of each item.
     * @param sectionHeadResId The section head layout id for each item
     * @param data             A new list is created out of this one to avoid mutable list
     */
    public MineAdapter(int layoutResId, int sectionHeadResId, List<MineItemBean> data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, MineItemBean item) {
     helper.setImageResource(R.id.iv_icon,item.getIcon());
     helper.setText(R.id.tv_item_title,item.getTitle());
     helper.setImageResource(R.id.iv_arr,item.getArr());
    }

    @Override
    protected void convertHeader(BaseViewHolder baseViewHolder, MineItemBean mineItemBean) {
        if (mineItemBean.isHeader){
            baseViewHolder.setGone(R.id.more,false);
        }
    }
}
