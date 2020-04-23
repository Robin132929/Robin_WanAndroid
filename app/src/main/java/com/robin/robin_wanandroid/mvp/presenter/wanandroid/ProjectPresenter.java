package com.robin.robin_wanandroid.mvp.presenter.wanandroid;

import com.robin.rbase.MVP.MvpBase.BasePresenter;
import com.robin.rbase.MVP.utils.RxLifecycleUtils;
import com.robin.robin_wanandroid.app.App;
import com.robin.robin_wanandroid.mvp.contract.wanandroid.ProjectContract;
import com.robin.robin_wanandroid.mvp.model.bean.BannerBean;
import com.robin.robin_wanandroid.mvp.model.bean.ProjectCategoryBean;
import com.robin.robin_wanandroid.mvp.model.bean.ProjectItemBean;
import com.robin.robin_wanandroid.mvp.model.common.DataManager;

import javax.inject.Inject;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

public class ProjectPresenter extends BasePresenter<DataManager,ProjectContract.View> implements ProjectContract.Presenter {
    @Inject
    public ProjectPresenter(DataManager model, ProjectContract.View rootView) {
        super(model, rootView);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    void onCreate() {
        //打开 App 时自动加载列表
        getProjectCategory();
        getProjectitem(1,294,false);
        getBanner();
    }

    @Override
    public void getProjectCategory() {
        mView.showLoading();
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
     mModel.getProjectItem(page, cid).subscribeOn(Schedulers.io())
             .observeOn(AndroidSchedulers.mainThread())
             .compose(RxLifecycleUtils.bindToLifecycle(mView))
             .doOnSubscribe(new Consumer<Disposable>() {
                 @Override
                 public void accept(Disposable disposable) throws Exception {
                     mView.showLoading();
                 }
             })
             .subscribe(new ErrorHandleSubscriber<ProjectItemBean>(App.getmMyAppComponent().rxErrorHandler()) {
                 @Override
                 public void onNext(ProjectItemBean projectItemBean) {
                     mView.setProjectitem(projectItemBean,isRefresh);
                 }

                 @Override
                 public void onComplete() {
                     super.onComplete();
                     mView.hideLoading();
                 }

                 @Override
                 public void onError(Throwable t) {
                     super.onError(t);
                     mView.showError();
                 }
             });
    }

    @Override
    public void getBanner() {
     mModel.getBanner().subscribeOn(Schedulers.io())
             .observeOn(AndroidSchedulers.mainThread())
             .compose(RxLifecycleUtils.bindToLifecycle(mView))
             .subscribe(new ErrorHandleSubscriber<BannerBean>(App.getmMyAppComponent().rxErrorHandler()) {
                 @Override
                 public void onNext(BannerBean bannerBean) {
                     mView.setBanner(bannerBean);
                 }
             });
    }
}
