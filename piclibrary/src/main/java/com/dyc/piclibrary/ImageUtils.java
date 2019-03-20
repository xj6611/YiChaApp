package com.dyc.piclibrary;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author dyc
 * @version  图片工具类
 */
public class ImageUtils {
	/** 支持最大图片的宽高度 ,超过则有损压缩 */
	public static final int IMAGE_MAX_WIDTH = 1080;
	public static final int IMAGE_MAX_HEIGHT = 1920;
	public static final String DIR="dlb";
	/**
	 * 输出指定宽度图片
	 * @param context
	 * @param largeImagePath
	 *            原始大图路径
	 * @param thumbfilePath
	 *            输出缩略图路径
	 * @param square_size
	 *            输出图片宽度，高度自动计算
	 * @param quality
	 *            输出图片质量
	 * @throws IOException
	 */
	public static void createImageThumbnail(Context context,
											String largeImagePath, String thumbfilePath, int square_size,
											int quality) throws IOException {
		// 原始图片bitmap
		Bitmap cur_bitmap = getBitmapByPath(largeImagePath);
		if (cur_bitmap == null)
			return;
		// 原始图片的高宽
		int[] cur_img_size = new int[] { cur_bitmap.getWidth(),
				cur_bitmap.getHeight() };
		// 计算原始图片缩放后的宽高
		int[] new_img_size = scaleImageSize(cur_img_size, square_size);

		// 生成缩放后的bitmap
		Bitmap thb_bitmap = zoomBitmap(cur_bitmap, new_img_size[0],
				new_img_size[1]);
		// 生成缩放后的图片文件
		saveImageToSD(thumbfilePath, thb_bitmap, quality);
		// FileUtils.deleteFile(largeImagePath);
	}

