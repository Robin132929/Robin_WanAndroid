package com.robin.robin_wanandroid.mvp.presenter.wanandroid;

import com.robin.rbase.CommonUtils.Logger.Logger;
import com.robin.rbase.MVP.MvpBase.BasePresenter;
import com.robin.rbase.MVP.utils.RxLifecycleUtils;
import com.robin.robin_wanandroid.app.App;
import com.robin.robin_wanandroid.mvp.contract.wanandroid.HomeContract;
import com.robin.robin_wanandroid.mvp.model.bean.BannerBean;
import com.robin.robin_wanandroid.mvp.model.bean.MainArticleBean;

import javax.inject.Inject;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

public class HomePresenter extends CommonPresenter<HomeContract.Model,HomeContract.View> implements HomeContract.Presenter {

    @Inject
    public HomePresenter(HomeContract.Model model, HomeContract.View rootView) {
        super(model, rootView);
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void onCreate() {
        //打开 App 时自动加载列表
//        requestBanner(false);
//        requestTopArticle();
//        requestArticle(0,false);
    }

    @Override
    public void requestArticle(int page, boolean isSave) {
       mModel.requestArticle(page,isSave)
               .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
               .doOnSubscribe(new Consumer<Disposable>() {
                   @Override
                   public void accept(Disposable disposable) throws Exception {
                       if (page<1) {
                           mView.showLoading();
                       }
                   }
               })
                .compose(RxLifecycleUtils.bindToLifecycle(mView)).subscribe(new ErrorHandleSubscriber<MainArticleBean>(App.getmMyAppComponent().rxErrorHandler()) {
                    @Override
                    public void onNext(MainArticleBean dataBeans) {
                        mView.setArticle(dataBeans.getData(),isSave);
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
    public void requestBanner(boolean isrefresh) {
        mModel.requestBanner().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxLifecycleUtils.bindToLifecycle(mView)).subscribe(new ErrorHandleSubscriber<BannerBean>(App.getmMyAppComponent().rxErrorHandler()) {
            @Override
            public void onNext(BannerBean dataBeans) {
                Logger.i("banner "+dataBeans.getData().get(0).toString());

                mView.setBanner(dataBeans,isrefresh);
            }
        });
    }

    @Override
    public void requestTopArticle() {

    }
}
