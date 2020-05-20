package com.robin.robin_wanandroid.mvp.contract.wanandroid;

import com.robin.rbase.MVP.MvpBase.IPresenter;
import com.robin.rbase.MVP.MvpBase.IView;
import com.robin.robin_wanandroid.mvp.model.bean.KnowledgeArticleBean;
import com.robin.robin_wanandroid.mvp.model.bean.KnowledgeStructureBean;

import java.util.List;

public interface KnowledgeStructureContract {

    interface View extends IView {
        void setStructureList1st(KnowledgeStructureBean bean);

        void setStructureList2st(KnowledgeStructureBean.DataBean dataBean);

        void setStructureItem(KnowledgeArticleBean bean, int page, boolean refresh);
    }

    interface Presenter extends IPresenter {
        void requestStructureList();

        void requestStructureItem(int page, int cid, boolean more);
    }
}