	/**
	 * 写图片文件到SD卡
	 *
	 * @param descFilePath
	 *            输出图片路径
	 * @param srcBitmap
	 *            原始图片流
	 * @param quality
	 *            输出图片质量
	 * @throws IOException
	 */
	public static void saveImageToSD(String descFilePath, Bitmap srcBitmap,
									 int quality) throws IOException {
		if (srcBitmap != null) {
			try {
				FileOutputStream fos = new FileOutputStream(descFilePath);
				if (srcBitmap.compress(CompressFormat.JPEG, quality, fos)){
					fos.flush();
					fos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 计算缩放图片的宽高度
	 *
	 * @param img_size
	 * @param square_size
	 * @return
	 */
	public static int[] scaleImageSize(int[] img_size, int square_size) {
		if (img_size[0] <= square_size && img_size[1] <= IMAGE_MAX_HEIGHT) {
			return img_size;
		}

		if (img_size[0] > square_size && img_size[1] <= IMAGE_MAX_HEIGHT) {
			double wRatio = square_size / (double) img_size[0];
			return new int[] { (int) (img_size[0] * wRatio),
					(int) (img_size[1] * wRatio) };
		}

		if (img_size[0] <= square_size && img_size[1] > IMAGE_MAX_HEIGHT) {
			double hRatio = IMAGE_MAX_HEIGHT / (double) img_size[1];
			return new int[] { (int) (img_size[0] * hRatio),
					(int) (img_size[1] * hRatio) };
		}

		double wRatio = square_size / (double) img_size[0];
		double hRatio = IMAGE_MAX_HEIGHT / (double) img_size[1];

		double ratio = Math.max(wRatio, hRatio);
		return new int[] { (int) (img_size[0] * ratio),
				(int) (img_size[1] * ratio) };

	}

	/**
	 * 放大缩小图片
	 *
	 * @param bitmap
	 * @param w
	 * @param h
	 * @return
	 */
	public static Bitmap zoomBitmap(Bitmap bitmap, int w, int h) {
		Bitmap newbmp = null;
		if (bitmap != null) {
			int width = bitmap.getWidth();
			int height = bitmap.getHeight();
			Matrix matrix = new Matrix();
			float scaleWidht = ((float) w / width);
			float scaleHeight = ((float) h / height);
			matrix.postScale(scaleWidht, scaleHeight);
			newbmp = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix,
					true);
		}
		return newbmp;
	}

	/**
	 * 获取指定图片路径Bitmap
	 *
	 * @param filePath
	 * @return
	 */
	public static Bitmap getBitmapByPath(String filePath) {
		BitmapFactory.Options opts = new BitmapFactory.Options();
		// 计算原始图片宽高
		int[] cur_img_size = null;
		try {
			cur_img_size = getOrigImgWidthAndHeigth(filePath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		// 如果图片过大,进行压缩处理
		if (cur_img_size[0] > IMAGE_MAX_WIDTH
				|| cur_img_size[1] > IMAGE_MAX_HEIGHT) {
			opts.inSampleSize = (int) Math.pow(
					2.0,
					(int) Math.round(Math.log(IMAGE_MAX_WIDTH
							/ (double) Math.max(cur_img_size[1],
							cur_img_size[0]))
							/ Math.log(0.5)));
		} else {
			opts.inSampleSize = 1;
		}
		opts.inTempStorage = new byte[150 * 1024];
		opts.inPreferredConfig = Bitmap.Config.RGB_565;
		opts.inPurgeable = true;
		opts.inInputShareable = true;
		return getBitmapByPath(filePath, opts);
	}

	public static Bitmap getBitmapByPath(String filePath,
										 BitmapFactory.Options opts) {
		FileInputStream fis = null;
		Bitmap bitmap = null;
		try {
			File file = new File(filePath);
			fis = new FileInputStream(file);
			bitmap = BitmapFactory.decodeStream(fis, null, opts);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (OutOfMemoryError e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
			} catch (Exception e) {
			}
		}
		return bitmap;
	}

	/**
	 * 不加载图片获取原始图片的宽高
	 *
	 * @param largeImagePath
	 * @return
	 * @throws FileNotFoundException
	 */
	public static int[] getOrigImgWidthAndHeigth(String largeImagePath)
			throws FileNotFoundException {
		File file = new File(largeImagePath);
		FileInputStream in = new FileInputStream(file);
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeStream(new BufferedInputStream(in, 16 * 1024),
				null, options);

		int w = options.outWidth;
		int h = options.outHeight;
		return new int[] { w, h };
	}


	/**
	 * 获取图片的角度
	 *
	 * @param path
	 * @return
	 */
	public static int readPictureDegree(String path) {

		int degree = 0;
	if(path!=null){
		try {
			ExifInterface exifInterface = new ExifInterface(path);
			int orientation = exifInterface.getAttributeInt(
					ExifInterface.TAG_ORIENTATION,
					ExifInterface.ORIENTATION_NORMAL);
			switch (orientation) {
				case ExifInterface.ORIENTATION_ROTATE_90:
					degree = 90;
					break;
				case ExifInterface.ORIENTATION_ROTATE_180:
					degree = 180;
					break;
				case ExifInterface.ORIENTATION_ROTATE_270:
					degree = 270;
					break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		return degree;

	}

	/**
	 * 旋转图片
	 *
	 * @param angle
	 *            旋转角度
	 * @param bitmap
	 * @return
	 */
	public static Bitmap rotaingImageView(int angle, Bitmap bitmap) {
		Matrix matrix = new Matrix();
		;
		matrix.postRotate(angle);
		Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0,
				bitmap.getWidth(), bitmap.getHeight(), matrix, true);
		return resizedBitmap;
	}

	public static String rotaingImageViewRuturnFile(int angle, String srcPath,Context ctx){
		Matrix matrix = new Matrix();
		matrix.postRotate(angle);
		Bitmap srcBitmap=  getBitmapByPath(srcPath);

		Bitmap resizedBitmap = Bitmap.createBitmap(srcBitmap, 0, 0,
				srcBitmap.getWidth(),srcBitmap.getHeight(), matrix, true);
		String descFilePath=getFilePath(ctx, ".jpg");
		try {
			saveImageToSD(descFilePath,resizedBitmap, 100);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return descFilePath;
	}

	/**
	 * 压缩图片通用类,图片旋转或自动旋转
	 * @param srcPath
	 * @param ctx
	 * @return
	 */
	public static String compressPic(String srcPath,Context ctx){
		int angle= readPictureDegree(srcPath);
		if(angle==90){
			return rotaingImageViewRuturnFile(angle, srcPath,ctx);
		}else{
			Bitmap bitmap=getBitmapByPath(srcPath);
			String descFilePath=getFilePath(ctx, ".jpg");
			try {
				saveImageToSD(descFilePath,bitmap, 100);
				ctx.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + descFilePath)));
			} catch (IOException e) {
				e.printStackTrace();
			}
			return descFilePath;
		}
	}

	/**
	 * 压缩图片通用类,图片旋转或自动旋转
	 * @param srcPath
	 * @param ctx
	 * @return
	 */
	public static String compressPicSign(String srcPath,Context ctx){
		int angle= readPictureDegree(srcPath);
		if(angle==90){
			return rotaingImageViewRuturnFile(angle, srcPath,ctx);
		}else{
			Bitmap bitmap=getBitmapByPath(srcPath);
//			String descFilePath=getFilePath(ctx, ".jpg");
			try {
				saveImageToSD(srcPath,bitmap, 100);
				ctx.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + srcPath)));
			} catch (IOException e) {
				e.printStackTrace();
			}
			return srcPath;
		}
	}

	public static String rotaingImageViewRuturnFile(int angle, Bitmap bitmap,Context ctx){
		Matrix matrix = new Matrix();
		;
		matrix.postRotate(angle);
		Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0,
				bitmap.getWidth(), bitmap.getHeight(), matrix, true);
		String descFilePath=getFilePath(ctx,".jpg");
		try {
			saveImageToSD(descFilePath,resizedBitmap, 100);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return descFilePath;
	}



	public static String getFilePath(Context ctx,String suffix) {
		String strFilePath = null;
		long time=System.currentTimeMillis();
		// 初始化默认路径
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			strFilePath = Environment.getExternalStorageDirectory()
					.getAbsolutePath() + "/"+DIR+"/";
		}
		// 如果没有SD卡，返回内存路径
		else {
			strFilePath = ctx.getFilesDir().getAbsolutePath() + "/";
		}

		File dir=new File(strFilePath);
		if(!dir.exists()){
			dir.mkdirs();
		}
		// 检查文件夹存不存在，如果不存在则创建
		String filePath=strFilePath+time+suffix;
		File file = new File(filePath);
		if (!file.exists()) {
			// 创建目录
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				Log.i("dyc", "创建文件异常");
			}
		}
		return filePath;
	}




}
