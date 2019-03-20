package dyc.commlibrary.activity;

/**
 * Created by dingyc on 2017/4/11.
 */
public interface BaseView {
    public void showProgress(String msg);
    public void dismissProgress();
    public void showToast(String msg);
    public void showNetError();
}
