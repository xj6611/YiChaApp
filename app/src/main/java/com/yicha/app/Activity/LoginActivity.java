package com.yicha.app.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yicha.app.BaseApplication;
import com.yicha.app.Class.CircleImageView;
import com.yicha.app.Entity.BaseEntity;
import com.yicha.app.Entity.UserEntity;
import com.yicha.app.Presenter.LoginPresenter;
import com.yicha.app.R;
import com.yicha.app.View.LoginContact;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dyc.commlibrary.activity.BaseActivity;
import dyc.commlibrary.utils.StringUtils;
import dyc.commlibrary.utils.ToastUtils;

public class LoginActivity extends BaseActivity implements LoginContact.View {
    @BindView(R.id.ed_accountnumber)
    EditText edAccountnumber;
    @BindView(R.id.ed_password)
    EditText edPassword;
    @BindView(R.id.mLoginTv)
    TextView mLoginTv;
    @BindView(R.id.tv_sigin)
    TextView tvSigin;
    @BindView(R.id.tv_forgetpwd)
    TextView tvForgetpwd;
    @BindView(R.id.KeJianBi_Pwd)
    ImageView KeJian_Pwd;
    @BindView(R.id.KeJianZheng_Pwd)
    ImageView KeJianZheng_Pwd;
    @BindView(R.id.CirleImageView_HeadPortrait)
    CircleImageView CirleImageView_HeadPortrait;
    private CheckBox rememberpasswordchekbox;
    LoginPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loggin);
        ButterKnife.bind(this);
        mPresenter = new LoginPresenter(this);
        rememberpasswordchekbox = (CheckBox) findViewById(R.id.rememberpasswordchekbox);
//        edAccountnumber.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        rememberpasswordchekbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (rememberpasswordchekbox.isChecked()) {
                    InITBaoCun();
                }
            }
        });
        readAccount();
        if (!BaseApplication.getInstance().getUserFace().equals("")) {
            Picasso.with(this).load(BaseApplication.getInstance().getUserFace()).error(R.mipmap.touciang).placeholder(R.mipmap.touciang).into(CirleImageView_HeadPortrait);
        }
//        edAccountnumber.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (!BaseApplication.getInstance().getUserFace().equals("")) {
            Picasso.with(this).load(BaseApplication.getInstance().getUserFace()).error(R.mipmap.touciang).placeholder(R.mipmap.touciang).into(CirleImageView_HeadPortrait);
        }
    }

    // 保存用户名和密码
    private void InITBaoCun() {
        String name = edAccountnumber.getText().toString().trim();
        String pwd = edPassword.getText().toString().trim();
        SharedPreferences sp = getSharedPreferences("info", MODE_PRIVATE);
        SharedPreferences.Editor ed = sp.edit();
        ed.putString("name", name);
        ed.putString("pwd", pwd);
        ed.commit();
    }

    //读取保存在本地的用户名和密码
    public void readAccount() {

        //创建SharedPreferences对象
        SharedPreferences sp = getSharedPreferences("info", MODE_PRIVATE);

        //获得保存在SharedPredPreferences中的用户名和密码
        String username = sp.getString("name", "");
        String password = sp.getString("pwd", "");

        //在用户名和密码的输入框中显示用户名和密码
        edAccountnumber.setText(username);
        edPassword.setText(password);
    }

    public String getPhone() {
        return this.edAccountnumber.getText().toString().trim();
    }

    public String getPwd() {
        return this.edPassword.getText().toString().trim();
    }


    @Override
    public void loginSuccess(UserEntity user) {
        ToastUtils.showShort(mContext, "登录成功");
        BaseApplication.getInstance().saveUserInfo(user);
        Intent intent = new Intent(mContext, MainActivity.class);
        intent.putExtra("edPassword", edPassword.getText().toString().trim());
        startActivity(intent);
        finish();
    }

    @Override
    public void loginError(BaseEntity value) {
        ToastUtils.showShort(this,value.getMsg());
    }

    @Override
    public void loginErrorString(String value) {
        ToastUtils.showShort(this,value);
    }

    @Override
    public void showToast(String msg) {
        Log.i("asss", "msg==" + msg);
        ToastUtils.showShort(this, msg);
    }

    @OnClick({R.id.mLoginTv, R.id.tv_sigin, R.id.tv_forgetpwd, R.id.KeJianBi_Pwd, R.id.KeJianZheng_Pwd})
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.mLoginTv:
                login();
                break;
            case R.id.tv_sigin:
//                intent.setClass(this, LetteOfCommitmentActivity.class);
//                startActivity(intent);
                break;
            case R.id.tv_forgetpwd:
                intent.setClass(this, ForgotPasswordActivity.class);
                intent.putExtra("phone",getPhone());
                startActivity(intent);
                break;
            case R.id.KeJianBi_Pwd:
                KeJianZheng_Pwd.setVisibility(View.VISIBLE);
                KeJian_Pwd.setVisibility(View.INVISIBLE);
                //从密码不可见模式变为密码可见模式
                edPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                break;
            case R.id.KeJianZheng_Pwd:
                KeJian_Pwd.setVisibility(View.VISIBLE);
                KeJianZheng_Pwd.setVisibility(View.INVISIBLE);
                //从密码可见模式变为密码不可见模式
                edPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                break;

        }
    }

    private void login() {
        if (checkData()) {
            InITBaoCun();
            mPresenter.requestLogin();
        }
    }

    private boolean checkData() {
        if (StringUtils.isEmpty(getPhone())) {
            ToastUtils.showShort(mContext, "账号为空");
            return false;
        }

        if (StringUtils.isEmpty(getPwd())) {
            ToastUtils.showShort(mContext, "密码为空");
            return false;
        }
        return true;
    }

}