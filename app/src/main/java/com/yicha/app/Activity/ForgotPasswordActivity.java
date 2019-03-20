package com.yicha.app.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
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
import com.yicha.app.Class.Timeutils2;
import com.yicha.app.Entity.BaseEntity;
import com.yicha.app.Presenter.ForgotPasswordPresenter;
import com.yicha.app.R;
import com.yicha.app.View.ForgotPasswordContant;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dyc.commlibrary.activity.BaseActivity;
import dyc.commlibrary.utils.ToastUtils;

public class ForgotPasswordActivity extends BaseActivity implements ForgotPasswordContant.View {
    @BindView(R.id.Return_FrameLayout)
    FrameLayout Return_FrameLayout;
    @BindView(R.id.newpwd)
    EditText newpwd;
    @BindView(R.id.code)
    EditText code;
    @BindView(R.id.GetCode)
    TextView GetCode;
    @BindView(R.id.newpwd2)
    EditText newpwd2;
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.mLoginTv)
    TextView mLoginTv;
    private int sun;
    ForgotPasswordContant.Presenter presenter;
    private Timeutils2 timeutils;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1://录音是播放示范录音按钮隐藏，禁止播放按钮显示
                    GetCode.setText("获取验证码");
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        ButterKnife.bind(this);
        presenter = new ForgotPasswordPresenter(this);
        phone.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
    }

    @OnClick({R.id.Return_FrameLayout, R.id.mLoginTv, R.id.GetCode})
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
            case R.id.GetCode:
                if (GetCode.getText().toString().trim().equals("获取验证码")) {
                    presenter.requestCode();
                    timeutils = new Timeutils2(GetCode);
                    timeutils.setCount(30);
                    timeutils.startTimer();
                    sun = timeutils.getCount();
                    timeutils.setOnControlsItemClickListener(new Timeutils2.OnCompleteClickListener() {
                        @Override
                        public void onUpDataItemClick(int time) {
                            if (time == 0) {
                                if (sun == 30) {
                                    handler.sendEmptyMessage(1);
                                }
                            }
                        }
                    });
                }
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
    public String getPhone() {
        return phone.getText().toString().trim();
    }

    @Override
    public String getPwd() {
        return newpwd.getText().toString().trim();
    }

    @Override
    public int getCode() {
        return Integer.parseInt(code.getText().toString().trim());
    }

    @Override
    public void DatalisSuccess(BaseEntity value) {
        ToastUtils.showShort(this, value.getMsg());
        if (value.getResult() == 1) {
            BaseApplication.getInstance().clearUserInfo();
            SharedPreferences sp = getSharedPreferences("info", MODE_PRIVATE);
            SharedPreferences.Editor ed = sp.edit();
            ed.putString("pwd", "");
            ed.commit();
            Intent intent = new Intent(this, LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }

    @Override
    public void CodeSuccess(BaseEntity value) {
        ToastUtils.showShort(this, value.getMsg());
    }
}
