package com.robin.robin_wanandroid.mvp.presenter;

import com.robin.rbase.MVP.MvpBase.BasePresenter;
import com.robin.rbase.MVP.utils.RxLifecycleUtils;
import com.robin.robin_wanandroid.app.App;
import com.robin.robin_wanandroid.mvp.contract.ProjectContract;
import com.robin.robin_wanandroid.mvp.model.bean.ProjectCategoryBean;
import com.robin.robin_wanandroid.mvp.model.bean.ProjectItemBean;

import javax.inject.Inject;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

public class ProjectPresenter extends BasePresenter<ProjectContract.Model,ProjectContract.View> implements ProjectContract.Presenter {
    @Inject
    public ProjectPresenter(ProjectContract.Model model, ProjectContract.View rootView) {
        super(model, rootView);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void onCreate() {
        //打开 App 时自动加载列表
        getProjectCategory();
        getProjectitem(1,294,false);
    }

    @Override
    public void getProjectCategory() {
     mModel.getProjectCategory().subscribeOn(Schedulers.io())
             .observeOn(AndroidSchedulers.mainThread())
             .compose(RxLifecycleUtils.bindToLifecycle(mView))
             .subscribe(new ErrorHandleSubscriber<ProjectCategoryBean>(App.getmMyAppComponent().rxErrorHandler()) {
                 @Override
                 public void onNext(ProjectCategoryBean projectCategoryBean) {
                     mView.setProjectCategory(projectCategoryBean);
                 }
             });
    }

    @Override
    public void getProjectitem(int page, int cid, boolean isRefresh) {
     mModel.getProjectitem(page, cid, isRefresh).subscribeOn(Schedulers.io())
             .observeOn(AndroidSchedulers.mainThread())
             .compose(RxLifecycleUtils.bindToLifecycle(mView))
             .subscribe(new ErrorHandleSubscriber<ProjectItemBean>(App.getmMyAppComponent().rxErrorHandler()) {
                 @Override
                 public void onNext(ProjectItemBean projectItemBean) {
                     mView.setProjectitem(projectItemBean,isRefresh);
                 }
             });
    }
}
