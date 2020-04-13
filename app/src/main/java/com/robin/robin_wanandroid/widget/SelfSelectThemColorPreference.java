package com.robin.robin_wanandroid.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.robin.rbase.CommonUtils.Logger.Logger;
import com.robin.robin_wanandroid.R;
import com.robin.robin_wanandroid.util.SettingUtil;

import androidx.cardview.widget.CardView;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;

public class SelfSelectThemColorPreference extends Preference {
    Context context;
    CardView view1;

    public SelfSelectThemColorPreference(Context context) {
        super(context);
        this.context=context;
        setWidgetLayoutResource(R.layout.theme_color_item);
    }

    public SelfSelectThemColorPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        setWidgetLayoutResource(R.layout.theme_color_item);

    }

    public SelfSelectThemColorPreference(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context=context;
        setWidgetLayoutResource(R.layout.theme_color_item);

    }

    public SelfSelectThemColorPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {

        super(context, attrs, defStyleAttr, defStyleRes);
        this.context=context;
        setWidgetLayoutResource(R.layout.theme_color_item);
    }


    @Override
    public void onBindViewHolder(PreferenceViewHolder holder) {


//        view1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                Logger.i("pre111 "+checkedId);
//            }
//        });
        super.onBindViewHolder(holder);
        view1= (CardView) holder.findViewById(R.id.them_color);
if (view1!=null) {
    view1.setCardBackgroundColor(context.getResources().getColor(SettingUtil.getColor()));
}

    }

public void setView(){
        view1.setCardBackgroundColor(context.getResources().getColor(SettingUtil.getColor()));
}

}
