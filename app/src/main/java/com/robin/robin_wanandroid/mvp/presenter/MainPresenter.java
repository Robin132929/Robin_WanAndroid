package com.robin.robin_wanandroid.mvp.presenter;

import android.content.Context;

import com.robin.rbase.CommonUtils.Logger.Logger;
import com.robin.rbase.MVP.MvpBase.BasePresenter;
import com.robin.rbase.MVP.utils.RxLifecycleUtils;
import com.robin.robin_wanandroid.delegate.AppLifecyclesImpl;
import com.robin.robin_wanandroid.mvp.contract.MainContract;
import com.robin.robin_wanandroid.mvp.model.bean.MainArticleBean;

import javax.inject.Inject;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import me.jessyan.rxerrorhandler.handler.listener.ResponseErrorListener;

public class MainPresenter extends BasePresenter<MainContract.Model,MainContract.View>implements MainContract.Presenter {

    RxErrorHandler mErrorHandler=RxErrorHandler
            .builder()
            .with(AppLifecyclesImpl.getapp())
            .responseErrorListener(new ResponseErrorListener() {
                @Override
                public void handleResponseError(Context context, Throwable t) {

                }
            })
            .build();
//    @Inject
//    MainArticleBean mUsers;
     @Inject
    public MainPresenter(MainContract.View view,MainContract.Model model) {
        super(model,view);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void onCreate() {
        load(0);//打开 App 时自动加载列表
    }
    @Override
    public MainArticleBean load(int page) {
         mModel.getMainArticle(page).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxLifecycleUtils.bindToLifecycle(mView)).subscribe(new ErrorHandleSubscriber<MainArticleBean>(mErrorHandler) {
                    @Override
                    public void onNext(MainArticleBean dataBeans) {
                        Logger.i("test mian :"+dataBeans.toString());
//                        mUsers=dataBeans;
                        mView.setHomeArt(dataBeans.getData());
                    }

             @Override
             public void onError(Throwable t) {
                 super.onError(t);
             }
         });

        return null;
    }

    @Override
    public void logout() {

    }
}
