package com.example.zy.sagittarius.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import android.widget.Toast;

import com.bumptech.glide.request.target.SimpleTarget;
import com.example.zy.sagittarius.R;
import com.example.zy.sagittarius.adapter.HomeAdapter;
import com.example.zy.sagittarius.bean.Latest;
import com.example.zy.sagittarius.bean.Others;
import com.example.zy.sagittarius.glideutils.GlideApp;
import com.example.zy.sagittarius.model.Themes;
import com.example.zy.sagittarius.presenter.HomePresenter;
import com.example.zy.sagittarius.presenter.IHomePresenter;
import com.example.zy.sagittarius.view.StretchableFloatingButton;

import java.util.ArrayList;

/**
 * @author zhaoy
 */
public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, IHomeView {

    private Latest latest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
            Transition explode = TransitionInflater.from(this).inflateTransition(android.R.transition.explode);
            getWindow().setEnterTransition(explode);
        }
        setContentView(R.layout.activity_home);

        init();
        initData();
    }

    /**
     * drawerLayout
     */
    private DrawerLayout mDrawerLayout;
    /**
     * 主界面最外层布局
     */
    private CoordinatorLayout clContent;
    /**
     * 抽屉中布局 NavigationView
     */
    private NavigationView mNavigationView;
    private View headerView;
    private TextView textUserName;
    private TextView textUserDesc;
    private ArrayList<Others> others;

    private RecyclerView recyclerViewHome;
    private HomeAdapter homeAdapter;

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
        actionBar.setDefaultDisplayHomeAsUpEnabled(true);
        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.main_collapsing);
        collapsingToolbarLayout.setTitle("哆啦A梦");
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mNavigationView = findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);

        headerView = mNavigationView.getHeaderView(0);
        textUserName = headerView.findViewById(R.id.text_user_name);
        textUserDesc = headerView.findViewById(R.id.text_user_description);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(Gravity.START);
            }
        });

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
        mDrawerLayout.addDrawerListener(drawerListener);

        clContent = findViewById(R.id.cl_content);

        recyclerViewHome = findViewById(R.id.rcv_home);
        recyclerViewHome.setLayoutManager(new LinearLayoutManager(this));
    }

    /**
     * 回退按钮
     */
    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
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
        int id = item.getItemId();
        String name = others.get(id - 1).getName();

        GlideApp.with(this)
                .asBitmap()
                .load(others.get(id - 1).getThumbnail())
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @NonNull com.bumptech.glide.request.transition.Transition<? super Bitmap> transition) {
                        BitmapDrawable drawable = new BitmapDrawable(getResources(), resource);
                        mNavigationView.getHeaderView(0).setBackground(drawable);
                    }
                });
        if (id == 1) {
            Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
        } else if (id == 2) {
            Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
        } else if (id == 3) {
            Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
        } else if (id == 4) {
            Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
        } else if (id == 5) {
            Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
        } else if (id == 6) {
            Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
        } else if (id == 7) {
            Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
        } else if (id == 8) {
            Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
        } else if (id == 9) {
            Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
        } else if (id == 10) {
            Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
        } else if (id == 11) {
            Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
        } else if (id == 12) {
            Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
        }

        mDrawerLayout.closeDrawer(GravityCompat.START);
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

        homePresenter.getTopic();
        homePresenter.getLatest();
    }

    @Override
    public void getTopicList(Themes themes) {
        others = themes.getOthers();
        int i = 0;
        for (Others o : others) {
            i++;
            mNavigationView.getMenu().add(1, i, i, o.getName());
        }

        GlideApp.with(HomeActivity.this)
                .asBitmap()
                .load(others.get(0).getThumbnail())
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable com.bumptech.glide.request.transition.Transition<? super Bitmap> transition) {
                        Drawable drawable = new BitmapDrawable(resource);
                        headerView.setBackground(drawable);
                    }
                });
    }

    @Override
    public void getLatestList(Latest latest) {
        homeAdapter = new HomeAdapter(HomeActivity.this, latest);
        recyclerViewHome.setAdapter(homeAdapter);
    }
}
