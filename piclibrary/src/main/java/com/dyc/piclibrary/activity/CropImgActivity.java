package com.dyc.piclibrary.activity;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.dyc.piclibrary.CropImageView;
import com.dyc.piclibrary.ImageUtils;
import com.dyc.piclibrary.R;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
/**
 * Created by admin on 2016/4/14.
 * 裁剪头像
 */

public class CropImgActivity extends Activity implements View.OnClickListener{
    String srcPath;
    boolean success = false;
    CropImageView mCropImag;
    TextView save;
    int cropWidth=120;
    int cropHeight=120;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setWindowStatusBarColor(R.color.title_bg_color);
        setContentView(R.layout.activity_cropimage);
        mCropImag= (CropImageView) findViewById(R.id.cropImg);
        save= (TextView) findViewById(R.id.save);
        srcPath = getIntent().getStringExtra("path");
        cropWidth = getIntent().getIntExtra("cropWidth",120);
        cropHeight = getIntent().getIntExtra("cropHeight",120);
        save.setOnClickListener(this);
        if (srcPath!=null&&!"".equals(srcPath)) {
            File file = new File(srcPath);
            if (file != null || file.length() > 0) {
                cropImage();
            }else{
                finish();
            }
        } else {
            finish();
        }

    }

    public void  setWindowStatusBarColor( int colorResId) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(getResources().getColor(colorResId));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void cropImage() {
        try{
            Bitmap bitmap= ImageUtils.getBitmapByPath(srcPath);
            mCropImag.setDrawable(new BitmapDrawable(bitmap),dip2px(CropImgActivity.this, cropWidth),dip2px(CropImgActivity.this, cropHeight));
            success = true;
           // bitmap.recycle();
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    /**
     * 保存图片到本地
     */
    public void writeImage(Bitmap bitmap, String strFilePath) {
        try {
            new File(strFilePath);
            FileOutputStream out = new FileOutputStream(strFilePath);
            if (bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)) {
                out.flush();
                out.close();

            }
            //bitmap.recycle();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        if (success) {
                    String strFilePath = getFilesDir().getAbsolutePath() +System.currentTimeMillis()+".png";
                    writeImage(mCropImag.getCropImage(), strFilePath);
                    Intent mIntent = new Intent();
                    mIntent.putExtra("cropImagePath", strFilePath);
                    setResult(RESULT_OK, mIntent);
        }
        finish();

    }
    /**
     * 根据手机分辨率从dp转成px
     * @param context
     * @param dpValue
     * @return
     */
    public   int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}


