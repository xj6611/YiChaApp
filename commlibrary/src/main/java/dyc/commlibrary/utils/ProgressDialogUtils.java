package dyc.commlibrary.utils;

import android.content.Context;

import dyc.commlibrary.R;
import dyc.commlibrary.widget.progress.CustomProgress;

/**
 * 进度条对话框
 */
public class ProgressDialogUtils {
    public static CustomProgress progressDialog;
    /**
     * 显示进度条
     * @param context
     */
    public static void showDialog(Context context){
        if(progressDialog!=null&&progressDialog.isShowing()){
            return;
        }
        progressDialog=CustomProgress.show(context, R.string.progressdialog_default_msg);
    }

    /**
     * 显示进度条
     * @param context
     */
    public static void showDialog(Context context,String text){
        if(progressDialog!=null&&progressDialog.isShowing()){
            return;
        }
        progressDialog=CustomProgress.show(context, text);
    }

    /**
     * 显示进度条
     * @param context
     */
    public static void showDialog(Context context,int msgId){
        if(progressDialog!=null&&progressDialog.isShowing()){
            return;
        }
        progressDialog=CustomProgress.show(context, msgId);
    }

    public static void dismissDialog(Context context){
        if(progressDialog!=null) {
            progressDialog.dismiss();
        }
    }
}
