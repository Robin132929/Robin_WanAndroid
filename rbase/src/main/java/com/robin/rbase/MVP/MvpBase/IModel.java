package com.robin.rbase.MVP.MvpBase;

public interface IModel {
    /**
     * 在框架中 {@link BasePresenter#onDetach()} 时会默认调用 {@link IModel#onDetach()}
     */
    void onDetach();
}
