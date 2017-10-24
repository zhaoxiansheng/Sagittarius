package com.example.zy.sagittarius.activity;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.zy.sagittarius.R;
import com.example.zy.sagittarius.adapter.HomeAdapter;
import com.example.zy.sagittarius.bean.Latest;
import com.example.zy.sagittarius.model.Themes;
import com.example.zy.sagittarius.presenter.HomePresenter;
import com.example.zy.sagittarius.presenter.IHomePresenter;

/**
 * Created on 2017/10/10.
 * 主页另一种形式的布局
 *
 * @author zhaoy
 */
public class CollapsingActivity extends AppCompatActivity implements IHomeView {

    private RecyclerView rcvHomeCollapsing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collapsing);
        initData();
    }

    private void initData() {
        rcvHomeCollapsing = (RecyclerView) findViewById(R.id.rcv_home_collapsing);
        IHomePresenter homePresenter = new HomePresenter(this, this);
        homePresenter.getLatest();
        rcvHomeCollapsing.setLayoutManager(new LinearLayoutManager(this));
        rcvHomeCollapsing.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void getTopicList(Themes themes) {

    }

    @Override
    public void getLatestList(Latest latest) {
        HomeAdapter homeAdapter = new HomeAdapter(this, latest);
        rcvHomeCollapsing.setAdapter(homeAdapter);
    }

    public static void activityIntent(Activity activity) {
        Intent intent = new Intent(activity, CollapsingActivity.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(activity).toBundle());
        } else {
            activity.startActivity(intent);
        }
    }
}
