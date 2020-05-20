package com.robin.robin_wanandroid.ui.wanandroid.mine;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import com.robin.rbase.CommonUtils.Logger.Logger;
import com.robin.rbase.MVP.MvpBase.BasePresenter;
import com.robin.robin_wanandroid.R;
import com.robin.robin_wanandroid.customize_interface.PageType;
import com.robin.robin_wanandroid.entity.ThemeColorBean;
import com.robin.robin_wanandroid.util.CacheUtils;
import com.robin.robin_wanandroid.widget.ColorDialogFragment;
import com.robin.robin_wanandroid.widget.SelfSelectThemColorPreference;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

public class SettingFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener, PageType {
    SelfSelectThemColorPreference colorSelectPreference;
    private List<ThemeColorBean> color_data = new ArrayList<>();

    public SettingFragment() {
        Logger.i("page type "+this);

    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.setting_preferences, rootKey);
        setHasOptionsMenu(true);
        color_data.add(new ThemeColorBean(false, -1, R.color.TURQUOISE, "TURQUOISE"));
        color_data.add(new ThemeColorBean(false, -1, R.color.EMERALD, "EMERALD"));
        color_data.add(new ThemeColorBean(false, -1, R.color.PETERRIVER, "PETERRIVER"));
        color_data.add(new ThemeColorBean(false, -1, R.color.SUNFLOWER, "SUNFLOWER"));

        color_data.add(new ThemeColorBean(false, -1, R.color.AMETHYST, "AMETHYST"));
        color_data.add(new ThemeColorBean(false, -1, R.color.CARROT, "CARROT"));
        color_data.add(new ThemeColorBean(false, -1, R.color.ALIZARIN, "ALIZARIN"));
        color_data.add(new ThemeColorBean(false, -1, R.color.ORANGE, "ORANGE"));

        findPreference("switch_noPhotoMode").setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                Logger.i("new vale is " + newValue);
                return true;
            }
        });

        findPreference("auto_nightMode");


        colorSelectPreference = findPreference("color_select");
        colorSelectPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {

                ColorDialogFragment   colorDialogFragment = new ColorDialogFragment(color_data);
                Logger.i("pre :::" +  colorDialogFragment);
                colorDialogFragment.show(getFragmentManager(), "setting");

                return false;
            }
        });




        findPreference("clearCache").setSummary(CacheUtils.getTotalCacheSize(getContext()));

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


        try {
            String Version = "当前版本: " + getContext().getPackageManager().getPackageInfo(getContext().getPackageName(), 0).versionName;
            findPreference("version").setSummary(Version);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        findPreference("clearCache").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {

                new AlertDialog.Builder(getContext())
                        .setTitle("清除缓存")
                        .setMessage("您确定要清除缓存？")
                        .setCancelable(true)
                        .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                CacheUtils.clearAllCache(getContext());
                                findPreference("clearCache").setSummary(CacheUtils.getTotalCacheSize(getContext()));

                            }
                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                    }
                }).show();

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
    public void onResume() {
        super.onResume();
        this.getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        this.getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Logger.i("onSharedPreferenceChanged   "+key);
           if (key.equals("theme_color")){
               colorSelectPreference.setView();
           }
    }

    @Override
    public void initToolbar(ActionBar toolbar) {
        toolbar.setTitle("设置");
    }

    @Override
    public void setData(Object data, Object... args) {

    }

//    @Override
//    public void setData(Object data) {
//
//    }

    @Override
    public void setPresenter(BasePresenter presenter) {

    }
}
