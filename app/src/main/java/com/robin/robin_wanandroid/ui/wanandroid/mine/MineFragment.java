package com.robin.robin_wanandroid.ui.wanandroid.mine;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.robin.rbase.CommonBase.Fragment.BaseFragment;
import com.robin.robin_wanandroid.R;
import com.robin.robin_wanandroid.adapter.wanandroid.MineAdapter;
import com.robin.robin_wanandroid.mvp.model.bean.MineItemBean;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class MineFragment extends BaseFragment {

    @BindView(R.id.tv_accumulate_points)
    TextView tvAccumulatePoints;
    @BindView(R.id.tv_level)
    TextView tvLevel;
    @BindView(R.id.tv_rank)
    TextView tvRank;
    @BindView(R.id.rv_mine)
    RecyclerView rvMine;

    private BaseQuickAdapter adapter;
    private List<MineItemBean> data=new ArrayList<>();
    @Override
    public void initView(@NonNull View view, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       adapter=new MineAdapter(R.layout.item_mine_recycle,R.layout.navgation_section_head,data);
        LinearLayoutManager layoutManager=new LinearLayoutManager(mContext);
        rvMine.setLayoutManager(layoutManager);
        rvMine.setAdapter(adapter);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        data.add(new MineItemBean(R.drawable.wenda,"test     ",R.drawable.ic_arrow_right_24dp,false));
        data.add(new MineItemBean(R.drawable.wenda,"test     ",R.drawable.ic_arrow_right_24dp,false));
        data.add(new MineItemBean(R.drawable.wenda,"test     ",R.drawable.ic_arrow_right_24dp,false));
        data.add(new MineItemBean(R.drawable.wenda,"     ",R.drawable.ic_arrow_right_24dp,true));

        data.add(new MineItemBean(R.drawable.wenda,"test     ",R.drawable.ic_arrow_right_24dp,false));
        data.add(new MineItemBean(R.drawable.wenda,"test     ",R.drawable.ic_arrow_right_24dp,false));
        data.add(new MineItemBean(R.drawable.wenda,"test     ",R.drawable.ic_arrow_right_24dp,false));

        adapter.notifyDataSetChanged();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine;
    }
}
