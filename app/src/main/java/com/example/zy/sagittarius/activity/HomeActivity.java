package com.example.zy.sagittarius.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.example.zy.sagittarius.R;
import com.example.zy.sagittarius.presenter.HomePresenter;
import com.example.zy.sagittarius.presenter.IHomePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author zhaoy
 */
public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, IHomeView {

    @BindView(R.id.rcv_home)
    RecyclerView rcvHome;
    @BindView(R.id.cl_content)
    CoordinatorLayout clContent;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
            Transition explode = TransitionInflater.from(this).inflateTransition(android.R.transition.explode);
            getWindow().setEnterTransition(explode);
        }
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        init();
        initData();
    }

    private View headerView;
    private TextView textUserName;
    private TextView textUserDesc;

    private IHomePresenter homePresenter;

    /**
     * 初始化
     */
    @SuppressLint("RestrictedApi")
    private void init() {
        homePresenter = new HomePresenter(this, this);

        Toolbar mToolbar = findViewById(R.id.toolbar);
        mToolbar.setNavigationIcon(R.mipmap.menu);
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDefaultDisplayHomeAsUpEnabled(true);
        }
        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.main_collapsing);
        collapsingToolbarLayout.setTitle("哆啦A梦");
        navView.setNavigationItemSelectedListener(this);

        headerView = navView.getHeaderView(0);
        textUserName = headerView.findViewById(R.id.text_user_name);
        textUserDesc = headerView.findViewById(R.id.text_user_description);

        mToolbar.setNavigationOnClickListener(view ->
                drawerLayout.openDrawer(Gravity.START)
        );

        DrawerLayout.DrawerListener drawerListener = new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                clContent.setX(slideOffset * drawerView.getWidth());
            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        };
        drawerLayout.addDrawerListener(drawerListener);

        clContent = findViewById(R.id.cl_content);

        rcvHome = findViewById(R.id.rcv_home);
        rcvHome.setLayoutManager(new LinearLayoutManager(this));
    }

    /**
     * 回退按钮
     */
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.back_up:
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    public static void activityIntent(Activity activity) {
        Intent intent = new Intent(activity, HomeActivity.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(activity).toBundle());
        } else {
            activity.startActivity(intent);
        }
    }

    private void initData() {
        textUserName.setText("赵宇");
        textUserDesc.setText("我是一个偶尔想静静的话痨");
    }
}
