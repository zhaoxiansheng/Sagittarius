package com.example.zy.sagittarius.presenter;

import android.content.Context;

import com.example.zy.sagittarius.activity.IHomeView;

/**
 * Created on 2017/9/24.
 *
 * @author zhaoy
 */

public class HomePresenter implements IHomePresenter {

    private IHomeView iHomeView;
    private Context context;

    public HomePresenter(Context context, IHomeView iHomeView) {
        this.context = context;
        this.iHomeView = iHomeView;
    }
}
