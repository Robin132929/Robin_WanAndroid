package com.robin.robin_wanandroid.mvp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.robin.rbase.CommonUtils.Logger.Logger;
import com.robin.rbase.MVP.MvpBase.BaseLazyLoadFragment;
import com.robin.rbase.MVP.MvpBase.BaseMvpFragment;
import com.robin.robin_wanandroid.ContentActivity;
import com.robin.robin_wanandroid.R;
import com.robin.robin_wanandroid.adapter.NavgationAdapter;
import com.robin.robin_wanandroid.app.App;
import com.robin.robin_wanandroid.entity.NavgationSection;
import com.robin.robin_wanandroid.mvp.contract.NavgationContract;
import com.robin.robin_wanandroid.mvp.model.bean.NavgationBean;
import com.robin.robin_wanandroid.mvp.presenter.NavgationPresenter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class NavigationFragment extends BaseLazyLoadFragment<NavgationPresenter> implements NavgationContract.View {
    List<NavgationSection> data = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private NavgationAdapter navgationAdapter;

    public NavigationFragment() {
        // Required empty public constructor
    }

    @Override
    public View initView(@NonNull View view, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRecyclerView = view.findViewById(R.id.navgation_rv);
        mSwipeRefreshLayout = view.findViewById(R.id.nav_swipe_refresh_layout);
        return view;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 4);
        gridLayoutManager.setOrientation(RecyclerView.VERTICAL);
        mRecyclerView.setLayoutManager(gridLayoutManager);

        navgationAdapter = new NavgationAdapter(R.layout.item_section_content, R.layout.navgation_section_head, data);
        navgationAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                NavgationSection nav = data.get(position);
                if (!nav.isHeader) {
                    ContentActivity.startActivity(App.getmMyAppComponent().application(), nav.t.getLink());
                }
            }
        });

        mRecyclerView.setAdapter(navgationAdapter);

    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_navgation;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

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

    @Override
    protected void lazyLoadData() {
        Logger.i(" lazyLoadData:");
        mPresenter.requestNavgationData();
    }
}
