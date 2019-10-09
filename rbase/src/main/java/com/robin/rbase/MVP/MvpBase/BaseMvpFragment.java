package com.robin.rbase.MVP.MvpBase;

import android.content.Context;

import com.robin.rbase.CommonBase.Cache.Cache;
import com.robin.rbase.CommonBase.Cache.CacheType;
import com.robin.rbase.CommonBase.Fragment.BaseFragment;
import com.robin.rbase.MVP.lifecycle.FragmentLifecycleable;
import com.trello.rxlifecycle2.android.FragmentEvent;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import dagger.android.support.AndroidSupportInjection;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;

public abstract class BaseMvpFragment<P extends IPresenter> extends BaseFragment implements FragmentLifecycleable {
    private final BehaviorSubject<FragmentEvent> mLifecycleSubject = BehaviorSubject.create();
    protected Context mContext;
    @Inject
    @Nullable
    protected P mPresenter;//如果当前页面逻辑简单, Presenter 可以为 null
    @Inject
    Cache.Factory mCachefactory;
    private Cache<String, Object> mCache;

    @NonNull
    @Override
    public final Subject<FragmentEvent> provideLifecycleSubject() {
        return mLifecycleSubject;
    }

    @NonNull
    @Override
    public synchronized Cache<String, Object> provideCache() {
        if (mCache == null) {
//            mCache = ArmsUtils.obtainAppComponentFromContext(getActivity()).cacheFactory().build(CacheType.FRAGMENT_CACHE);
            mCache = mCachefactory.build(CacheType.ACTIVITY_CACHE);

        }
        return mCache;
    }

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mContext = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //释放资源
        if (mPresenter != null) mPresenter.onDetach();
        this.mPresenter = null;
    }

}
