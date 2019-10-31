package com.robin.robin_wanandroid.mvp.contract.wanandroid;

import com.robin.rbase.MVP.MvpBase.IModel;
import com.robin.rbase.MVP.MvpBase.IPresenter;
import com.robin.rbase.MVP.MvpBase.IView;
import com.robin.robin_wanandroid.mvp.model.bean.BannerBean;
import com.robin.robin_wanandroid.mvp.model.bean.LogoutResultBean;
import com.robin.robin_wanandroid.mvp.model.bean.MainArticleBean;
import com.robin.robin_wanandroid.mvp.model.bean.TopArticleBean;

import io.reactivex.Observable;

public interface HomeContract {
    interface Model extends IModel {
        Observable<BannerBean> requestBanner();
        Observable<MainArticleBean> requestArticle(int page,boolean isSave);
        Observable<TopArticleBean> requestTopArticle();
    }

    interface View extends IView {
        void setArticle(MainArticleBean.DataBean dataBean,boolean isRefresh);
        void setBanner(BannerBean banner,boolean isRefresh);
    }

    interface Presenter extends IPresenter {
        void requestArticle(int page,boolean isSave);
        void requestBanner(boolean isrefresh);
        void requestTopArticle();
    }
}
