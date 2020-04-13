package com.robin.robin_wanandroid.customize_interface;

import com.robin.rbase.CommonBase.Fragment.BaseFragment;
import com.robin.rbase.CommonUtils.Logger.Logger;

public class RealPageFactory extends PageFactory {
    @Override
    public <T extends BaseFragment> T creatPage(String clz) {
        try {
            return (T) Class.forName(clz).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
