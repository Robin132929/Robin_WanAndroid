//package com.robin.robin_wanandroid.widget;
//
//import android.app.Dialog;
//import android.content.DialogInterface;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//
//import com.chad.library.adapter.base.BaseQuickAdapter;
//import com.robin.robin_wanandroid.R;
//import com.robin.robin_wanandroid.adapter.ThemeColorAdapter;
//import com.robin.robin_wanandroid.rx.RxBus;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AlertDialog;
//import androidx.fragment.app.DialogFragment;
//import androidx.recyclerview.widget.GridLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//public class ColorDialogFragment extends DialogFragment {
//    private int resID;
//    private boolean dimiss=false;
//    private boolean show=false;
//    private RecyclerView mRecyclerView;
//    private List<Integer> data=new ArrayList<>();
//
//    public ColorDialogFragment(Builder builder) {
//        this.resID=builder.resID;
//        this.dimiss=builder.dimiss;
//        this.show=builder.show;
//
//        data.add(R.color.TURQUOISE);
//        data.add(R.color.EMERALD);
//        data.add(R.color.PETERRIVER);
//        data.add(R.color.AMETHYST);
//        data.add(R.color.WETASPHALT);
//        data.add(R.color.SUNFLOWER);
//        data.add(R.color.CARROT);
//        data.add(R.color.ALIZARIN);
//        data.add(R.color.CLOUDS);
//        data.add(R.color.CONCRETE);
//
//        data.add(R.color.GREENSEA);
//        data.add(R.color.NEPHRITIS);
//        data.add(R.color.BELIZEHOLE);
//        data.add(R.color.WISTERIA);
//        data.add(R.color.MIDNIGHTBLUE);
//        data.add(R.color.ORANGE);
//        data.add(R.color.PUMPKIN);
//        data.add(R.color.POMEGRANATE);
//        data.add(R.color.SILVER);
//        data.add(R.color.ASBESTOS);
//    }
//
//    @NonNull
//    @Override
//    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        // Get the layout inflater
//        LayoutInflater inflater = getActivity().getLayoutInflater();
//        View view = inflater.inflate(R.layout.fragment_select_color_layout, null);
//        mRecyclerView=view.findViewById(R.id.color_list);
//        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),4));
//        ThemeColorAdapter adapter=new ThemeColorAdapter(data);
//
//        mRecyclerView.setAdapter(adapter);
//        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//              int color= (int) adapter.getItem(position);
////                RxBus.getInstance().post(new );
//            }
//        });
//        builder.setTitle("选择颜色");
//        builder.setView(view);
//        return builder.create();
//    }
//
//    public static class Builder{
//        private int resID=0;
//        private boolean dimiss=false;
//        private boolean show=false;
//        public Builder setRes(int id){
//            this.resID=id;
//            return this;
//        }
//
//        public Builder dimiss(boolean dimiss){
//            this.dimiss=dimiss;
//            return this;
//        }
//
//        public Builder show (boolean show){
//            this.show=show;
//            return this;
//        }
//        public ColorDialogFragment build() {
//            return new ColorDialogFragment(this);
//        }
//
//    }
//}
