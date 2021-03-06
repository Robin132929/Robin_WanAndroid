package com.robin.robin_wanandroid.ui.content;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.DownloadListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.robin.rbase.CommonBase.Cache.Cache;
import com.robin.rbase.CommonBase.Cache.IntelligentCache;
import com.robin.rbase.CommonUtils.Logger.Logger;
import com.robin.robin_wanandroid.R;
import com.robin.robin_wanandroid.adapter.wanandroid.ShowMoreNavgationAdapter;
import com.robin.robin_wanandroid.base.RobinBaseActivity;
import com.robin.robin_wanandroid.mvp.contract.ContentContract;
import com.robin.robin_wanandroid.mvp.model.bean.NavgationBean;
import com.robin.robin_wanandroid.mvp.presenter.ContentPresenter;

import java.util.List;

public class ContentActivity extends RobinBaseActivity<ContentPresenter> implements ContentContract.View {
    private static List<NavgationBean.DataBean.ArticlesBean> mArticlesBean;
    private static int Tag;
    private WebView webView;
    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private ShowMoreNavgationAdapter mShowMoreNavgationAdapter;
    private LinearLayout linearLayout;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_content);
//        this.addContentView();
//        webView=findViewById(R.id.webview);
//        initWebView(this,webView);
//        Intent intent=getIntent();
//       String url= intent.getStringExtra("url");
//       if (url!=null){
//           Logger.i("url is "+url);
//           webView.loadUrl(url);
//
//       }
//    }

    public static void loadData(WebView webView, String content) {
        webView.loadDataWithBaseURL(null, content, "text/html", "UTF-8", null);//这种写法可以正确解码
    }

    public static void startActivity(Context context, List<NavgationBean.DataBean.ArticlesBean> ArticlesBean) {
        Tag = 1;
        mArticlesBean = ArticlesBean;
        Intent intent = new Intent(context, ContentActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @NonNull
    @Override
    public Cache<String, Object> provideCache() {
        return new IntelligentCache<>(4 * 1024);
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        linearLayout = findViewById(R.id.ll_content);
        mToolbar = findViewById(R.id.toolbar);
        mToolbar.findViewById(R.id.search_btn).setVisibility(View.GONE);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        if (Tag == 0) {
            webView = new WebView(this);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//        this.addContentView(webView,lp);
            linearLayout.addView(webView, lp);
        }
        if (Tag == 1) {
            mRecyclerView = new RecyclerView(this);
//        mRecyclerView.setPadding(0,getSupportActionBar().getHeight(),0,0);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//        this.addContentView(webView,lp);
            linearLayout.addView(mRecyclerView, lp);
        }

        Logger.i("current resurse content " + getResources().toString());

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_content;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {


        if (Tag == 0) {
            initWebView(this, webView);
            Intent intent = getIntent();
            String url = intent.getStringExtra("url");
            String title = intent.getStringExtra("title");
//            if (!TextUtils.isEmpty(url) && !TextUtils.isEmpty(title)) {
//                mPresenter.addFootPrint(title, url);
//            }
            if (url != null) {
                Logger.i("url is " + url);
                webView.loadUrl(url);

            }
        }
        if (Tag == 1) {
            Logger.i("art is :" + mArticlesBean.size());
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
            mRecyclerView.setLayoutManager(gridLayoutManager);
            mShowMoreNavgationAdapter = new ShowMoreNavgationAdapter(R.layout.item_section_content, mArticlesBean);
//            mShowMoreNavgationAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//                @Override
//                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                    NavgationBean.DataBean.ArticlesBean articlesBean = (NavgationBean.DataBean.ArticlesBean) adapter.getItem(position);
//                    startActivity(ContentActivity.this, articlesBean.getTitle(), articlesBean.getLink());
//                }
//            });
            mRecyclerView.setAdapter(mShowMoreNavgationAdapter);
        }
    }

    public static void initWebView(final Context context, final WebView webBase) {
        WebSettings webSettings = webBase.getSettings();
        if (Build.VERSION.SDK_INT >= 19) {
            webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);//加载缓存否则网络
        }

        if (Build.VERSION.SDK_INT >= 19) {
            webSettings.setLoadsImagesAutomatically(true);//图片自动缩放 打开
        } else {
            webSettings.setLoadsImagesAutomatically(false);//图片自动缩放 关闭
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            webBase.setLayerType(View.LAYER_TYPE_SOFTWARE, null);//软件解码
        }
        webBase.setLayerType(View.LAYER_TYPE_HARDWARE, null);//硬件解码

//        webSettings.setAllowContentAccess(true);
//        webSettings.setAllowFileAccessFromFileURLs(true);
//        webSettings.setAppCacheEnabled(true);
   /*     if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }*/


        // setMediaPlaybackRequiresUserGesture(boolean require) //是否需要用户手势来播放Media，默认true

        webSettings.setJavaScriptEnabled(true); // 设置支持javascript脚本
//        webSettings.setPluginState(WebSettings.PluginState.ON);
        webSettings.setSupportZoom(true);// 设置可以支持缩放
        webSettings.setBuiltInZoomControls(true);// 设置出现缩放工具 是否使用WebView内置的缩放组件，由浮动在窗口上的缩放控制和手势缩放控制组成，默认false

        webSettings.setDisplayZoomControls(false);//隐藏缩放工具
        webSettings.setUseWideViewPort(true);// 扩大比例的缩放

        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);//自适应屏幕
        webSettings.setLoadWithOverviewMode(true);

        webSettings.setDatabaseEnabled(true);//
        webSettings.setSavePassword(true);//保存密码
        webSettings.setDomStorageEnabled(true);//是否开启本地DOM存储  鉴于它的安全特性（任何人都能读取到它，尽管有相应的限制，将敏感数据存储在这里依然不是明智之举），Android 默认是关闭该功能的。
        webBase.setSaveEnabled(true);
        webBase.setKeepScreenOn(true);


        //设置此方法可在WebView中打开链接，反之用浏览器打开
        webBase.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                if (url.startsWith("http:") || url.startsWith("https:")) {
                    view.loadUrl(url);
                    return false;
                }

                // Otherwise allow the OS to handle things like tel, mailto, etc.
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                context.startActivity(intent);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                // TODO Auto-generated method stub
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (!webBase.getSettings().getLoadsImagesAutomatically()) {
                    webBase.getSettings().setLoadsImagesAutomatically(true);
                }
            }
        });
        webBase.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String paramAnonymousString1, String paramAnonymousString2, String paramAnonymousString3, String paramAnonymousString4, long paramAnonymousLong) {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                intent.setData(Uri.parse(paramAnonymousString1));
                context.startActivity(intent);
            }
        });
    }

    public static void startActivity(Context context, String title, String url) {
        Tag = 0;
        Intent intent = new Intent(context, ContentActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("url", url);
        intent.putExtra("title", title);
        context.startActivity(intent);
    }

    @Override
    public boolean useFragment() {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError() {

    }
}
