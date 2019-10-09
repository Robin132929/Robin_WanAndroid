package com.robin.robin_wanandroid.util.flowView;

import android.content.Context;

import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;


import com.google.android.flexbox.FlexboxLayoutManager;
import com.robin.robin_wanandroid.R;

import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FlowLayout extends LinearLayout {
        private LinearLayout flow_down_tView;
    private View flow_up_tView;
    RecyclerView downRecycle;
    RecyclerView upRecycle;
    ImageButton downImageButton;
    ImageButton upImageButton;
    List<String> data;
    FlowAdapter flowAdapter;
    Context context;
    private boolean first=true;
    public FlowLayout(Context context) {
        super(context);
        initView(context);

    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);

    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        this.context=context;
        flow_up_tView = inflate(context, R.layout.flow_up_layout, null);
        addView(flow_up_tView);
        upRecycle=flow_up_tView.findViewById(R.id.up_recycle);
        upImageButton=flow_up_tView.findViewById(R.id.iv_arrow_up);
        upImageButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                flow_up_tView.setVisibility(GONE);
                if (first) {
                    first=false;
                    addView(flow_down_tView);
                    GridLayoutManager linearLayoutManager = new GridLayoutManager(context, 8);
//                linearLayoutManager.setOrientation(RecyclerView.VERTICAL);

                    downRecycle.setLayoutManager(linearLayoutManager);
                    FlowAdapter flowAdapter = new FlowAdapter(context, data);
                    downRecycle.setAdapter(flowAdapter);
                }else {
                    flow_down_tView.setVisibility(VISIBLE);
                }

            }
        });

        FlexboxLayoutManager flexboxLayoutManager=new FlexboxLayoutManager(context);

        flow_down_tView = (LinearLayout) inflate(context, R.layout.flow_down_layout, null);
        LinearLayout.LayoutParams line_lp=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        flow_down_tView.setLayoutParams(line_lp);
        downRecycle=flow_down_tView.findViewById(R.id.down_recycle);
        downImageButton=flow_down_tView.findViewById(R.id.iv_arrow_down);
        downImageButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                flow_down_tView.setVisibility(GONE);
                flow_up_tView.setVisibility(VISIBLE);

            }
        });


        LinearLayoutManager linearLayoutManager1=new LinearLayoutManager(context);
        linearLayoutManager1.setOrientation(RecyclerView.HORIZONTAL);
        upRecycle.setLayoutManager(linearLayoutManager1);

    }

    public void setData(List<String> data){
        this.data=data;
        flowAdapter=new FlowAdapter(context,data);
        upRecycle.setAdapter(flowAdapter);


    }

}
