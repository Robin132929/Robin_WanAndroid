package com.robin.robin_wanandroid.widget;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.robin.rbase.CommonUtils.Logger.Logger;
import com.robin.robin_wanandroid.R;
import com.robin.robin_wanandroid.adapter.ThemeColorAdapter;
import com.robin.robin_wanandroid.entity.ThemeColorBean;
import com.robin.robin_wanandroid.rx.ColorEvent;
import com.robin.robin_wanandroid.rx.RxBus;
import com.robin.robin_wanandroid.util.SettingUtil;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ColorDialogFragment extends DialogFragment {
    static int mSelect = -1;
    ThemeColorAdapter themeColorAdapter;
    View view;
    private RecyclerView mRecyclerView;
    private List<ThemeColorBean> color_data;
    private static int lastColor=SettingUtil.getColor();

    public ColorDialogFragment(List<ThemeColorBean> color_data) {
        this.color_data = color_data;
        if (mSelect==-1){
            for (int i = 0; i < color_data.size(); i++) {
               if (this.color_data.get(i).getColorInt()==lastColor){
                   this.color_data.get(i).setSelect(true);
                   mSelect=i;
               }
            }
        }
        Logger.i(" mSelect is "+mSelect);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Logger.i("post button which   onDetach ");

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Logger.i("post button which   onCreateDialog ");

        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.fragment_select_color_layout, null);
        themeColorAdapter = new ThemeColorAdapter(color_data);
        mRecyclerView = view.findViewById(R.id.recycle);
//        themeColorAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                Logger.i("post button which 444 " + position);
//                BaseViewHolder hodler = (BaseViewHolder) mRecyclerView.findViewHolderForLayoutPosition(mSelect);
//                if (hodler != null) {
//                    hodler.getView(R.id.img_select).setVisibility(View.GONE);
//                } else {
//                    adapter.notifyItemChanged(mSelect);
//                }
//                color_data.get(mSelect).setSelect(false);
//
//                mSelect = position;
//                color_data.get(mSelect).setSelect(true);
//                view.findViewById(R.id.img_select).setVisibility(View.VISIBLE);
//                SettingUtil.setColor(color_data.get(position).getColorInt());
//
//                RxBus.getInstance().post(new ColorEvent(color_data.get(position).getColorName(), color_data.get(position).getColorInt()));
//
//            }
//        });
        if (mRecyclerView.getLayoutManager() == null) {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        }

        mRecyclerView.setAdapter(themeColorAdapter);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("选择颜色").setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
            }
        }).setView(view).setPositiveButton("确认", null);

        return builder.create();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mRecyclerView=null;
        themeColorAdapter=null;
        view=null;
        color_data=null;
    }
}
