package com.robin.robin_wanandroid.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.robin.rbase.CommonUtils.Utils.PreferUtil;
import com.robin.rbase.MVP.MvpBase.BaseMvpActivity;
import com.robin.robin_wanandroid.R;
import com.robin.robin_wanandroid.app.App;
import com.robin.robin_wanandroid.mvp.contract.wanandroid.LoginContract;
import com.robin.robin_wanandroid.mvp.presenter.wanandroid.LoginPresenter;

public class LoginActivity extends BaseMvpActivity<LoginPresenter> implements LoginContract.View {
    TextWatcher afterTextChangedListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            // ignore
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // ignore
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    private TextView account;
    private TextView passwored;
    private Button login;
    private Button register;

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        account = findViewById(R.id.username);
        passwored = findViewById(R.id.password);
        login = findViewById(R.id.login);
        register = findViewById(R.id.register);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        account.addTextChangedListener(afterTextChangedListener);
        passwored.addTextChangedListener(afterTextChangedListener);
        passwored.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                return false;
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=account.getText().toString();
                String password=passwored.getText().toString();
                if (name.isEmpty()||password.isEmpty()){
                    Toast.makeText(LoginActivity.this, "密码或用户名不能为空", Toast.LENGTH_LONG).show();
                    return;
                }
                mPresenter.login(name,password);
            }
        });
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

    @Override
    public void login(String name, String password) {
        PreferUtil.persist(App.getmMyAppComponent().application(),"login",true);
        PreferUtil.persist(App.getmMyAppComponent().application(),"name",name);
        finish();
    }
}
