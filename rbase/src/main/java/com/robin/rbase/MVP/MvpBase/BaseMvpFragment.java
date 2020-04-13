package com.robin.rbase.MVP.MvpBase;

import android.content.Context;

import com.robin.rbase.CommonBase.Cache.Cache;
import com.robin.rbase.CommonBase.Cache.CacheType;
import com.robin.rbase.CommonBase.Fragment.BaseFragment;
import com.robin.rbase.CommonUtils.Logger.Logger;
import com.robin.rbase.MVP.lifecycle.ActivityLifecycleable;
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
    @Inject
    @Nullable
    protected P mPresenter;//如果当前页面逻辑简单, Presenter 可以为 null

    @NonNull
    @Override
    public final Subject<FragmentEvent> provideLifecycleSubject() {
        return mLifecycleSubject;
    }

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //释放资源
        if (mPresenter != null) mPresenter.onDetach();
        this.mPresenter = null;
    }
}
