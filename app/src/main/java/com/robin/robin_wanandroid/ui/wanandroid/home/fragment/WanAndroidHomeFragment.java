package com.robin.robin_wanandroid.ui.wanandroid.home.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.robin.rbase.CommonUtils.Logger.Logger;
import com.robin.robin_wanandroid.R;
import com.robin.robin_wanandroid.adapter.wanandroid.HomeAdapter;
import com.robin.robin_wanandroid.annotation.CheckLogin;
import com.robin.robin_wanandroid.app.App;
import com.robin.robin_wanandroid.base.RobinBaseFragment;
import com.robin.robin_wanandroid.customize_interface.ScrollTopListener;
import com.robin.robin_wanandroid.mvp.contract.wanandroid.HomeContract;
import com.robin.robin_wanandroid.mvp.model.bean.MainArticleBean;
import com.robin.robin_wanandroid.mvp.presenter.wanandroid.HomePresenter;
import com.robin.robin_wanandroid.rx.FootPrintEvent;
import com.robin.robin_wanandroid.rx.RxBus;
import com.robin.robin_wanandroid.ui.content.ContentActivity;
import com.robin.robin_wanandroid.util.loading.Gloading;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import io.reactivex.functions.Consumer;

public class WanAndroidHomeFragment extends RobinBaseFragment<HomePresenter>
        implements HomeContract.View, ScrollTopListener {

    private static int index = 0;
    private LinearLayoutManager linearLayoutManager;
    @BindView(R.id.home_rv)
    RecyclerView mRecyclerView;
    private BaseQuickAdapter mHomeAdapter;

    public WanAndroidHomeFragment() {
        // Required empty public constructor
    }

    public static WanAndroidHomeFragment newInstance() {
        WanAndroidHomeFragment fragment = new WanAndroidHomeFragment();
        return fragment;
    }

    @Override
    public void initView(@NonNull View view, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mHomeAdapter = new HomeAdapter(R.layout.home_recycle_item);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(linearLayoutManager);
//        mHomeAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        mRecyclerView.setAdapter(mHomeAdapter);

        mHomeAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MainArticleBean.DataBean.DatasBean data =
                        (MainArticleBean.DataBean.DatasBean) adapter.getItem(position);
                ContentActivity.startActivity(
                        App.getmMyAppComponent().application(), data.getTitle(), data.getLink());
            }
        });
        mHomeAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Logger.i("iscollect : click ");
                MainArticleBean.DataBean.DatasBean bean =
                        (MainArticleBean.DataBean.DatasBean) adapter.getItem(position);
                if (view.getId() == R.id.iv_like) {
                    collectAction(position, bean);
                }
            }
        });
        //todo 加载更多
//        mHomeAdapter.addLoadMoreModule()setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
//            @Override
//            public void onLoadMoreRequested() {
//                mPresenter.requestArticle(index++, true);
//            }
//        }, mRecyclerView);

        RxBusSubscriber();
    }

    @CheckLogin(action = CheckLogin.Action.JUMP)
    private void collectAction(int position, MainArticleBean.DataBean.DatasBean bean) {
        bean.setCollect(!bean.isCollect());
        mHomeAdapter.setData(position, bean);
        Logger.i("iscollect " + bean.isCollect());
        if (bean.isCollect()) {
            mPresenter.addCollectArticle(bean.getId());
        } else {
            mPresenter.cancelCollectArticle(bean.getId());
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void showLoading() {
        showLoadingView();
    }

    @Override
    public void hideLoading() {
        showLoadSuccess();
    }

    @Override
    public void showError() {
        showLoadFailed();
    }

    @Override
    public void setArticle(MainArticleBean.DataBean dataBean, boolean isRefresh) {
        index = dataBean.getCurPage();
        if (isRefresh && dataBean.isOver()) {
//            mHomeAdapter.loadMoreEnd();
        }
        if (!isRefresh) {
            mHomeAdapter.setNewData(dataBean.getDatas());
        }
        mHomeAdapter.addData(dataBean.getDatas());
//        mHomeAdapter.loadMoreComplete();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void scroll2Top() {
        if (linearLayoutManager.findFirstVisibleItemPosition() > 20) {
            mRecyclerView.scrollToPosition(0);
        } else {
            mRecyclerView.smoothScrollToPosition(0);
        }
    }

    @SuppressLint("CheckResult")
    public void RxBusSubscriber() {
        RxBus.getInstance().toObservable(this, FootPrintEvent.class)
                .subscribe(new Consumer<FootPrintEvent>() {
            @Override
            public void accept(FootPrintEvent footPrintEvent) throws Exception {
                Logger.i(" rxbus is get the event");
                mPresenter.addFootPrint(footPrintEvent.title, footPrintEvent.url);
            }
        });

    }

    @Override
    protected void initLoadingStatusViewIfNeed() {
        if (mHolder == null) {
            //bind status view to activity root view by default
            mHolder = Gloading.getDefault().wrap(mRecyclerView).withRetry(this::onLoadRetry);
        }
    }

    @Override
    protected void onLoadRetry() {

    }

    @Override
    protected void lazyLoad() {
        super.lazyLoad();
        showLoading();
        mPresenter.requestArticle(0,true);
    }
}
