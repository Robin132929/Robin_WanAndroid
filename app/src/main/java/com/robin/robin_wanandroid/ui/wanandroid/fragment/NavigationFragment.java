package com.robin.robin_wanandroid.ui.wanandroid.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.robin.rbase.CommonUtils.Logger.Logger;
import com.robin.rbase.MVP.MvpBase.BaseMvpFragment;
import com.robin.robin_wanandroid.R;
import com.robin.robin_wanandroid.ui.content.ContentActivity;
import com.robin.robin_wanandroid.adapter.wanandroid.NavgationAdapter;
import com.robin.robin_wanandroid.app.App;
import com.robin.robin_wanandroid.customize_interface.ScrollTopListener;
import com.robin.robin_wanandroid.entity.NavgationSection;
import com.robin.robin_wanandroid.mvp.contract.wanandroid.NavgationContract;
import com.robin.robin_wanandroid.mvp.model.bean.NavgationBean;
import com.robin.robin_wanandroid.mvp.presenter.wanandroid.NavgationPresenter;
import com.robin.robin_wanandroid.util.loading.Gloading;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;

public class NavigationFragment extends BaseMvpFragment<NavgationPresenter> implements NavgationContract.View, ScrollTopListener {
    @Inject
    List<NavgationSection> data ;
    @BindView(R.id.navgation_rv)
    RecyclerView mRecyclerView;
    @BindView(R.id.nav_swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @Inject
    NavgationAdapter navgationAdapter;
    Gloading.Holder holder;
    private static int StartConut=0;

    public NavigationFragment() {
        // Required empty public constructor
        StartConut++;
        Logger.i("start count is "+StartConut);
    }

    @Override
    public void initView(@NonNull View view, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        holder = Gloading.getDefault().wrap(mRecyclerView);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 4);
        gridLayoutManager.setOrientation(RecyclerView.VERTICAL);
        mRecyclerView.setLayoutManager(gridLayoutManager);

//        navgationAdapter = new NavgationAdapter(R.layout.item_section_content, R.layout.navgation_section_head, data);
        navgationAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                NavgationSection nav = data.get(position);
                if (!nav.isHeader) {
                    ContentActivity.startActivity(App.getmMyAppComponent().application(), nav.t.getTitle(), nav.t.getLink());
                }
            }
        });

        mRecyclerView.setAdapter(navgationAdapter);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
//                Gloading.getDefault().wrap(mRecyclerView).showLoadSuccess();
                mPresenter.requestNavgationData(true);
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });

    }


    public void setData(@Nullable Object data) {
//        LoadSir.beginBuilder()
//                .addCallback(new EmptyCallback())
//                .addCallback(new LoadingCallback())
//                .addCallback(new TimeoutCallback())
//                .addCallback(new CustomCallback())
//                .setDefaultCallback(LoadingCallback.class)
//                .commit();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_navgation;
    }

    @Override
    public void showLoading() {
//    loadingView.show();
        holder.showLoading();
    }

    @Override
    public void hideLoading() {
        holder.showLoadSuccess();

//loadingView.dismiss();

    }

    @Override
    public void showError() {
//loadingView.setContentView(LayoutInflater.from(mContext).inflate(R.layout.test_progress, null, false));
        holder.showLoadFailed();
    }

    @Override
    public void setNavgationData(NavgationBean navgationData) {
        List<NavgationBean.DataBean> nav;
        nav = navgationData.getData();
        for (int i = 0; i < nav.size(); i++) {
            int childSize = nav.get(i).getArticles().size();
            data.add(new NavgationSection(true, nav.get(i).getName(), childSize >= 11));
            for (int i1 = 0; i1 < childSize; i1++) {
                if (i1 <= 11) {
                    data.add(new NavgationSection(nav.get(i).getArticles().get(i1)));

                }
            }
        }
        navgationAdapter.setNewData(data);
        navgationAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                NavgationSection nav = data.get(position);
                if (nav.isHeader) {
                    for (int i = 0; i < navgationData.getData().size(); i++) {
                        if (navgationData.getData().get(i).getName().equals(nav.header)) {
                            ContentActivity.startActivity(App.getmMyAppComponent().application(), navgationData.getData().get(i).getArticles());
                        }
                    }
                } else {
                    Intent intent = new Intent(getContext(), ContentActivity.class);
                    intent.putExtra("url", nav.t.getLink());
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        data.clear();
    }
//
//    @Override
//    protected void lazyLoadData() {
//        Logger.i(" lazyLoadData nav: "+this);
//
//        mPresenter.requestNavgationData(false);
//    }

    @Override
    public void scroll2Top() {
        mRecyclerView.scrollToPosition(0);
    }
}
