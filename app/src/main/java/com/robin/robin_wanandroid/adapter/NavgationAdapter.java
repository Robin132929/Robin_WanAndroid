package com.robin.robin_wanandroid.adapter;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.robin.robin_wanandroid.R;
import com.robin.robin_wanandroid.entity.NavgationSection;
import com.robin.robin_wanandroid.mvp.model.bean.NavgationBean;

import java.util.List;
import java.util.Random;

import androidx.annotation.NonNull;

public class NavgationAdapter extends BaseSectionQuickAdapter<NavgationSection, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param layoutResId      The layout resource id of each item.
     * @param sectionHeadResId The section head layout id for each item
     * @param data             A new list is created out of this one to avoid mutable list
     */
    public NavgationAdapter(int layoutResId, int sectionHeadResId, List<NavgationSection> data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, NavgationSection item) {
    helper.setText(R.id.header, item.header);
    if (item.isMore) {
        helper.setVisible(R.id.more,item.isMore);
        helper.addOnClickListener(R.id.more);
    }else {
        helper.setVisible(R.id.more,item.isMore);
    }
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, NavgationSection item) {
        NavgationBean.DataBean.ArticlesBean navData=item.t;
        helper.setText(R.id.tv_nav_title,navData.getTitle());
        Random mRandom=new Random();
        switch (mRandom.nextInt(6)) {
            case 0:
                helper.setImageResource(R.id.iv_nav_img,R.drawable.tianquan);
                break;
            case 1:
                helper.setImageResource(R.id.iv_nav_img,R.drawable.binggan);
                break;
            case 2:
                helper.setImageResource(R.id.iv_nav_img,R.drawable.caomei);
                break;
            case 3:
                helper.setImageResource(R.id.iv_nav_img,R.drawable.niuyouguo);
                break;
            case 4:
                helper.setImageResource(R.id.iv_nav_img,R.drawable.buding);
                break;
            case 5:
                helper.setImageResource(R.id.iv_nav_img,R.drawable.dangao);
                break;
                default:
                    break;

        }


    }
}
