package com.robin.robin_wanandroid.factory;

import com.robin.robin_wanandroid.base.RobinBaseFragment;

public class ConvenientEntranceFactory {
    public static  <T extends RobinBaseFragment> T creatFragment(Class cl) {
        try {
            return (T) cl.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
