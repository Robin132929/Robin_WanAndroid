package com.robin.robin_wanandroid.entity;

import com.chad.library.adapter.base.entity.SectionEntity;
import com.robin.robin_wanandroid.mvp.model.bean.NavgationBean;

public class NavgationSection extends SectionEntity<NavgationBean.DataBean.ArticlesBean> {
   public boolean isMore;
    public NavgationSection(boolean isHeader, String header,boolean showMore) {
        super(isHeader, header);
        this.isMore=showMore;
    }

    public NavgationSection(NavgationBean.DataBean.ArticlesBean navgationBean) {
        super(navgationBean);
    }
}
