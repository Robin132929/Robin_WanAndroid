//package com.robin.robin_wanandroid.entity;
//
//import android.graphics.Color;
//import android.view.Gravity;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import com.robin.robin_wanandroid.Main4Activity;
//import com.robin.robin_wanandroid.app.App;
//
//import java.util.List;
//
//import androidx.annotation.NonNull;
//import androidx.viewpager.widget.PagerAdapter;
//import q.rorbin.verticaltablayout.adapter.TabAdapter;
//import q.rorbin.verticaltablayout.widget.ITabView;
//import q.rorbin.verticaltablayout.widget.TabView;
//
//public class MyPagerAdapter extends PagerAdapter implements TabAdapter {
//
//    List<String> titles;
//
//    @Override
//    public int getCount() {
//        return titles.size();
//    }
//
//    @Override
//    public ITabView.TabBadge getBadge(int position) {
//        if (position == 5) return new TabView.TabBadge.Builder().setBadgeNumber(666)
//                .setExactMode(true)
//                .build();
//        return null;
//    }
//
//    @Override
//    public ITabView.TabIcon getIcon(int position) {
//        return null;
//    }
//
//    @Override
//    public ITabView.TabTitle getTitle(int position) {
//        return new TabView.TabTitle.Builder()
//                .setContent(titles.get(position))
//                .setTextColor(Color.WHITE, 0xBBFFFFFF)
//                .build();
//    }
//
//    @Override
//    public int getBackground(int position) {
//        return 0;
//    }
//
//    @Override
//    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
//        return view==object;
//    }
//
//    @Override
//    public Object instantiateItem(ViewGroup container, int position) {
//        TextView tv = new TextView(App.getmMyAppComponent().application());
//        tv.setTextColor(Color.WHITE);
//        tv.setGravity(Gravity.CENTER);
//        tv.setText(titles.get(position));
//        tv.setTextSize(18);
//        container.addView(tv);
//        return tv;
//    }
//
//    @Override
//    public void destroyItem(ViewGroup container, int position, Object object) {
//        container.removeView((View) object);
//    }
//}
