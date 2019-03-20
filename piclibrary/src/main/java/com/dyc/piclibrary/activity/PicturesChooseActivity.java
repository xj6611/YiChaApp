package com.dyc.piclibrary.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dyc.piclibrary.AlbumBitmapCacheHelper;
import com.dyc.piclibrary.photo.CommonAdapter;
import com.dyc.piclibrary.photo.ImageFloder;
import com.dyc.piclibrary.R;
import com.dyc.piclibrary.photo.ListImageDirPopupWindow;
import com.dyc.piclibrary.photo.ViewHolder;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * Created by liq on 2017/3/17.
 */

public class PicturesChooseActivity extends Activity implements ListImageDirPopupWindow.OnImageDirSelected{
    private int mScreenHeight;
    private GridView mGirdView;
    private TextView mChooseDir;
    private TextView mImageCount;
    private RelativeLayout mBottomLy;
    private ProgressDialog mProgressDialog;
    private File mImgDir;
    private List<String> mImgs;
    private MyAdapter mAdapter;
    private int totalCount = 0;
    /**
     * 扫描拿到所有的图片文件夹
     */
    private List<ImageFloder> mImageFloders = new ArrayList<ImageFloder>();
    private ListImageDirPopupWindow mListImageDirPopupWindow;
    /**
     * 临时的辅助类，用于防止同一个文件夹的多次扫描
     */
    private HashSet<String> mDirPaths = new HashSet<String>();
    /**
     * 存储文件夹中的图片数量
     */
    private int mPicsSize;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_choose);
        DisplayMetrics outMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
        mScreenHeight = outMetrics.heightPixels;

        initView();
        getImages();
        initEvent();
    }

    /**
     * 初始化View
     */
    private void initView() {
        mGirdView = (GridView) findViewById(R.id.id_gridView);
        mChooseDir = (TextView) findViewById(R.id.id_choose_dir);
        mImageCount = (TextView) findViewById(R.id.id_total_count);

        mBottomLy = (RelativeLayout) findViewById(R.id.id_bottom_ly);
        ((TextView)findViewById(R.id.tv_title)).setText("取消");
        findViewById(R.id.tv_title).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.iv_back).setVisibility(View.GONE);
        ((TextView)findViewById(R.id.tv_titlee)).setText("手机相册");
        findViewById(R.id.tv_titlee).setVisibility(View.VISIBLE);
    }
    private void initEvent() {
        /**
         * 为底部的布局设置点击事件，弹出popupWindow
         */
        mBottomLy.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mListImageDirPopupWindow
                        .setAnimationStyle(R.style.anim_popup_dir);
                mListImageDirPopupWindow.showAsDropDown(mBottomLy, 0, 0);

                // 设置背景颜色变暗
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = .3f;
                getWindow().setAttributes(lp);
            }
        });
    }

    /**
     * 利用ContentProvider扫描手机中的图片，此方法在运行在子线程中 完成图片的扫描，最终获得jpg最多的那个文件夹
     */
    private void getImages() {
        if (!Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            Toast.makeText(this, "暂无外部存储", Toast.LENGTH_SHORT).show();
            return;
        }
        // 显示进度条
        mProgressDialog = ProgressDialog.show(this, null, "正在加载...");

        new Thread(new Runnable() {
            public void run() {

                String firstImage = null;

                Uri mImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                ContentResolver mContentResolver = PicturesChooseActivity.this
                        .getContentResolver();
                // 只查询jpeg和png的图片
                Cursor mCursor = mContentResolver.query(mImageUri, null,
                        MediaStore.Images.Media.MIME_TYPE + "=? or "
                                + MediaStore.Images.Media.MIME_TYPE + "=?",
                        new String[] { "image/jpeg", "image/png" },
                        MediaStore.Images.Media.DATE_MODIFIED);

                while (mCursor.moveToNext()) {
                    // 获取图片的路径
                    String path = mCursor.getString(mCursor
                            .getColumnIndex(MediaStore.Images.Media.DATA));

                    // 拿到第一张图片的路径
                    if (firstImage == null)
                        firstImage = path;
                    // 获取该图片的父路径名
                    File parentFile = new File(path).getParentFile();
                    if (parentFile == null)
                        continue;
                    String dirPath = parentFile.getAbsolutePath();
                    ImageFloder imageFloder = null;
                    // 利用一个HashSet防止多次扫描同一个文件夹（不加这个判断，图片多起来还是相当恐怖的~~）
                    if (mDirPaths.contains(dirPath))
                    {
                        continue;
                    } else
                    {
                        mDirPaths.add(dirPath);
                        // 初始化imageFloder
                        imageFloder = new ImageFloder();
                        imageFloder.setDir(dirPath);
                        imageFloder.setFirstImagePath(path);
                    }

                    int picSize = parentFile.list(new FilenameFilter()
                    {
                        public boolean accept(File dir, String filename)
                        {
                            if (filename.endsWith(".jpg")
                                    || filename.endsWith(".png")
                                    || filename.endsWith(".jpeg"))
                                return true;
                            return false;
                        }
                    }).length;
                    totalCount += picSize;

                    imageFloder.setCount(picSize);
                    mImageFloders.add(imageFloder);

                    if (picSize > mPicsSize)
                    {
                        mPicsSize = picSize;
                        mImgDir = parentFile;
                    }
                }
                mCursor.close();

                // 扫描完成，辅助的HashSet也就可以释放内存了
                mDirPaths = null;

                // 通知Handler扫描图片完成
                mHandler.sendEmptyMessage(0x110);

            }
        }).start();

    }
    private Handler mHandler = new Handler()
    {
        public void handleMessage(android.os.Message msg)
        {
            mProgressDialog.dismiss();
            // 为View绑定数据
            data2View();
            // 初始化展示文件夹的popupWindw
            initListDirPopupWindw();
        }
    };
    /**
     * 为View绑定数据
     */
    private void data2View() {
        if (mImgDir == null) {
            Toast.makeText(getApplicationContext(), "擦，一张图片没扫描到",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        mImgs = Arrays.asList(mImgDir.list());
        /**
         * 可以看到文件夹的路径和图片的路径分开保存，极大的减少了内存的消耗；
         */
        mAdapter = new MyAdapter(getApplicationContext(), mImgs,
                R.layout.grid_item, mImgDir.getAbsolutePath());
        mGirdView.setAdapter(mAdapter);
        mImageCount.setText(totalCount + "张");
    };
    /**
     * 初始化展示文件夹的popupWindw
     */
    private void initListDirPopupWindw() {
        mListImageDirPopupWindow = new ListImageDirPopupWindow(
                ViewGroup.LayoutParams.MATCH_PARENT, (int) (mScreenHeight * 0.7),
                mImageFloders, LayoutInflater.from(getApplicationContext())
                .inflate(R.layout.list_dir, null));

        mListImageDirPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener()
        {

            public void onDismiss()
            {
                // 设置背景颜色变暗
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1.0f;
                getWindow().setAttributes(lp);
            }
        });
        // 设置选择文件夹的回调
        mListImageDirPopupWindow.setOnImageDirSelected(PicturesChooseActivity.this);
    }
    public void selected(ImageFloder floder)
    {

        mImgDir = new File(floder.getDir());
        mImgs = Arrays.asList(mImgDir.list(new FilenameFilter()
        {
            public boolean accept(File dir, String filename)
            {
                if (filename.endsWith(".jpg") || filename.endsWith(".png")
                        || filename.endsWith(".jpeg"))
                    return true;
                return false;
            }
        }));
        /**
         * 可以看到文件夹的路径和图片的路径分开保存，极大的减少了内存的消耗；
         */
        mAdapter = new MyAdapter(this, mImgs,
                R.layout.grid_item, mImgDir.getAbsolutePath());
        mGirdView.setAdapter(mAdapter);
        // mAdapter.notifyDataSetChanged();
        mImageCount.setText(floder.getCount() + "张");
        mChooseDir.setText(floder.getName());
        mListImageDirPopupWindow.dismiss();

    }


    class MyAdapter extends CommonAdapter<String> {

        /**
         * 用户选择的图片，存储为图片的完整路径
         */
//        public static List<String> mSelectedImage = new LinkedList<String>();

        /**
         * 文件夹路径
         */
        private String mDirPath;
        private Context mContext;

        public MyAdapter(Context context, List<String> mDatas, int itemLayoutId,
                         String dirPath) {
            super(context, mDatas, itemLayoutId);
            this.mDirPath = dirPath;
        }

        @Override
        public void convert(final ViewHolder helper, final String item) {
            //设置no_pic
            helper.setImageResource(R.id.id_item_image, R.drawable.pictures_no);
            //设置no_selected
//		helper.setImageResource(R.id.id_item_select,
//						R.drawable.picture_unselected);
            //设置图片
            helper.setImageByUrl(R.id.id_item_image, mDirPath + "/" + item);

            final ImageView mImageView = helper.getView(R.id.id_item_image);
//		final ImageView mSelect = helper.getView(R.id.id_item_select);

            mImageView.setColorFilter(null);
            //设置ImageView的点击事件
            mImageView.setOnClickListener(new View.OnClickListener() {
                //选择，则将图片变暗，反之则反之
                public void onClick(View v) {
//                    String picPath = data.getStringExtra("data");
//                    Intent intent = new Intent(PicturesChooseActivity.this, CropImgActivity.class);
//                    intent.putExtra("path", com.dyc.piclibrary.ImageUtils.compressPic(mDirPath + "/" + item, PicturesChooseActivity.this));
//                    intent.putExtra("tag", "daiqianPhoto");
//                    startActivity(intent);
//                    startActivityForResult(intent, REQUEST_CODE_CROP_HEAD);
                    Intent data = new Intent();
                    data.putExtra("data", mDirPath + "/" + item);
                    setResult(RESULT_OK, data);
                    finish();
//				// 已经选择过该图片
//				if (mSelectedImage.contains(mDirPath + "/" + item))
//				{
//					mSelectedImage.remove(mDirPath + "/" + item);
//					mSelect.setImageResource(R.drawable.picture_unselected);
//					mImageView.setColorFilter(null);
//				} else
//				// 未选择该图片
//				{
//					mSelectedImage.add(mDirPath + "/" + item);
//					mSelect.setImageResource(R.drawable.pictures_selected);
//					mImageView.setColorFilter(Color.parseColor("#77000000"));
//				}

                }
            });

            /**
             * 已经选择过的图片，显示出选择过的效果
             */
//		if (mSelectedImage.contains(mDirPath + "/" + item))
//		{
//			mSelect.setImageResource(R.drawable.pictures_selected);
//			mImageView.setColorFilter(Color.parseColor("#77000000"));
//		}

        }
    }

    @Override
    public void onBackPressed() {
        AlbumBitmapCacheHelper.getInstance(PicturesChooseActivity.this).clearCache();
        super.onBackPressed();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
