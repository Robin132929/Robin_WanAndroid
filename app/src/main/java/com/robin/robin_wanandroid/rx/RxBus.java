package com.robin.robin_wanandroid.rx;

import com.robin.rbase.MVP.MvpBase.IView;
import com.robin.rbase.MVP.utils.RxLifecycleUtils;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public class RxBus {
    private volatile static RxBus mDefaultInstance;
    private final Subject<Object> mBus;

    private RxBus() {
        mBus = PublishSubject.create().toSerialized();
    }

    public static RxBus getInstance() {
        if (mDefaultInstance == null) {
            synchronized (RxBus.class) {
                if (mDefaultInstance == null) {
                    mDefaultInstance = new RxBus();
                }
            }
        }
        return mDefaultInstance;
    }

    /**
     * 发送事件
     */
    public void post(Object event) {
        mBus.onNext(event);
    }

    /**
     * 使用Rxlifecycle解决RxJava引起的内存泄漏
     */
    public <T> Observable<T> toObservable(IView view, final Class<T> eventType) {
        return mBus.ofType(eventType).compose(RxLifecycleUtils.bindToLifecycle(view));
    }

    /**
     * 判断是否有订阅者
     */
    public boolean hasObservers() {
        return mBus.hasObservers();
    }

    public void reset() {
        mDefaultInstance = null;
    }

}
