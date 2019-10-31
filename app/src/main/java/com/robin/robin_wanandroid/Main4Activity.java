package com.robin.robin_wanandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.robin.rbase.CommonUtils.Logger.Logger;
import com.robin.robin_wanandroid.dummy.DummyContent;
import com.robin.robin_wanandroid.util.flowView.FlowAdapter;
import com.robin.robin_wanandroid.util.flowView.FlowLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main4Activity extends AppCompatActivity  {
    private static final String TAG = "Main4Activity";
    FlowLayout flowLayout;
    List<String> data=new ArrayList<>();
    RecyclerView recyclerView;
    ImageButton imageButton;
    ViewPager viewpager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        viewpager = (ViewPager) findViewById(R.id.viewpager);
//        viewpager.setAdapter(new MyPagerAdapter());
//        VerticalTabLayout tablayout = (VerticalTabLayout) findViewById(R.id.tablayout1);
//        tablayout.setupWithViewPager(viewpager);
//        initTab2();
//        startActivity(new Intent(Main4Activity.this, MainActivity.class));
//        recyclerView=findViewById(R.id.flow_rv);
//        imageButton=findViewById(R.id.img_btn);
//        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,6);
//        gridLayoutManager.setOrientation(RecyclerView.VERTICAL);
//        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
//        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
//        imageButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (recyclerView.getLayoutManager() instanceof GridLayoutManager){
//                    recyclerView.setLayoutManager(linearLayoutManager);
//                   Logger.i("line");
//                }else  {
//                    recyclerView.setLayoutManager(gridLayoutManager);
//                    Logger.i("grid");
//                }
//            }
//        });
////        flowLayout=findViewById(R.id.flowlayout);
//        for (int i = 0; i <20 ; i++) {
//            if (i%3==0){
//                data.add("第"+i+"项sdsdsdsdsdcs");
//
//            }else {
//                data.add("第"+i+"项");
//
//            }
//        }
//        FlowAdapter flowAdapter=new FlowAdapter(this,data);
//        flowAdapter.setRecyclerView(recyclerView);
//        recyclerView.setLayoutManager(linearLayoutManager);
//
//        recyclerView.setAdapter(flowAdapter);



//        flowLayout.setData(data);
//       FragmentTransaction fm= getSupportFragmentManager().beginTransaction();
//       fm.add(R.id.fl_content_container,ItemFragment.newInstance(1));
//       fm.commit();
//        RecyclerView recyclerView=findViewById(R.id.recycle);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(new MyItemRecyclerViewAdapter(DummyContent.ITEMS, this));
//        if (getSupportActionBar() != null) {
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        }

    }

//    private class MyPagerAdapter extends PagerAdapter implements TabAdapter {
//        List<String> titles;
//
//        public MyPagerAdapter() {
//            titles = new ArrayList<>();
//            Collections.addAll(titles, "Android", "IOS", "Web", "JAVA", "C++",
//                    ".NET", "JavaScript", "Swift", "PHP", "Python", "C#", "Groovy", "SQL", "Ruby");
//        }
//
//        @Override
//        public int getCount() {
//            return titles.size();
//        }
//
//        @Override
//        public TabView.TabBadge getBadge(int position) {
//            if (position == 5) return new TabView.TabBadge.Builder().setBadgeNumber(666)
//                    .setExactMode(true)
//                   .build();
//            return null;
//        }
//
//        @Override
//        public TabView.TabIcon getIcon(int position) {
//            return null;
//        }
//
//        @Override
//        public TabView.TabTitle getTitle(int position) {
//
//            return new TabView.TabTitle.Builder()
//                    .setContent(titles.get(position))
//                    .setTextColor(Color.WHITE, 0xBBFFFFFF)
//                    .build();
//        }
//
//        @Override
//        public int getBackground(int position) {
//            return 0;
//        }
//
//        @Override
//        public boolean isViewFromObject(View view, Object object) {
//            return view == object;
//        }
//
//        @Override
//        public Object instantiateItem(ViewGroup container, int position) {
//            TextView tv = new TextView(Main4Activity.this);
//            tv.setTextColor(Color.WHITE);
//            tv.setGravity(Gravity.CENTER);
//            tv.setText(titles.get(position));
//            tv.setTextSize(18);
//            container.addView(tv);
//            return tv;
//        }
//
//        @Override
//        public void destroyItem(ViewGroup container, int position, Object object) {
//            container.removeView((View) object);
//        }
//    }
//
//    private void initTab2() {
//        VerticalTabLayout tablayout = (VerticalTabLayout) findViewById(R.id.tablayout1);
//        tablayout.setupWithViewPager(viewpager);
//        tablayout.setTabBadge(2, -1);
//        tablayout.setTabBadge(8, -1);
//        tablayout.getTabAt(3).setBadge(new TabView.TabBadge.Builder().setBadgeGravity(Gravity.START | Gravity.TOP)
//                .setBadgeNumber(999)
//                .build());
////                .setOnDragStateChangedListener(new T.OnDragStateChangedListener() {
////                    @Override
////                    public void onDragStateChanged(int dragState, Badge badge, View targetView) {
////                        if (dragState == STATE_SUCCEED) {
////                            badge.setBadgeNumber(-1).stroke(0xFFFFFFFF,1,true);
////                        }
////                    }
////                })
////                .build());
//    }
}
