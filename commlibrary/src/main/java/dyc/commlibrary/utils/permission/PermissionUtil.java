package dyc.commlibrary.utils.permission;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.support.v4.app.Fragment;

import java.util.ArrayList;

/**
 * 请求权限工具类
 * dingyc
 */
@TargetApi(Build.VERSION_CODES.M)
public class PermissionUtil {
    private static final int MARSHMALLOW = 23;//棉花糖的API版本号
    public class PermissionCode {
        public static final int PERMISSION_RECORD = 65;//麦克风权限请求码
        public static final int PERMISSION_CAMERA = 66;//相机权限请求码
        public static final int PERMISSION_STORAGE = 67;//存储空间权限请求码
        public static final int PERMISSION_LOCATION = 68;//定位权限请求码
        public static final int PERMISSION_CALL = 69;//权限请求码
        public static final int READ_PHONE_STATE=70;//权限 获取手机状态
        public static final int PERMISSION_ALBUM=71;//权限请求相册
    }

    /**
     * 判断单个权限是否获得
     *
     * @param activity
     * @param permission
     * @return
     */
    public static boolean hasPermission(Activity activity, String permission) {
        if (sdkSupport()) return true;
        if (activity.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED)
            return true;
        return false;
    }



    /**
     * 权限相关操作，包括判断是否有权限，没有权限的时候去申请权限等操作
     *
     * @param activity
     * @param permission
     * @param requestCode
     * @param callback
     */

    public static void permissionHandler(Activity activity, String permission, int requestCode, PermissionCallback callback) {
        if (hasPermission(activity, permission)) {
            //已经获得权限
            callback.permissionGranted();
        } else {
            //没有获得权限，现在要去判断用户是否选择了不再询问选项
            if (activity.shouldShowRequestPermissionRationale(permission)) {
                callback.permissionDenial();
                return;
            }
            activity.requestPermissions(new String[]{permission}, requestCode);
        }
    }

    /**
     * 定位需要相关权限
     * @param activity
     */
    public static void loationPermissionHadler(Activity activity,PermissionCallback callback){
        permissionHandler(activity, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.READ_PHONE_STATE,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE}, PermissionCode.PERMISSION_LOCATION, callback);
    }

    /**
     * 定位需要相关权限
     */
    public static void loationPermissionHadler(Fragment fragment, PermissionCallback callback){
        permissionHandler(fragment, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.READ_PHONE_STATE,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE}, PermissionCode.PERMISSION_LOCATION, callback);
    }

    /**
     * 请求拍照权限
     * @param activity
     */
    public static void phoneHermissionHandler(Activity activity,PermissionCallback callback){
        String[] permissions={Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA};
        permissionHandler(activity, permissions, PermissionCode.PERMISSION_CAMERA, callback);
    }

    /**
     * 请求拍照权限
     * @param activity
     */
    public static void phoneHermissionHandler(Fragment fragment, PermissionCallback callback){
        String[] permissions={Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA};
        permissionHandler(fragment, permissions, PermissionCode.PERMISSION_CAMERA, callback);
    }

    /**
     * 请求相册
     * @param activity
     * @param callback
     */
   public static void albumHermissionHandler(Activity activity,PermissionCallback callback){
       String[] permissions={Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE};
       permissionHandler(activity, permissions, PermissionCode.PERMISSION_ALBUM, callback);
   }
    /**
     * 请求相册
     * @param fragment
     * @param callback
     */
    public static void albumHermissionHandler(Fragment fragment, PermissionCallback callback){
        String[] permissions={Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE};
        permissionHandler(fragment, permissions, PermissionCode.PERMISSION_ALBUM, callback);
    }


    /**
     * 权限相关操作，包括判断是否有权限，没有权限的时候去申请权限等操作
     *
     * @param activity
     * @param permissions
     * @param requestCode
     * @param callback
     */
    public static void permissionHandler(Activity activity, String[] permissions, int requestCode, PermissionCallback callback) {

        if (permissions == null || permissions.length <= 0) return;

        ArrayList list = new ArrayList();
        boolean shouldRequestPermission = false;

        //遍历出需要弹出权限选择框的权限
        for (int i = 0; i < permissions.length; i++) {

            if (!hasPermission(activity, permissions[i])) {

                shouldRequestPermission = true;
                if (!activity.shouldShowRequestPermissionRationale(permissions[i])) {
                    list.add(permissions[i]);
                } else {
                    //如有权限已被静止且选择了不再询问，给出提示
                    callback.permissionDenial();
                    return;
                }

            }
        }

        if (shouldRequestPermission && list.size() > 0) {
            String[] strings = new String[list.size()];
            activity.requestPermissions((String[]) (list.toArray(strings)), requestCode);
        } else {
            callback.permissionGranted();
        }
    }


    public static void permissionHandler(Fragment fragment, String[] permissions, int requestCode, PermissionCallback callback) {

        if (permissions == null || permissions.length <= 0) return;

        ArrayList list = new ArrayList();
        boolean shouldRequestPermission = false;

        //遍历出需要弹出权限选择框的权限
        for (int i = 0; i < permissions.length; i++) {

            if (!hasPermission(fragment.getActivity(), permissions[i])) {

                shouldRequestPermission = true;
                if (!fragment.shouldShowRequestPermissionRationale(permissions[i])) {
                    list.add(permissions[i]);
                } else {
                    //如有权限已被静止且选择了不再询问，给出提示
                    callback.permissionDenial();
                    return;
                }

            }
        }

        if (shouldRequestPermission && list.size() > 0) {
            String[] strings = new String[list.size()];
            fragment.requestPermissions((String[]) (list.toArray(strings)), requestCode);
        } else {
            callback.permissionGranted();
        }
    }

    /**
     * SDK不支持运行时权限
     *
     * @return
     */
    public static boolean sdkSupport() {
        if (Build.VERSION.SDK_INT < MARSHMALLOW) return true;
        return false;
    }


    public static boolean isOpenedGPS(Context context) {
        if (context == null) {
            return false;
        }
        LocationManager locationManager
                = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        // 通过GPS卫星定位，定位级别可以精确到街（通过24颗卫星定位，在室外和空旷的地方定位准确、速度快）
        boolean gps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (gps) {
            return true;
        }

        return false;
    }
}
