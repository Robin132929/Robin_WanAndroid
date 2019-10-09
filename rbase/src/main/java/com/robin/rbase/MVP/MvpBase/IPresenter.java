package com.robin.rbase.MVP.MvpBase;

public interface IPresenter{
    /**
     * 绑定 View
     *
     */
    void onAttach();

    /**
     * 解绑 View
     */
    void onDetach();
}
