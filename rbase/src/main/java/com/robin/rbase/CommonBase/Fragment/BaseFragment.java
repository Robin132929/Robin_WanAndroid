package com.robin.rbase.CommonBase.Fragment;

import android.content.Context;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.robin.rbase.CommonBase.Cache.Cache;
import com.robin.rbase.CommonBase.Cache.CacheType;
import com.robin.rbase.CommonBase.delegate.IFragment;
import com.robin.rbase.CommonBase.utils.Const;
import com.robin.rbase.CommonUtils.Logger.Logger;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseFragment extends Fragment implements IFragment {
    protected Context mContext;
    private Cache<String, Object> mCache;
    private Unbinder mUnbinder;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(getLayoutId(), container, false);
        mUnbinder= ButterKnife.bind(this, view);
        initView(view, container, savedInstanceState);
        return view;
    }

    @NonNull
    @Override
    public Cache<String, Object> provideCache() {
        if (mCache==null){
            mCache = Const.getCacheFactory(getContext()).build(CacheType.ACTIVITY_CACHE);
        }
        return mCache;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mUnbinder != null && mUnbinder != Unbinder.EMPTY)
            mUnbinder.unbind();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.mContext = null;

    }

}
