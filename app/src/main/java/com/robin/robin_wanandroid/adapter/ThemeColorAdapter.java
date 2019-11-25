package com.robin.robin_wanandroid.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.robin.robin_wanandroid.R;
import com.robin.robin_wanandroid.app.App;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ThemeColorAdapter extends BaseQuickAdapter<Integer, BaseViewHolder> {

    public ThemeColorAdapter( @Nullable List<Integer> data) {
        super(R.layout.theme_color_item, data);

    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, Integer item) {
    helper.getView(R.id.img_color).setBackgroundColor(App.getmMyAppComponent().application().getResources().getColor(item));
    }
}
