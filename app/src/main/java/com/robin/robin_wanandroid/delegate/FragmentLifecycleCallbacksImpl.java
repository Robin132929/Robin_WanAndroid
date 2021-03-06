package com.robin.robin_wanandroid.delegate;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.robin.rbase.CommonBase.Fragment.BaseFragment;
import com.robin.rbase.CommonUtils.Logger.Logger;
import com.robin.rbase.MVP.MvpBase.BaseMvpFragment;
import com.robin.robin_wanandroid.util.FragmentPageManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class FragmentLifecycleCallbacksImpl extends FragmentManager.FragmentLifecycleCallbacks {
   public static FragmentPageManager sFragmentPageManager;

    public FragmentLifecycleCallbacksImpl() {
        super();
        sFragmentPageManager = FragmentPageManager.getFragmentPageManager();
    }

    @Override
    public void onFragmentPreAttached(@NonNull FragmentManager fm, @NonNull Fragment f, @NonNull Context context) {
        super.onFragmentPreAttached(fm, f, context);
        Logger.i("FragmentLifecycleCallbacksImpl: \n" +
                "Name :" + f.getClass().getSimpleName() +"Action :onFragmentPreAttached \n" +
                "Time :" + System.currentTimeMillis());

    }

    @Override
    public void onFragmentAttached(@NonNull FragmentManager fm, @NonNull Fragment f, @NonNull Context context) {
        super.onFragmentAttached(fm, f, context);
        Logger.i("FragmentLifecycleCallbacksImpl: \n" +
                "Name :" + f.getClass().getSimpleName() +
                "Action :onFragmentAttached \n" +
                "Time :" + System.currentTimeMillis());
    }

    @Override
    public void onFragmentPreCreated(@NonNull FragmentManager fm, @NonNull Fragment f, @Nullable Bundle savedInstanceState) {
        super.onFragmentPreCreated(fm, f, savedInstanceState);
        Logger.i("FragmentLifecycleCallbacksImpl: \n" +
                "Name :" + f.getClass().getSimpleName() +
                "Action :onFragmentPreCreated \n" +
                "Time :" + System.currentTimeMillis());
    }

    @Override
    public void onFragmentCreated(@NonNull FragmentManager fm, @NonNull Fragment f, @Nullable Bundle savedInstanceState) {
        super.onFragmentCreated(fm, f, savedInstanceState);
        Logger.i("FragmentLifecycleCallbacksImpl111: " +
                "Name :" + f.getClass().getSimpleName() +
                "Action :onFragmentCreated \n" +
                "Time :" + System.currentTimeMillis());
        sFragmentPageManager.addFragment(f);
    }

    @Override
    public void onFragmentActivityCreated(@NonNull FragmentManager fm, @NonNull Fragment f, @Nullable Bundle savedInstanceState) {
        super.onFragmentActivityCreated(fm, f, savedInstanceState);
        Logger.i("FragmentLifecycleCallbacksImpl: \n" +
                "Name :" + f.getClass().getSimpleName()  +
                "Action :onFragmentActivityCreated \n" +
                "Time :" + System.currentTimeMillis());

    }

    @Override
    public void onFragmentViewCreated(@NonNull FragmentManager fm, @NonNull Fragment f, @NonNull View v, @Nullable Bundle savedInstanceState) {
        super.onFragmentViewCreated(fm, f, v, savedInstanceState);
        Logger.i("FragmentLifecycleCallbacksImpl: \n" +
                "Name :" + f.getClass().getSimpleName() +
                "Action :onFragmentViewCreated \n" +
                "Time :" + System.currentTimeMillis());
    }

    @Override
    public void onFragmentStarted(@NonNull FragmentManager fm, @NonNull Fragment f) {
        super.onFragmentStarted(fm, f);
        Logger.i("FragmentLifecycleCallbacksImpl: \n" +
                "Name :" + f.getClass().getSimpleName()  +
                "Action :onFragmentStarted \n" +
                "Time :" + System.currentTimeMillis());
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onFragmentResumed(@NonNull FragmentManager fm, @NonNull Fragment f) {
        super.onFragmentResumed(fm, f);
        Logger.i("FragmentLifecycleCallbacksImpl: \n" +
                "Name :" + f.getClass().getSimpleName()  +
                "Action :onFragmentResumed \n" +
                "Time :" + System.currentTimeMillis());
        if (f instanceof BaseFragment &&f.getUserVisibleHint()){
            sFragmentPageManager.setCurrentFragment(f);
        }
    }

    @Override
    public void onFragmentPaused(@NonNull FragmentManager fm, @NonNull Fragment f) {
        super.onFragmentPaused(fm, f);
        Logger.i("FragmentLifecycleCallbacksImpl: \n" +
                "Name :" + f.getClass().getSimpleName()  +
                "Action :onFragmentPaused \n" +
                "Time :" + System.currentTimeMillis());
    }

    @Override
    public void onFragmentStopped(@NonNull FragmentManager fm, @NonNull Fragment f) {
        super.onFragmentStopped(fm, f);
        Logger.i("FragmentLifecycleCallbacksImpl: \n" +
                "Name :" + f.getClass().getSimpleName()  +
                "Action :onFragmentAttached \n" +
                "Time :" + System.currentTimeMillis());
    }

    @Override
    public void onFragmentSaveInstanceState(@NonNull FragmentManager fm, @NonNull Fragment f, @NonNull Bundle outState) {
        super.onFragmentSaveInstanceState(fm, f, outState);
        Logger.i("FragmentLifecycleCallbacksImpl: \n" +
                "Name :" + f.getClass().getSimpleName() + "\n" +
                "Action :onFragmentStopped \n" +
                "Time :" + System.currentTimeMillis());
    }

    @Override
    public void onFragmentViewDestroyed(@NonNull FragmentManager fm, @NonNull Fragment f) {
        super.onFragmentViewDestroyed(fm, f);
        Logger.i("FragmentLifecycleCallbacksImpl: \n" +
                "Name :" + f.getClass().getSimpleName() + "\n" +
                "Action :onFragmentViewDestroyed \n" +
                "Time :" + System.currentTimeMillis());
    }

    @Override
    public void onFragmentDestroyed(@NonNull FragmentManager fm, @NonNull Fragment f) {
        super.onFragmentDestroyed(fm, f);
        Logger.i("FragmentLifecycleCallbacksImpl: \n" +
                "Name :" + f.getClass().getSimpleName() + "\n" +
                "Action :onFragmentDestroyed \n" +
                "Time :" + System.currentTimeMillis());
        sFragmentPageManager.removeFragment(f);
    }

    @Override
    public void onFragmentDetached(@NonNull FragmentManager fm, @NonNull Fragment f) {
        super.onFragmentDetached(fm, f);
        Logger.i("FragmentLifecycleCallbacksImpl: \n" +
                "Name :" + f.getClass().getSimpleName() + "\n" +
                "Action :onFragmentDetached\n" +
                "Time :" + System.currentTimeMillis());
    }
}
