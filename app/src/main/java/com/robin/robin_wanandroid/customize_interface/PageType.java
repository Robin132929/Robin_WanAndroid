package com.robin.robin_wanandroid.customize_interface;


import com.robin.rbase.MVP.MvpBase.BasePresenter;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;

public interface PageType {
    void initToolbar(ActionBar toolbar);
    void setData(Object data,Object... args);
    void setPresenter(BasePresenter presenter);

}
