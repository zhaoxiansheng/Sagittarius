package com.example.zy.sagittarius.presenter;

import android.content.Context;

import com.example.zy.sagittarius.MyApplication;
import com.example.zy.sagittarius.activity.IHomeView;
import com.example.zy.sagittarius.bean.Latest;
import com.example.zy.sagittarius.model.Themes;
import com.example.zy.sagittarius.net.MyObserver;
import com.example.zy.sagittarius.net.RetrofitFactory;

import io.reactivex.Observable;

/**
 * Created by zhaoy on 2017/9/24.
 */

public class HomePresenter implements IHomePresenter{

    private IHomeView iHomeView;
    private Context context;

    public HomePresenter(Context context, IHomeView iHomeView) {
        this.context = context;
        this.iHomeView = iHomeView;
    }

    @Override
    public void getTopic() {
        Observable<Themes> observable = MyApplication.zhiHuApi.getTopicList();
        RetrofitFactory.doHttpRequest(observable, new MyObserver<Themes>(MyApplication.context, false) {
            @Override
            protected void onSuccess(Themes themes) {
                iHomeView.getTopicList(themes);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        });
    }

    @Override
    public void getLatest() {
        Observable<Latest> latestObservable = MyApplication.zhiHuApi.getLatestList();
        RetrofitFactory.doHttpRequest(latestObservable, new MyObserver<Latest>(MyApplication.context, false) {
            @Override
            protected void onSuccess(Latest latest) {
                iHomeView.getLatestList(latest);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        });
    }
}
