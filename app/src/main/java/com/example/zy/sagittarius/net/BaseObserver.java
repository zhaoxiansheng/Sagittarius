package com.example.zy.sagittarius.net;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import com.example.zy.sagittarius.utils.NetworkUtils;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created on 2017/9/21.
 * 封装
 *
 * @author zhaoy
 */

public abstract class BaseObserver<T> implements Observer<T> {

    private Context mContext;
    private ProgressDialog progressDialog;
    private boolean isShow;

    protected BaseObserver(Context mContext, boolean isShow) {
        this.mContext = mContext;
        this.isShow = isShow;
        if (isShow) {
            initProgressDialog();
        }
    }

    /**
     * 请求成功时 回掉函数
     *
     * @param t 请求成功时返回的结果
     */
    protected abstract void onSuccess(T t);

    @Override
    public void onSubscribe(Disposable d) {
        showProgressDialog();
    }

    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

    //同一做失败处理，必须实现
    @Override
    public void onError(Throwable e) {
        errorDo(e);
        dismissProgressDialog();
    }

    @Override
    public void onComplete() {
        dismissProgressDialog();
    }

    private void initProgressDialog() {
        if (progressDialog == null && mContext != null) {
            progressDialog = new ProgressDialog(mContext);
            progressDialog.setCancelable(false);
        }
    }

    private void showProgressDialog() {
        if (!isShow) {
            return;
        }
        if (progressDialog == null || mContext == null) {
            return;
        }
        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    private void dismissProgressDialog() {
        if (!isShow) {
            return;
        }
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    private void errorDo(Throwable e) {
        if (mContext == null) {
            return;
        }
        if (e instanceof SocketTimeoutException) {
            Toast.makeText(mContext, "网络中断，请检查你的网络", Toast.LENGTH_SHORT).show();
            NetworkUtils.showWifiDlg(mContext);
        } else if (e instanceof ConnectException) {
            Toast.makeText(mContext, "网络中断，请检查你的网络", Toast.LENGTH_SHORT).show();
            NetworkUtils.showWifiDlg(mContext);
        } else {
            Toast.makeText(mContext, "错误" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
