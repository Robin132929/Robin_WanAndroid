package com.robin.robin_wanandroid.util.loading;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.robin.rbase.CommonUtils.Logger.Logger;
import com.robin.rbase.CommonUtils.Utils.Utils;
import com.robin.robin_wanandroid.R;

import static com.robin.robin_wanandroid.util.loading.Gloading.STATUS_EMPTY_DATA;
import static com.robin.robin_wanandroid.util.loading.Gloading.STATUS_LOADING;
import static com.robin.robin_wanandroid.util.loading.Gloading.STATUS_LOAD_FAILED;
import static com.robin.robin_wanandroid.util.loading.Gloading.STATUS_LOAD_SUCCESS;

@SuppressLint("ViewConstructor")
public class GlobalLoadingStatusView extends LinearLayout implements View.OnClickListener {

    private final TextView mTextView;
    private final Runnable mRetryTask;
    private final ProgressBar mImageView;

    public GlobalLoadingStatusView(Context context,Runnable retryTask) {
        super(context);
        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER_VERTICAL);
        LayoutInflater.from(context).inflate(R.layout.loading_view, this, true);
        mImageView = findViewById(R.id.pb_load);
        mTextView = findViewById(R.id.text);
        this.mRetryTask = retryTask;
        setBackgroundColor(getResources().getColor(R.color.deep_red));
    }

    public void setStatus(int status) {
        boolean show = true;
        OnClickListener onClickListener = null;
        int str = R.string.str_none;
        switch (status) {
            case STATUS_LOAD_SUCCESS: show = false; break;
            case STATUS_LOADING: str = R.string.loading; break;
            case STATUS_LOAD_FAILED:
                str = R.string.load_failed;
                Boolean networkConn = Utils.isNetworkEnable(getContext());
                if (networkConn != null && !networkConn) {
                    str = R.string.load_failed_no_network;
                }
                onClickListener = this;
                break;
            case STATUS_EMPTY_DATA:
                str = R.string.empty;
                break;
            default: break;
        }
//        mImageView.setImageResource(image);
        setOnClickListener(onClickListener);
        mTextView.setText(str);
        Logger.i("gload view is show "+show);
        setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onClick(View v) {
        if (mRetryTask != null) {

            mRetryTask.run();
        }
    }
}
