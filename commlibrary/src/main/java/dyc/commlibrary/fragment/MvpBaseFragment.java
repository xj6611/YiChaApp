package dyc.commlibrary.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dyc.commlibrary.R;
import dyc.commlibrary.activity.BaseView;
import dyc.commlibrary.utils.ProgressDialogUtils;
import dyc.commlibrary.utils.ToastUtils;

/**
 * Created by 刘贵 on 2017/4/11.
 */
public abstract class  MvpBaseFragment extends BaseFragment implements BaseView{
    @Override
    protected abstract View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);


    @Override
    protected abstract void initData();



    @Override
    protected abstract View getLoadingTargetView();



    @Override
    public void showProgress(String msg) {
        ProgressDialogUtils.showDialog(getContext(),msg);
    }

    @Override
    public void dismissProgress() {
        ProgressDialogUtils.dismissDialog(getContext());
    }

    @Override
    public void showToast(String msg) {
        ToastUtils.showShort(getContext(),msg);
    }

    @Override
    public void showNetError() {
        ToastUtils.showShort(getContext(), R.string.library_net_error);
    }
}
