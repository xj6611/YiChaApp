package dyc.commlibrary.activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import dyc.commlibrary.AppManager;
import dyc.commlibrary.utils.ProgressDialogUtils;
import dyc.commlibrary.utils.ToastUtils;

public class BaseActivity extends AppCompatActivity{
    protected Context mContext = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        //添加Activity到堆栈
        AppManager.getAppManager().addActivity(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //结束Activity&从堆栈中移除
        AppManager.getAppManager().finishActivity(this);
    }

    public void showProgress(String msg){
        ProgressDialogUtils.showDialog(this,msg);
    }
    public void dismissProgress(){
        ProgressDialogUtils.dismissDialog(this);
    }
    public void showToast(String msg){
        ToastUtils.showShort(this,msg);
    }
    public void showNetError(){
        ToastUtils.showShort(this,"网络异常！");
    }
}
