package com.example.zy.sagittarius.model;

import android.widget.Toast;

import com.example.zy.sagittarius.MyApplication;
import com.example.zy.sagittarius.net.BaseObserver;
import com.example.zy.sagittarius.net.RetrofitFactory;

import io.reactivex.Observable;

/**
 * Created by zhaoy on 2017/9/19.
 * 版本检查接口返回
 */

public class VersionModel implements IVersion{

    private String status;

    private String latest;

    private String msg;

    private String url;

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public String getLatest() {
        return latest;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    @Override
    public void checkVersion(final String versionName) {
        Observable<VersionModel> observable = MyApplication.zhiHuApi.checkVersion(versionName);
        RetrofitFactory.doHttpRequest(observable, new BaseObserver<VersionModel>(MyApplication.getContext(), false) {
            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }

            @Override
            protected void onSuccess(VersionModel versionModel) {
                if ("0".equals(versionModel.getStatus())){
                    Toast.makeText(MyApplication.getContext(), "当前为最新版:" + versionModel.getLatest(),
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MyApplication.getContext(), "可以去:" + versionModel.getUrl() +
                                    "下载\n这次更新的内容有:" + versionModel.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
