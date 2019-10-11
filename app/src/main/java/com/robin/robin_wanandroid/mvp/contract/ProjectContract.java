package com.robin.robin_wanandroid.mvp.contract;

import com.robin.rbase.MVP.MvpBase.IModel;
import com.robin.rbase.MVP.MvpBase.IPresenter;
import com.robin.rbase.MVP.MvpBase.IView;
import com.robin.robin_wanandroid.mvp.model.bean.BannerBean;
import com.robin.robin_wanandroid.mvp.model.bean.ProjectCategoryBean;
import com.robin.robin_wanandroid.mvp.model.bean.ProjectItemBean;

import io.reactivex.Observable;


public interface ProjectContract {
    interface Model extends IModel {
        Observable<ProjectCategoryBean> getProjectCategory();
        Observable<ProjectItemBean> getProjectitem(int page,int cid,boolean isRefresh);
        Observable<BannerBean> getBanner();
    }

    interface View extends IView {
        void setProjectCategory(ProjectCategoryBean bean);
        void setProjectitem(ProjectItemBean bean,boolean isRefresh);
        void setBanner(BannerBean banner);
    }

    interface Presenter extends IPresenter {
        void getProjectCategory();
        void getProjectitem(int page,int cid,boolean isRefresh);
        void getBanner();
    }
}
