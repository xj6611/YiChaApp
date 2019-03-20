package com.yicha.app.Activity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hss01248.dialog.StyledDialog;
import com.hss01248.dialog.interfaces.MyItemDialogListener;
import com.yicha.app.BaseApplication;
import com.yicha.app.Entity.PC_GuJiaEntity;
import com.yicha.app.Presenter.GuJiaPresenter;
import com.yicha.app.R;
import com.yicha.app.Utlis.RequestBodyUtil;
import com.yicha.app.Utlis.SelfDialog;
import com.yicha.app.View.GuJiaContact;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dyc.commlibrary.activity.BaseActivity;
import dyc.commlibrary.utils.ToastUtils;
import dyc.commlibrary.widget.datepicker.DatePickDialog;
import dyc.commlibrary.widget.datepicker.bean.DateType;
import okhttp3.RequestBody;

public class PC_ValuationActivity extends BaseActivity implements GuJiaContact.View {
    @BindView(R.id.Title)
    TextView Title;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.chengse)
    TextView chengse;
    @BindView(R.id.mLoginTv)
    TextView mLoginTv;
    @BindView(R.id.Money)
    TextView Money;
    @BindView(R.id.Money_LinearLayout)
    LinearLayout Money_LinearLayout;
    @BindView(R.id.Add_FrameLayout)
    FrameLayout Add_FrameLayout;
    private int id;
    private String title;
    private GuJiaContact.Presenter presenter;
    private SimpleDateFormat simpleDateFormat;
    private SelfDialog selfDialog;
    private HashMap<String, String> stringHashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pc__valuation);
        ButterKnife.bind(this);
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        id = getIntent().getIntExtra("id", 0);
        title = getIntent().getStringExtra("name");
        stringHashMap = ((HashMap) getIntent().getSerializableExtra("stringHashMap"));
        Title.setText(title);
        presenter = new GuJiaPresenter(this);

    }

    @OnClick({R.id.Add_FrameLayout, R.id.time, R.id.chengse, R.id.mLoginTv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.Add_FrameLayout:
                finish();
                break;
            case R.id.time:
                showDatePickDialog();
                break;
            case R.id.chengse:
                showCXDialog();
                break;
            case R.id.mLoginTv:
                if (time.getText().toString().equals("")) {
                    ToastUtils.showShort(this, "请选择时间");
                    return;
                }
                if (chengse.getText().toString().equals("")) {
                    ToastUtils.showShort(this, "请选择成色");
                    return;
                }
                Dialog(this);
                break;

        }
    }

    private void showCXDialog() {
        final List<String> strings = new ArrayList<>();
        strings.add("1");
        strings.add("2");
        strings.add("3");
        strings.add("4");
        strings.add("5");
        strings.add("6");
        strings.add("7");
        strings.add("8");
        strings.add("9");
        StyledDialog.buildBottomItemDialog(strings, "取消", new MyItemDialogListener() {
            @Override
            public void onItemClick(CharSequence text, int position) {
                chengse.setText(strings.get(position));
            }

            @Override
            public void onBottomBtnClick() {
            }
        }).show();
    }

    private void showDatePickDialog() {
        DatePickDialog dialog = new DatePickDialog(this);
        //设置上下年分限制
        dialog.setYearLimt(1000);
        //设置标题
        dialog.setTitle("选择时间");
        //设置类型
        dialog.setType(DateType.TYPE_YMD);
        //设置消息体的显示格式，日期格式
        dialog.setMessageFormat("yyyy-MM-dd");
        //设置选择回调
        dialog.setOnChangeLisener(null);
        //设置点击确定按钮回调
        dialog.setOnSureLisener(date -> time.setText(simpleDateFormat.format(date.getTime())));
        dialog.show();
    }

    public void Dialog(final Context context) {
        selfDialog = new SelfDialog(context);
        selfDialog.setTitle("请输入警情编号");
        selfDialog.setMessage("");
        selfDialog.setYesOnclickListener("确定", () -> {
            if (!selfDialog.getMessage().equals("")) {
                selfDialog.dismiss();
                Money_LinearLayout.setVisibility(View.VISIBLE);
                presenter.request();
            } else {
                ToastUtils.showShort(context, "请输入警情编号！");
                Money_LinearLayout.setVisibility(View.GONE);
            }
            return true;
        });
        selfDialog.setNoOnclickListener("取消", () -> selfDialog.dismiss());
        selfDialog.show();
    }

    @Override
    public void Success(PC_GuJiaEntity paramPC_GuJiaEntity) {
        Money.setText(paramPC_GuJiaEntity.getPrice());
        mLoginTv.setText("返回评估");
    }

    @Override
    public Map<String, RequestBody> getParts() {
        {
            HashMap hashMap = new HashMap();
            hashMap.put("uid", RequestBodyUtil.convertToRequestBody(String.valueOf(BaseApplication.getInstance().getUserId())));
            hashMap.put("token", RequestBodyUtil.convertToRequestBody(String.valueOf(BaseApplication.getInstance().getToken())));
            hashMap.put("newlevel", RequestBodyUtil.convertToRequestBody(chengse.getText().toString().trim()));
            hashMap.put("modelName", RequestBodyUtil.convertToRequestBody(title));
            hashMap.put("buytime", RequestBodyUtil.convertToRequestBody(time.getText().toString().trim()));
            hashMap.put("casenumber", RequestBodyUtil.convertToRequestBody(selfDialog.getMessage()));
            Iterator iter = stringHashMap.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                Object key = entry.getKey();
                Object val = entry.getValue();
                hashMap.put((String) key, RequestBodyUtil.convertToRequestBody((String) val));
            }
            return hashMap;
        }
    }
}
