package com.yicha.app.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.yicha.app.BaseApplication;
import com.yicha.app.Class.Timeutils;
import com.yicha.app.Entity.BaseEntity;
import com.yicha.app.Presenter.ResetYourPasswordPresenter;
import com.yicha.app.R;
import com.yicha.app.View.ResetYourPasswordContant;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dyc.commlibrary.activity.BaseActivity;
import dyc.commlibrary.utils.ToastUtils;

public class ResetYourPasswordActivity extends BaseActivity implements ResetYourPasswordContant.View {
    @BindView(R.id.Return_FrameLayout)
    FrameLayout Return_FrameLayout;
    @BindView(R.id.newpwd)
    EditText newpwd;
    @BindView(R.id.newpwd2)
    EditText newpwd2;
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.mLoginTv)
    TextView mLoginTv;
    private ResetYourPasswordContant.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_your_password);
        ButterKnife.bind(this);
        presenter = new ResetYourPasswordPresenter(this);
        phone.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
    }

    @OnClick({R.id.Return_FrameLayout, R.id.mLoginTv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mLoginTv:
                if (Detection() == false) {
                    ToastUtils.showShort(this, "两次密码输入不一致");
                } else {
                    presenter.request();
                }
                break;
            case R.id.Return_FrameLayout:
                finish();
                break;
        }
    }

    private Boolean Detection() {
        if (newpwd2.getText().toString().trim().equals(newpwd.getText().toString().trim())) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int getID() {
        return BaseApplication.getInstance().getUserId();
    }

    @Override
    public String getToken() {
        return BaseApplication.getInstance().getToken();
    }

    @Override
    public String getPwd() {
        return newpwd.getText().toString().trim();
    }

    @Override
    public String getYuanPwd() {
        return phone.getText().toString().trim();
    }

    @Override
    public void Success(BaseEntity value) {
        ToastUtils.showShort(this, value.getMsg() + ",请用新密码重新登录");
        BaseApplication.getInstance().clearUserInfo();
        SharedPreferences sp = getSharedPreferences("info", MODE_PRIVATE);
        SharedPreferences.Editor ed = sp.edit();
        ed.putString("pwd", "");
        ed.commit();
        Intent intent = new Intent(this, LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
