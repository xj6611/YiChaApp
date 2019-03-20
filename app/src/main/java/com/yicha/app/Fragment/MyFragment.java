package com.yicha.app.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.dyc.piclibrary.ImageUtils;
import com.dyc.piclibrary.activity.CropImgActivity;
import com.dyc.piclibrary.activity.PickOrTakeImageActivity;
import com.hss01248.dialog.StyledDialog;
import com.hss01248.dialog.interfaces.MyDialogListener;
import com.hss01248.dialog.interfaces.MyItemDialogListener;
import com.squareup.picasso.Picasso;
import com.yicha.app.Activity.LoginActivity;
import com.yicha.app.Activity.ResetYourPasswordActivity;
import com.yicha.app.BaseApplication;
import com.yicha.app.Class.CircleImageView;
import com.yicha.app.Entity.BaseEntity;
import com.yicha.app.Presenter.UploadHeaderPresenter;
import com.yicha.app.R;
import com.yicha.app.Utlis.AppUtil;
import com.yicha.app.Utlis.RequestBodyUtil;
import com.yicha.app.View.UploadHeaderContact;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dyc.commlibrary.fragment.BaseFragment;
import dyc.commlibrary.utils.GoActivityUtils;
import dyc.commlibrary.utils.ToastUtils;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;
import static com.yicha.app.Utlis.RequestBodyUtil.convertToRequestBody;

/**
 * Created by CYY on 2018/11/17.
 */

public class MyFragment extends BaseFragment implements UploadHeaderContact.View{
    private String sourcePath;
    @BindView(R.id.My_TouXiang_CircleImageView)
    CircleImageView My_TouXiang_CircleImageView;
    @BindView(R.id.ResetYouPassword)
    TextView ResetYouPassword;
    @BindView(R.id.TuiChu_Button)
    Button TuiChu_Button;
    @BindView(R.id.My_name_TextView)
    TextView My_name_TextView;


    private UploadHeaderContact.Presenter presenter;
    private List<File> fileList;
    @Override
    protected View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_layou,container,false);
        ButterKnife.bind(this, view);
        presenter = new UploadHeaderPresenter(this);
        if (!BaseApplication.getInstance().getUserFace().equals("")) {
            Picasso.with(getActivity()).load(BaseApplication.getInstance().getUserFace()).error(R.mipmap.touciang).placeholder(R.mipmap.touciang).into(My_TouXiang_CircleImageView);
        }
        My_name_TextView.setText(BaseApplication.getInstance().getUserName()+"("+BaseApplication.getInstance().getNumber()+")");
        return view;
    }
    @OnClick({R.id.TuiChu_Button, R.id.My_TouXiang_CircleImageView,R.id.ResetYouPassword})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.My_TouXiang_CircleImageView:
                showSexDialog();
                break;
            case R.id.TuiChu_Button:
                showExitDiolog();
                break;
            case R.id.ResetYouPassword:
                Intent intent = new Intent();
                intent.setClass(getActivity(), ResetYourPasswordActivity.class);
                startActivity(intent);
                break;

        }
    }
    @Override
    protected void initData() {

    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }
    private void showExitDiolog() {
        StyledDialog.buildIosAlert("提示", "确定要退出登录吗？", new MyDialogListener() {
            @Override
            public void onFirst() {
            }

            @Override
            public void onSecond() {
                BaseApplication.getInstance().clearUserInfo();
//                SharedPreferences sp = getActivity().getSharedPreferences("info", MODE_PRIVATE);
//                SharedPreferences.Editor ed = sp.edit();
//                ed.putString("name", "");
//                ed.putString("pwd", "");
//                ed.commit();
                Intent intent = new Intent(getActivity(), LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }

            @Override
            public void onThird() {
            }
        }).setBtnText("取消", "确定")
                .show();
    }
    private void showSexDialog() {
        final ArrayList localArrayList = new ArrayList();
        localArrayList.add("打开相册");
        StyledDialog.buildBottomItemDialog(localArrayList, "取消", new MyItemDialogListener() {
            public void onBottomBtnClick() {
            }

            public void onItemClick(CharSequence paramAnonymousCharSequence, int paramAnonymousInt) {
                if (((String) localArrayList.get(paramAnonymousInt)).equals("打开相册")) {
//                    Toast.makeText(LiuCheng_DaiBan_Activity.this, "同意", 0).show();
                    Intent intent2 = new Intent(getActivity(), PickOrTakeImageActivity.class);
                    intent2.putExtra("extra_nums", 1);
                    startActivityForResult(intent2, AppUtil.REQUEST_CODE_SELECT_HEAD);
                }
            }
        }).show();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case AppUtil.REQUEST_CODE_CROP_HEAD:
                    sourcePath = data.getStringExtra("cropImagePath");
                    Log.i("asss","sourcePath=="+sourcePath);
                    My_TouXiang_CircleImageView.setImageURI(Uri.parse(sourcePath));
//                    presenter.requestChangeFace(AppUtil.decodePic(sourcePath));
                    presenter.request();
                    break;
                case AppUtil.REQUEST_CODE_SELECT_HEAD:
                    if (((ArrayList<String>) data.getSerializableExtra("data")).get(0) != null) {
                        String picPath = ((ArrayList<String>) data.getSerializableExtra("data")).get(0);
                        Intent intent = new Intent(getActivity(), CropImgActivity.class);
                        intent.putExtra("path", ImageUtils.compressPic(picPath, getActivity()));
                        startActivityForResult(intent,  AppUtil.REQUEST_CODE_CROP_HEAD);
                    }
                    break;
            }
        }
    }

    @Override
    public Map<String, RequestBody> getParts() {
        Map<String, RequestBody> map = new HashMap<>();
        map.put("uid", convertToRequestBody(String.valueOf(BaseApplication.getInstance().getUserId())));
        map.put("token", convertToRequestBody(String.valueOf(BaseApplication.getInstance().getToken())));
        return map;
    }

    @Override
    public List<MultipartBody.Part> getSoundRecordFile() {
        List<MultipartBody.Part> parts;
        fileList = new ArrayList<>();
        Log.i("asss","sourcePath2222222=="+sourcePath);
        if (!sourcePath.equals("")){
            File file = new File(sourcePath);
            fileList.add(file);
            Log.i("asss","file=="+file);
        }else {
            ToastUtils.showShort(getActivity(),"图片文件路径不存在！");
        }
        parts = RequestBodyUtil.filesToMultipartBodyParts(fileList, "file");
        return parts;
    }

    @Override
    public void Success(BaseEntity value) {
        ToastUtils.showShort(getActivity(),value.getMsg());
        Log.i("asss","value=="+value.getMsg()+"re=="+value.getResult());
    }
}
