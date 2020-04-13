package com.robin.robin_wanandroid.customize_interface;

import com.robin.rbase.CommonBase.Fragment.BaseFragment;

public abstract class PageFactory {
    public abstract <T extends BaseFragment> T creatPage(String clz);
}
