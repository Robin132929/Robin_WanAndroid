package com.robin.robin_wanandroid.factory;

import com.robin.rbase.CommonBase.Fragment.BaseFragment;
import com.robin.rbase.MVP.MvpBase.BasePresenter;

import java.lang.reflect.Constructor;

public class NavigationFragmentFactory {
    public static <T extends BaseFragment> T create(Class clz, Object... args){
        try {
            Constructor constructor = clz.getDeclaredConstructor(BasePresenter.class);
            constructor.setAccessible(true);
            return (T) constructor.newInstance(args);
        } catch (Exception e) {
        }
        return null;
    }
}
