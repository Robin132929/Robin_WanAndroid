package com.robin.robin_wanandroid.entity;

import com.chad.library.adapter.base.entity.SectionEntity;
import com.robin.robin_wanandroid.mvp.model.bean.NavgationBean;

public class NavgationSection implements SectionEntity {
   public boolean isMore;
    public NavgationSection(boolean isHeader, String header,boolean showMore) {
        this.isMore=showMore;
    }

    public NavgationSection(NavgationBean.DataBean.ArticlesBean navgationBean) {
    }

    @Override
    public boolean isHeader() {
        return false;
    }

    @Override
    public int getItemType() {
        return 0;
    }
}
