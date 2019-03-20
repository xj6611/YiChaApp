package com.yicha.app.Utlis;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.squareup.picasso.Transformation;
import com.yanzhenjie.permission.AndPermission;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by 谢军 on 2018/8/3.
 */
public class AppUtil {
    public static final String KEY_GUIDE="guide";
    public static final String KEY_ZXING="zxing";
    public static final String KEY_USER="UserEntity";
    public static final String KEY_SHOPID="shopId";
    public static final String KEY_KEYWORD="keyword";
    public static final String KEY_KEYCATEID="cateId";
    public static final String KEY_CITY="CityEntity";
    public static  final String KEY_FLAG="flag";
    public static  final String KEY_MESSAGE="message";
    public static  final String KEY_RECORD_LIST="KEY_RECORD_LIST";
    public static final String ACTION_LOGIN_SCUUSSS="www.loginsuceess.com";
    public static final String ACTION_EXIT_SCUUSSS="www.exitsuceess.com";
    public static final String ACTION_UPDATE_SUCCESS="www.updatesuceess.com";
    public static final String ACTION_CHANGE_CITY="www.changecity.com";
    public static  final int REQUEST_CODE_SELECT_HEAD=9999;
    public static  final int REQUEST_CODE_SELECT_PICS=9998;
    public static final int REQUEST_CODE_CROP_HEAD = 9997;
    public static final int REQUEST_SELECT_ADDRESS_CODE=2;

    /**
     * 指定图片转换成Base64字符串
     * @param filePath
     * @return
     */
    public static String decodePic(String filePath){
        try{
            Bitmap bitmap= BitmapFactory.decodeFile(filePath);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 60, stream);
            byte[] b = stream.toByteArray();
            return Base64.encodeToString(b, Base64.DEFAULT);
        }catch (Exception e){
            return null;
        }

    }
    /**
     * 隐藏软键盘
     * @param activity
     */
    public static void hiddenIm(Activity activity){
        InputMethodManager imm =  (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if(imm != null) {
            imm.hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(),
                    0);
        }
    }
    //判断文件是否存在

    public static boolean fileIsExists(String strFile) {
        try {
            File f = new File(strFile);
            if (!f.exists()) {
                return false;
            }

        } catch (Exception e) {
            return false;
        }

        return true;
    }
    public static void verifyStoragePermissions(Activity context) {

        // 先判断是否有权限。
        if (AndPermission.hasPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.ACCESS_NETWORK_STATE)) {
            // 有权限，直接do anything.
        } else {
            // 申请权限。
            AndPermission.with(context)
                    .requestCode(100)
                    .permission(Manifest.permission.CAMERA,
                            Manifest.permission.ACCESS_NETWORK_STATE,
                            Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS,
                            Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.READ_PHONE_STATE)
                    .send();

        }
    }
    public static Bitmap stringToBitmap(String string) {
        Bitmap bitmap = null;
        try {
            byte [] input = Base64.decode(string, Base64.DEFAULT);
            bitmap = BitmapFactory.decodeByteArray(input, 0, input.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }
    public static String listToString2(List<Integer> list){
        if(list==null){
            return null;
        }
        StringBuilder result = new StringBuilder();
        boolean first = true;
        //第一个前面不拼接","
        for(Integer string :list) {
            if(first) {
                first=false;
            }else{
                result.append(",");
            }
            result.append(string);
        }
        return result.toString();
    }
    public static String listToString(List<String> list){
        if(list==null){
            return null;
        }
        StringBuilder result = new StringBuilder();
        boolean first = true;
        //第一个前面不拼接","
        for(String string :list) {
            if(first) {
                first=false;
            }else{
                result.append(",");
            }
            result.append(string);
        }
        return result.toString();
    }
    public static boolean isAvilible(Context context, String packageName){
        //获取packagemanager
        final PackageManager packageManager = context.getPackageManager();
        //获取所有已安装程序的包信息
        List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
        //用于存储所有已安装程序的包名
        List<String> packageNames = new ArrayList<String>();
        //从pinfo中将包名字逐一取出，压入pName list中
        if(packageInfos != null){
            for(int i = 0; i < packageInfos.size(); i++){
                String packName = packageInfos.get(i).packageName;
                packageNames.add(packName);
            }
        }
        //判断packageNames中是否有目标程序的包名，有TRUE，没有FALSE
        return packageNames.contains(packageName);
    }
    public static class PicassoTransformation implements Transformation {

        int screenWidth;
        double targetWidth;

        /**
         * @param view  为了得到contenxt对象获得屏幕宽度
         * @param aver  根据屏幕宽度进行的等分
         */
        public PicassoTransformation(View view, int aver){
            WindowManager wm = (WindowManager) view.getContext().getSystemService(Context.WINDOW_SERVICE);
            DisplayMetrics displayMetrics = new DisplayMetrics();
            wm.getDefaultDisplay().getMetrics(displayMetrics);
            //获得屏幕宽度
            screenWidth = displayMetrics.widthPixels;
            targetWidth = screenWidth/aver;
        }

        @Override
        public Bitmap transform(Bitmap bitmap) {
            if (bitmap.getWidth() == 0 || bitmap.getHeight() == 0) {
                return bitmap;
            }
            //得到图片宽高比,每个参数必须强转成double型
            double ratio = (double) bitmap.getWidth() / (double) bitmap.getHeight();

            Bitmap bitmapResult=null;
            if(bitmap!=null){
                bitmapResult = Bitmap.createScaledBitmap(bitmap, (int) (targetWidth+0.5), (int) ((targetWidth / ratio)+0.5), false);
            }
            if (bitmap != bitmapResult) {
                bitmap.recycle();
            }

            return bitmapResult;
        }
        @Override
        public String key() {
            return "transformation" + screenWidth ;
        }
    }

}
