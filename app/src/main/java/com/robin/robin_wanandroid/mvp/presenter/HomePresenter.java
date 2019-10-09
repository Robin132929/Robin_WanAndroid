package com.robin.robin_wanandroid.mvp.presenter;

import android.content.Context;

import com.robin.rbase.CommonUtils.Logger.Logger;
import com.robin.rbase.MVP.MvpBase.BasePresenter;
import com.robin.rbase.MVP.utils.RxLifecycleUtils;
import com.robin.robin_wanandroid.app.App;
import com.robin.robin_wanandroid.delegate.AppLifecyclesImpl;
import com.robin.robin_wanandroid.mvp.contract.HomeContract;
import com.robin.robin_wanandroid.mvp.model.bean.BannerBean;
import com.robin.robin_wanandroid.mvp.model.bean.MainArticleBean;

import javax.inject.Inject;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import me.jessyan.rxerrorhandler.handler.listener.ResponseErrorListener;

public class HomePresenter extends BasePresenter<HomeContract.Model,HomeContract.View> implements HomeContract.Presenter {
    RxErrorHandler mErrorHandler=RxErrorHandler
            .builder()
            .with(AppLifecyclesImpl.getapp())
            .responseErrorListener(new ResponseErrorListener() {
                @Override
                public void handleResponseError(Context context, Throwable t) {

                }
            })
            .build();

    @Inject
    public HomePresenter(HomeContract.Model model, HomeContract.View rootView) {
        super(model, rootView);
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void onCreate() {
        //打开 App 时自动加载列表
        requestBanner(false);
        requestTopArticle();
        requestArticle(0,true);
    }

    @Override
    public void requestArticle(int page, boolean isSave) {
       mModel.requestArticle(page,isSave).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxLifecycleUtils.bindToLifecycle(mView)).subscribe(new ErrorHandleSubscriber<MainArticleBean>(App.getmMyAppComponent().rxErrorHandler()) {
                    @Override
                    public void onNext(MainArticleBean dataBeans) {
                        Logger.i("test mian :"+dataBeans.toString());

                        mView.setArticle(dataBeans.getData());
                    }

           @Override
           public void onComplete() {
               super.onComplete();
               mView.hideLoading();
           }
       });
    }

    @Override
    public void requestBanner(boolean isrefresh) {
        mModel.requestBanner().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxLifecycleUtils.bindToLifecycle(mView)).subscribe(new ErrorHandleSubscriber<BannerBean>(mErrorHandler) {
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
