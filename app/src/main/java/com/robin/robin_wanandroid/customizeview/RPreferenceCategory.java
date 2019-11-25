package com.robin.robin_wanandroid.customizeview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.robin.robin_wanandroid.R;

import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceViewHolder;

public class RPreferenceCategory extends PreferenceCategory {
    public RPreferenceCategory(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public RPreferenceCategory(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public RPreferenceCategory(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RPreferenceCategory(Context context) {
        super(context);
    }

    @Override
    public void onBindViewHolder(PreferenceViewHolder holder) {
        super.onBindViewHolder(holder);
    TextView title= (TextView) holder.findViewById(android.R.id.title);
    title.setTextColor(getContext().getResources().getColor(R.color.colorAccent));
    title.setTextSize(14);

    }
}
