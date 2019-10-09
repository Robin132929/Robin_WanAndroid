package com.robin.robin_wanandroid.entity;

import com.robin.robin_wanandroid.mvp.model.bean.BannerBean;
import com.robin.robin_wanandroid.mvp.model.bean.MainArticleBean;

public class HomeData {
    private MainArticleBean.DataBean.DatasBean articleData;
    private BannerBean bannerData;
    private String img;
    private String title;

    public HomeData(MainArticleBean.DataBean.DatasBean articleData, BannerBean bannerData, String img, String title) {
        this.articleData = articleData;
        this.bannerData = bannerData;
        this.img = img;
        this.title = title;
    }

    public MainArticleBean.DataBean.DatasBean getArticleData() {
        return articleData;
    }

    public void setArticleData(MainArticleBean.DataBean.DatasBean articleData) {
        this.articleData = articleData;
    }

    public BannerBean getBannerData() {
        return bannerData;
    }

    public void setBannerData(BannerBean bannerData) {
        this.bannerData = bannerData;
    }

    public String getImg() {
        return img == null ? "" : img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title == null ? "" : title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
