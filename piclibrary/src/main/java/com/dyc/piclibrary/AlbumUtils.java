package com.dyc.piclibrary;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.File;
import java.io.IOException;

/**
 * 相册工具类
 * Created by dyc on 2016/11/4.
 */
public class AlbumUtils {
    public static final int CODE_FOR_TAKE_PIC = 3;//调用拍照CDOE
    public static final int PHOTO_REQUEST_CUT = 4;//图片裁剪CODE
    public int outputX=400,outputY=400;//裁剪后的图片大小

    /**
     * 调用拍照 AlbumUtils.PHOTO_REQUEST_CUT
     * 调用拍照前需要自己判断权限
     * @param activity
     * @return 返回拍照图片路径
     */
    public static String  takePicture(Activity activity){
        String name = "temp"+System.currentTimeMillis();
        if (!new File(CommonUtil.getDataPath(activity)).exists())
            new File(CommonUtil.getDataPath(activity)).mkdirs();
        String  tempPath = CommonUtil.getDataPath(activity) + name + ".jpg";
        File file = new File(tempPath);
        try {
            if (file.exists())
                file.delete();
            file.createNewFile();
        }catch (IOException e){
            e.printStackTrace();
        }
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        activity.startActivityForResult(intent, CODE_FOR_TAKE_PIC);
        return tempPath;
    }


    /**
     * 剪切图片 AlbumUtils.PHOTO_REQUEST_CUT
     * @param uri
     */
    public void crop(Uri uri,Activity activity) {
        // 裁剪图片意图
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // 裁剪框的比例，1：1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // 裁剪后输出图片的尺寸大小
        intent.putExtra("outputX", outputX);
        intent.putExtra("outputY", outputY);
        // 图片格式
        intent.putExtra("outputFormat", "JPEG");
        intent.putExtra("scale", true);
        intent.putExtra("noFaceDetection", true);// 取消人脸识别
        intent.putExtra("return-data", true);// true:不返回uri，false：返回uri
        activity.startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }
}
