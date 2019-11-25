package com.robin.rbase.MVP.MvpBase;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import com.robin.rbase.CommonBase.Activity.BaseActivity;
import com.robin.rbase.CommonBase.Cache.Cache;
import com.robin.rbase.CommonBase.Cache.CacheType;
import com.robin.rbase.CommonUtils.Logger.Logger;
import com.robin.rbase.MVP.lifecycle.ActivityLifecycleable;
import com.trello.rxlifecycle2.android.ActivityEvent;

import javax.inject.Inject;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;

public abstract class BaseMvpActivity<P extends BasePresenter> extends BaseActivity implements ActivityLifecycleable, HasAndroidInjector {
    @Inject
    DispatchingAndroidInjector<Object> androidInjector;

    private final BehaviorSubject<ActivityEvent> mLifecycleSubject = BehaviorSubject.create();
    @Inject
    @Nullable
    protected P mPresenter;//如果当前页面逻辑简单, Presenter 可以为 null
    @Inject
    Cache.Factory mCachefactory;
    private Cache<String, Object> mCache;

    @NonNull
    @Override
    public final Subject<ActivityEvent> provideLifecycleSubject() {
        return mLifecycleSubject;
    }
    @Override
    public AndroidInjector<Object> androidInjector() {
        return androidInjector;
    }
    @NonNull
    @Override
    public synchronized Cache<String, Object> provideCache() {
        if (mCache == null) {
//            mCache = ArmsUtils.obtainAppComponentFromContext(this).cacheFactory().build(CacheType.ACTIVITY_CACHE);
            mCache = mCachefactory.build(CacheType.ACTIVITY_CACHE);
        }
        return mCache;
    }

    /**
     * 这个 {@link Activity} 是否会使用 {@link Fragment}, 框架会根据这个属性判断是否注册 {@link androidx.core.app.FragmentManager.FragmentLifecycleCallbacks}
     * 如果返回 {@code false}, 那意味着这个 {@link Activity} 不需要绑定 {@link Fragment}, 那你再在这个 {@link Activity} 中绑定继承于 {@link BaseMvpFragment} 的 {@link Fragment} 将不起任何作用
     *
     * @return 返回 {@code true} (默认为 {@code true}), 则需要使用 {@link Fragment}
     */
    @Override
    public boolean useFragment() {
        return true;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //一处声明，处处依赖注入
        AndroidInjection.inject(this);
        //TODO
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null)
            mPresenter.onDetach();//释放资源
        this.mPresenter = null;
    }


}
