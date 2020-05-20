package com.robin.robin_wanandroid.widget;

import android.content.Context;
import android.view.ViewGroup;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

public  class BaseBottomSheetDialog extends BottomSheetDialog {

    public BaseBottomSheetDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    public void onStart() {
        super.onStart();
        ViewGroup.LayoutParams params=new CoordinatorLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        findViewById(com.google.android.material.R.id.design_bottom_sheet).setLayoutParams(params);
    }

}
