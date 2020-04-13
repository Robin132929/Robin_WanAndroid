package com.robin.robin_wanandroid.adapter;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.robin.rbase.CommonUtils.Logger.Logger;
import com.robin.robin_wanandroid.R;
import com.robin.robin_wanandroid.app.App;
import com.robin.robin_wanandroid.entity.ThemeColorBean;
import com.robin.robin_wanandroid.util.SettingUtil;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class ThemeColorAdapter extends BaseQuickAdapter<ThemeColorBean, BaseViewHolder> {
    List<ThemeColorBean> data;


    public ThemeColorAdapter(@Nullable List<ThemeColorBean> data) {
        super(R.layout.them_test, data);
            this.data = data;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, ThemeColorBean item) {
        CardView img_color = helper.getView(R.id.img_color);
        img_color.setCardBackgroundColor(App.getmMyAppComponent().application().getResources().getColor(item.getColorInt()));

        TextView tv_colorname = (TextView) helper.getView(R.id.tv_colorname);
        tv_colorname.setText(item.getColorName());

        if (item.isSelect()){
            helper.getView(R.id.img_select).setVisibility(View.VISIBLE);

        }

//       helper.getView(R.id.color_name).setOnClickListener(new View.OnClickListener() {
//           @Override
//           public void onClick(View v) {
//               BaseViewHolder hodler= (BaseViewHolder) mRecyclerView.findViewHolderForLayoutPosition(mSelect);
//               if (hodler!=null){
//                   hodler.getView(R.id.img_select).setVisibility(View.GONE);
//               }else {
//                   notifyItemChanged(mSelect);
//               }
//               data.get(mSelect).setSelect(false);
//
//               mSelect=helper.getAdapterPosition();
//               data.get(mSelect).setSelect(true);
//               helper.getView(R.id.img_select).setVisibility(View.VISIBLE);
//               helper.addOnClickListener(R.id.color_name);
//           }
//       });
    }
}
