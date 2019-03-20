//package com.yicha.app.Activity;
//
//import android.content.Context;
//import android.os.Bundle;
//import android.support.v7.widget.LinearLayoutManager;
//import android.util.Log;
//import android.view.View;
//import android.widget.FrameLayout;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import com.hss01248.dialog.StyledDialog;
//import com.hss01248.dialog.interfaces.MyItemDialogListener;
//import com.jcodecraeer.xrecyclerview.XRecyclerView;
//import com.yicha.app.Adapter.PC_Data_XRecycleViewAdapter;
//import com.yicha.app.Adapter.StringTagAdapter;
//import com.yicha.app.BaseApplication;
//import com.yicha.app.Entity.DatalisEntity;
//import com.yicha.app.Entity.PC_DatalisEntity;
//import com.yicha.app.FlowBox.interfaces.OnFlexboxSubscribeListener;
//import com.yicha.app.FlowBox.widget.TagFlowLayout;
//import com.yicha.app.Presenter.Phone_DatalisPresenter;
//import com.yicha.app.Presenter.GuJiaPresenter;
//import com.yicha.app.Presenter.PC_DatalisPresenter;
//import com.yicha.app.R;
//import com.yicha.app.Utlis.SelfDialog;
//import com.yicha.app.View.Phone_DatalisContact;
//import com.yicha.app.View.GuJiaContact;
//import com.yicha.app.View.PC_DatalisContact;
//
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import butterknife.OnClick;
//import dyc.commlibrary.activity.BaseActivity;
//import dyc.commlibrary.utils.ToastUtils;
//import dyc.commlibrary.widget.datepicker.DatePickDialog;
//import dyc.commlibrary.widget.datepicker.OnSureLisener;
//import dyc.commlibrary.widget.datepicker.bean.DateType;
//
//public class DatalisActivity extends BaseActivity implements Phone_DatalisContact.View {
//    @BindView(R.id.Add_FrameLayout)
//    FrameLayout Add_FrameLayout;
//    @BindView(R.id.Money)
//    TextView Money;
//    @BindView(R.id.Money_LinearLayout)
//    LinearLayout Money_LinearLayout;
//    @BindView(R.id.Title)
//    TextView Title;
//    @BindView(R.id.chengse)
//    TextView chengse;
//    private int id;
//    @BindView(R.id.mLoginTv)
//    TextView mLoginTv;
//    private String newlevel;
//    private GuJiaContact.Presenter presenter;
//    private SelfDialog selfDialog;
//    private SimpleDateFormat simpleDateFormat;
//    private HashMap<String, String> stringHashMap;
//    @BindView(R.id.time)
//    TextView time;
//    private String title;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_datalis);
//        ButterKnife.bind(this);
//        presenter = new GuJiaPresenter(this);
//        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        id = getIntent().getIntExtra("id", 0);
//        title = getIntent().getStringExtra("name");
//        stringHashMap = ((HashMap) getIntent().getSerializableExtra("stringHashMap"));
//        Log.i("asss", "jjjjjj==" + stringHashMap.size());
//        Title.setText(title);
//    }
//
//    @OnClick({R.id.Add_FrameLayout, R.id.time, R.id.chengse, R.id.mLoginTv})
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.Add_FrameLayout:
//                finish();
//                break;
//            case R.id.time:
//                showDatePickDialog();
//                break;
//            case R.id.chengse:
//                showCXDialog();
//                break;
//            case R.id.mLoginTv:
//                if (time.getText().toString().equals("")){
//                    ToastUtils.showShort(this,"请选择时间");
//                    return;
//                }
//                if (chengse.getText().toString().equals("")){
//                    ToastUtils.showShort(this,"请选择成色");
//                    return;
//                }
//                Dialog(this);
//                break;
//
//        }
//    }
//
//    @Override
//    public int getUid() {
//        return BaseApplication.getInstance().getUserId();
//    }
//
//    @Override
//    public String getID() {
//        return id;
//    }
//
//    @Override
//    public String getToken() {
//        return BaseApplication.getInstance().getToken();
//    }
//
//
//    @Override
//    public void DatalisSuccess(PC_DatalisEntity value) {
//        list = new ArrayList<>();
//        ChengSe = new ArrayList<>();
//        NeiCun = new ArrayList<>();
//        Money.setText(value.get());
//        list.addAll(value.getOptions());
//        NeiCun.addAll(list.get(0).getValue());
//        ChengSe.addAll(list.get(1).getValue());
//    }
//
//    private void showCXDialog() {
//        final List<String> strings = new ArrayList<>();
//        strings.add("1成新");
//        strings.add("2成新");
//        strings.add("3成新");
//        strings.add("4成新");
//        strings.add("5成新");
//        strings.add("6成新");
//        strings.add("7成新");
//        strings.add("8成新");
//        strings.add("9成新");
//        StyledDialog.buildBottomItemDialog(strings, "取消", new MyItemDialogListener() {
//            @Override
//            public void onItemClick(CharSequence text, int position) {
//                Log.i("asss", "position==" + position);
//                Log.i("asss", strings.get(position));
//                chengse.setText(strings.get(position));
//            }
//
//            @Override
//            public void onBottomBtnClick() {
//            }
//        }).show();
//    }
//
//    private void showDatePickDialog() {
//        DatePickDialog dialog = new DatePickDialog(this);
//        //设置上下年分限制
//        dialog.setYearLimt(1000);
//        //设置标题
//        dialog.setTitle("选择时间");
//        //设置类型
//        dialog.setType(DateType.TYPE_YMD);
//        //设置消息体的显示格式，日期格式
//        dialog.setMessageFormat("yyyy-MM-dd");
//        //设置选择回调
//        dialog.setOnChangeLisener(null);
//        //设置点击确定按钮回调
//        dialog.setOnSureLisener(new OnSureLisener() {
//            @Override
//            public void onSure(Date date) {
//                time.setText(simpleDateFormat.format(date.getTime()));
//            }
//        });
//        dialog.show();
//    }
//
//    public void Dialog(final Context context) {
//        selfDialog = new SelfDialog(context);
//        selfDialog.setTitle("请输入警情编号");
//        selfDialog.setMessage("");
//        selfDialog.setYesOnclickListener("确定", new SelfDialog.onYesOnclickListener() {
//            @Override
//            public boolean onYesClick() {
//                if (!selfDialog.getMessage().equals("")) {
//                    selfDialog.dismiss();
//                    Money_LinearLayout.setVisibility(View.VISIBLE);
//                } else {
//                    ToastUtils.showShort(context, "请输入警情编号！");
//                    Money_LinearLayout.setVisibility(View.GONE);
//                }
//                return true;
//            }
//        });
//        selfDialog.setNoOnclickListener("取消", new SelfDialog.onNoOnclickListener() {
//            @Override
//            public void onNoClick() {
//                selfDialog.dismiss();
//            }
//        });
//        selfDialog.show();
//    }
//}
