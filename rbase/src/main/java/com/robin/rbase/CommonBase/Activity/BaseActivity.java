package com.robin.rbase.CommonBase.Activity;

import android.app.Activity;
import android.os.Bundle;

import com.robin.rbase.CommonBase.Cache.Cache;
import com.robin.rbase.CommonBase.Cache.CacheType;
import com.robin.rbase.CommonBase.delegate.IActivity;
import com.robin.rbase.CommonBase.utils.Const;
import com.robin.rbase.MVP.MvpBase.BaseMvpActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * ================================================
 * 框架要求框架中的每个 {@link Activity} 都继承于此类,此类只提供了{@link Activity}需要的最基础方法
 * 可在不想使用任何架构的情况下继承此类，如果想要使用MVP架构可继承{@link BaseMvpActivity}
 * 后者添加了支持MVP架构的相关内容
 *
 * @see BaseActivity
 * ================================================
 */
public abstract class BaseActivity extends AppCompatActivity implements IActivity {
    Cache<String, Object> mCache;
    private Unbinder mUnbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       try{
           int layoutResID=getLayoutId();
           if (layoutResID != 0) {
               setContentView(layoutResID);
               mUnbinder =ButterKnife.bind(this);
           }
       }catch (Exception e){
           e.printStackTrace();
       }
        initView(savedInstanceState);
        initData(savedInstanceState);
    }

    @NonNull
    @Override
    public Cache<String, Object> provideCache() {
        if (mCache == null) {
            mCache = Const.getCacheFactory(getApplication()).build(CacheType.ACTIVITY_CACHE);
        }
        return mCache;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnbinder != null && mUnbinder != Unbinder.EMPTY)
            mUnbinder.unbind();
        this.mUnbinder = null;

    }


//    /**
//     * 初始化ToolBar
//     */
//    protected abstract void initToolbar();
//
//    /**
//     * 封装toast方法
//     *
//     * @param str
//     *
//     */
//     protected  void showToast(String str) {
//        //TODO
//    }
//    protected Intent getIntent(Class<?> cls){
//        return new Intent(getContext(),cls);
//    }
//    protected void startActivity(Class<?> cls){
//        startActivity(getIntent(cls));
//    }
//    /**
//     * [携带数据的页面跳转]
//     *
//     * @param clz
//     * @param bundle
//     */
//    public void startActivity(Class<?> clz, Bundle bundle) {
//        Intent intent = new Intent();
//        intent.setClass(this, clz);
//        if (bundle != null) {
//            intent.putExtras(bundle);
//        }
//        startActivity(intent);
//    }
//
//    /**
//     * [含有Bundle通过Class打开编辑界面]
//     *
//     * @param cls
//     * @param bundle
//     * @param requestCode
//     */
//    public void startActivityForResult(Class<?> cls, Bundle bundle, int requestCode) {
//        Intent intent = new Intent();
//        intent.setClass(this, cls);
//        if (bundle != null) {
//            intent.putExtras(bundle);
//        }
//        startActivityForResult(intent, requestCode);
//    }
//
//    /**
//     * 沉浸式实现
//     */
//    protected void setStatusBar() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            // 透明状态栏
//            getWindow().addFlags(
//                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            // 透明导航栏
//            getWindow().addFlags(
//                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        }
//    }
//    private void startLogin() {
//    }
//    public void finishActivity() {
//        finish();
//    }
//    /** 获取 Intent 数据 **/
//    protected abstract void getIntent(Intent intent);
//    public void showDialogFragment(DialogFragment dialogFragment){
//
//    }
//    public void replaceFragment(@IdRes int resId, Fragment fragment, boolean isBackStack) {
//        FragmentTransaction fragmentTransaction =  getSupportFragmentManager().beginTransaction();
//        fragmentTransaction.replace(resId, fragment);
//        if(isBackStack){
//            fragmentTransaction.addToBackStack(null);
//        }
//        fragmentTransaction.commit();
//    }
//    protected Context getContext(){
//        return this;
//    }

}
