package com.robin.rbase.CommonBase.Lifecycle;

import android.content.Context;
import android.os.Bundle;

import android.view.View;

import com.robin.rbase.CommonBase.Cache.Cache;
import com.robin.rbase.CommonBase.Cache.IntelligentCache;
import com.robin.rbase.CommonBase.delegate.FragmentDelegate;
import com.robin.rbase.CommonBase.delegate.IFragment;
import com.robin.rbase.CommonBase.delegate.Impl.FragmentDelegateImpl;
import com.robin.rbase.CommonUtils.Utils.Preconditions;
import com.robin.rbase.MVP.lifecycle.ActivityLifecycleForRxLifecycle;
import com.robin.rbase.MVP.lifecycle.FragmentLifecycleForRxLifecycle;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

/**
 * ================================================
 * {@link FragmentManager.FragmentLifecycleCallbacks} 默认实现类
 * 通过 {@link FragmentDelegate} 管理 {@link Fragment}
 * ================================================
 */
@Singleton
public class FragmentLifecycle extends FragmentManager.FragmentLifecycleCallbacks {

    private static volatile FragmentLifecycle sFragmentLifecycle;

    @Inject
    public FragmentLifecycle() {
    }

    public static FragmentLifecycle getFragmentLifecycle() {
        if (sFragmentLifecycle == null) {
            synchronized (FragmentLifecycle.class) {
                if (sFragmentLifecycle == null) {
                    sFragmentLifecycle = new FragmentLifecycle();
                }
            }
        }
        return sFragmentLifecycle;
    }

    @Override
    public void onFragmentAttached(FragmentManager fm, Fragment f, Context context) {
        if (f instanceof IFragment) {
            FragmentDelegate fragmentDelegate = fetchFragmentDelegate(f);
            if (fragmentDelegate == null || !fragmentDelegate.isAdded()) {
                Cache<String, Object> cache = getCacheFromFragment((IFragment) f);
                fragmentDelegate = new FragmentDelegateImpl(fm, f);
                //使用 IntelligentCache.KEY_KEEP 作为 key 的前缀, 可以使储存的数据永久存储在内存中
                //否则存储在 LRU 算法的存储空间中, 前提是 Fragment 使用的是 IntelligentCache (框架默认使用)
                cache.put(IntelligentCache.getKeyOfKeep(FragmentDelegate.FRAGMENT_DELEGATE), fragmentDelegate);

            }
            fragmentDelegate.onAttach(context);
        }
    }

    @Override
    public void onFragmentCreated(FragmentManager fm, Fragment f, Bundle savedInstanceState) {
        FragmentDelegate fragmentDelegate = fetchFragmentDelegate(f);
        if (fragmentDelegate != null) {
            fragmentDelegate.onCreate(savedInstanceState);
        }
    }

    @Override
    public void onFragmentActivityCreated(FragmentManager fm, Fragment f, Bundle savedInstanceState) {
        FragmentDelegate fragmentDelegate = fetchFragmentDelegate(f);
        if (fragmentDelegate != null) {
            fragmentDelegate.onActivityCreate(savedInstanceState);
        }
    }

    @Override
    public void onFragmentViewCreated(FragmentManager fm, Fragment f, View v, Bundle savedInstanceState) {
        FragmentDelegate fragmentDelegate = fetchFragmentDelegate(f);
        if (fragmentDelegate != null) {
            fragmentDelegate.onCreateView(v, savedInstanceState);
        }
    }

    @Override
    public void onFragmentStarted(FragmentManager fm, Fragment f) {
        FragmentDelegate fragmentDelegate = fetchFragmentDelegate(f);
        if (fragmentDelegate != null) {
            fragmentDelegate.onStart();
        }
    }

    @Override
    public void onFragmentResumed(FragmentManager fm, Fragment f) {
        FragmentDelegate fragmentDelegate = fetchFragmentDelegate(f);
        if (fragmentDelegate != null) {
            fragmentDelegate.onResume();
        }
    }

    @Override
    public void onFragmentPaused(FragmentManager fm, Fragment f) {
        FragmentDelegate fragmentDelegate = fetchFragmentDelegate(f);
        if (fragmentDelegate != null) {
            fragmentDelegate.onPause();
        }
    }

    @Override
    public void onFragmentStopped(FragmentManager fm, Fragment f) {
        FragmentDelegate fragmentDelegate = fetchFragmentDelegate(f);
        if (fragmentDelegate != null) {
            fragmentDelegate.onStop();
        }
    }

    @Override
    public void onFragmentSaveInstanceState(FragmentManager fm, Fragment f, Bundle outState) {
        FragmentDelegate fragmentDelegate = fetchFragmentDelegate(f);
        if (fragmentDelegate != null) {
            fragmentDelegate.onSaveInstanceState(outState);
        }
    }

    @Override
    public void onFragmentViewDestroyed(FragmentManager fm, Fragment f) {
        FragmentDelegate fragmentDelegate = fetchFragmentDelegate(f);
        if (fragmentDelegate != null) {
            fragmentDelegate.onDestroyView();
        }
    }

    @Override
    public void onFragmentDestroyed(FragmentManager fm, Fragment f) {
        FragmentDelegate fragmentDelegate = fetchFragmentDelegate(f);
        if (fragmentDelegate != null) {
            fragmentDelegate.onDestroy();
        }
    }

    private FragmentDelegate fetchFragmentDelegate(Fragment fragment) {
        if (fragment instanceof IFragment) {
            Cache<String, Object> cache = getCacheFromFragment((IFragment) fragment);
            return (FragmentDelegate) cache.get(IntelligentCache.getKeyOfKeep(FragmentDelegate.FRAGMENT_DELEGATE));
        }
        return null;
    }

    @NonNull
    private Cache<String, Object> getCacheFromFragment(IFragment fragment) {
        Cache<String, Object> cache = fragment.provideCache();
        Preconditions.checkNotNull(cache, "%s cannot be null on Fragment", Cache.class.getName());
        return cache;
    }


}
