package com.robin.robin_wanandroid.mvp.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.opengl.Visibility;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.chad.library.adapter.base.layoutmanager.FlowLayoutManager;
import com.robin.robin_wanandroid.R;
import com.robin.robin_wanandroid.adapter.ThemeColorAdapter;
import com.robin.robin_wanandroid.adapter.wanandroid.KnowledgeListAdapter;
import com.robin.robin_wanandroid.rx.ColorEvent;
import com.robin.robin_wanandroid.rx.RxBus;
import com.robin.robin_wanandroid.util.CacheUtil;
import com.robin.robin_wanandroid.util.CacheUtils;
import com.robin.robin_wanandroid.widget.CustomPopupWindow;

import java.util.ArrayList;
import java.util.List;

import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SettingFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {
    CustomPopupWindow mExpandPopupWindow;
    private List<Integer> data=new ArrayList<>();

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.setting_preferences, rootKey);
        setHasOptionsMenu(true);
        findPreference("clearCache").setSummary(CacheUtils.getTotalCacheSize(getContext()));
        View contentView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_select_color_layout, null, false);
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
        mExpandPopupWindow = CustomPopupWindow.builder()
                .contentView(contentView)
                .isFocus(true)
                .setHeight(1000)
                .setWidth(800)
                .customListener(new CustomPopupWindow.CustomPopupWindowListener() {
                    @Override
                    public void initPopupView(View contentView) {
//                       RecyclerView recyclerView= contentView.findViewById(R.id.color_list);
//                       LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
//                       linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
//                       GridLayoutManager gridLayoutManager=new GridLayoutManager(getContext(),2);
//                       gridLayoutManager.setOrientation(RecyclerView.VERTICAL);
//                       recyclerView.setLayoutManager(linearLayoutManager);
//                        ThemeColorAdapter adapter=new ThemeColorAdapter(data);
//
//                        recyclerView.setAdapter(adapter);
                        RadioGroup radioGroup=contentView.findViewById(R.id.color_list);
                        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(RadioGroup group, int checkedId) {
                                RadioButton r=(RadioButton)group.getChildAt(checkedId);
                                RxBus.getInstance().post(new ColorEvent(r.getText().toString(),r.getHighlightColor()));

                            }
                        });
                    }
                }).build();

        try {
            String Version = "当前版本: " + getContext().getPackageManager().getPackageInfo(getContext().getPackageName(), 0).versionName;
            findPreference("version").setSummary(Version);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        findPreference("auto_nightMode").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                return true;
            }
        });
        findPreference("clearCache").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                CacheUtils.clearAllCache(getContext());
                return false;
            }
        });

        findPreference("color").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                mExpandPopupWindow.show();
                return false;
            }
        });
        //other setting
        findPreference("changelog").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/Robin132929/Robin_WanAndroid")));
                return false;
            }
        });
        findPreference("sourceCode").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/Robin132929/Robin_WanAndroid")));
                return false;
            }
        });
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

    }
}
