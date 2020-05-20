package com.robin.robin_wanandroid.ui.wanandroid.explore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.robin.rbase.CommonUtils.Logger.Logger;
import com.robin.rbase.MVP.MvpBase.BaseMvpFragment;
import com.robin.robin_wanandroid.R;
import com.robin.robin_wanandroid.adapter.wanandroid.NodeTreeAdapter;
import com.robin.robin_wanandroid.base.RobinBaseFragment;
import com.robin.robin_wanandroid.ui.content.ContentActivity;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;

public class NavigationFragment extends RobinBaseFragment<NavgationPresenter>
        implements NavgationContract.View, ScrollTopListener {
    private static int StartConut = 0;
    @Inject
    List<NavgationSection> data;
    @BindView(R.id.navgation_rv)
    RecyclerView mRecyclerView;
    NodeTreeAdapter adapter;
//    @Inject
//    NavgationAdapter navgationAdapter;

    public NavigationFragment() {
        // Required empty public constructor
        StartConut++;
        Logger.i("start count is " + StartConut);
    }

    @Override
    public void initView(@NonNull View view, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        adapter=new NodeTreeAdapter();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 4);
        gridLayoutManager.setOrientation(RecyclerView.VERTICAL);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
//        navgationAdapter = new NavgationAdapter(R.layout.item_section_content, R.layout.navgation_section_head, data);
//        navgationAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                NavgationSection nav = data.get(position);
//                if (!nav.isHeader) {
//                    ContentActivity.startActivity(App.getmMyAppComponent().application(),
//                            nav.t.getTitle(), nav.t.getLink());
//                }
//            }
//        });

        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_navgation;
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
//        navgationAdapter.setNewData(data);
//        navgationAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
//            @Override
//            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                NavgationSection nav = data.get(position);
//                if (nav.isHeader) {
//                    for (int i = 0; i < navgationData.getData().size(); i++) {
//                        if (navgationData.getData().get(i).getName().equals(nav.header)) {
//                            ContentActivity.startActivity(App.getmMyAppComponent().application(),
//                                    navgationData.getData().get(i).getArticles());
//                        }
//                    }
//                } else {
//                    Intent intent = new Intent(getContext(), ContentActivity.class);
//                    intent.putExtra("url", nav.t.getLink());
//                    startActivity(intent);
//                }
//            }
//        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        data.clear();
    }

    @Override
    public void scroll2Top() {
        mRecyclerView.scrollToPosition(0);
    }

    @Override
    protected void initLoadingStatusViewIfNeed() {
        if (mHolder == null) {
            //bind status view to activity root view by default
            mHolder = Gloading.getDefault().wrap(mRecyclerView).withRetry(new Runnable() {
                @Override
                public void run() {
                    onLoadRetry();
                }
            });
        }
    }

    @Override
    protected void onLoadRetry() {

    }

    @Override
    protected void lazyLoad() {
        super.lazyLoad();
        mPresenter.requestNavgationData(false);
    }
}
