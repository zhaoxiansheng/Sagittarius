package com.example.zy.sagittarius.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.example.zy.sagittarius.R;
import com.example.zy.sagittarius.view.StretchableFloatingButton;

public class HomeActivity extends AppCompatActivity
    implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

  private static final String TAG = "com.example.zy.sagittarius.activity.HomeActivity:";

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home);
    init();
  }

  private Toolbar mToolbar;
  private DrawerLayout mDrawerLayout;
  private StretchableFloatingButton sFBtn;
  private LinearLayout llContent;
  private NavigationView mNavigationView;

  private void init() {
    mToolbar = (Toolbar) findViewById(R.id.toolbar);
    mToolbar.setNavigationIcon(R.drawable.menu);
    setSupportActionBar(mToolbar);
    mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
    mNavigationView = (NavigationView) findViewById(R.id.nav_view);
    mNavigationView.setNavigationItemSelectedListener(this);

    mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        mDrawerLayout.openDrawer(Gravity.START);
      }
    });

    DrawerLayout.DrawerListener drawerListener = new DrawerLayout.DrawerListener() {
      @Override public void onDrawerSlide(View drawerView, float slideOffset) {
        llContent.setX(slideOffset * drawerView.getWidth());
      }

      @Override public void onDrawerOpened(View drawerView) {

      }

      @Override public void onDrawerClosed(View drawerView) {

      }

      @Override public void onDrawerStateChanged(int newState) {

      }
    };
    mDrawerLayout.addDrawerListener(drawerListener);

    llContent = (LinearLayout) findViewById(R.id.ll_content);

    sFBtn = (StretchableFloatingButton) findViewById(R.id.view_stretchable_floating_button);
    sFBtn.setOnClickListener(this);
  }

  @Override public void onBackPressed() {
    if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
      mDrawerLayout.closeDrawer(GravityCompat.START);
    } else {
      super.onBackPressed();
    }
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.toolbar_menu, menu);
    return true;
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.back_up:
        break;
      default:
        break;
    }
    return true;
  }

  @Override public void onClick(View v) {
    switch (v.getId()) {
      case R.id.view_stretchable_floating_button:
        sFBtn.startScroll();
        break;
      default:
        break;
    }
  }

  @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
    int id = item.getItemId();

    if (id == R.id.nav_camera) {
      Toast.makeText(this, "camera", Toast.LENGTH_SHORT).show();
    } else if (id == R.id.nav_gallery) {
      Toast.makeText(this, "gallery", Toast.LENGTH_SHORT).show();
    } else if (id == R.id.nav_slideshow) {
      Toast.makeText(this, "slideshow", Toast.LENGTH_SHORT).show();
    } else if (id == R.id.nav_manage) {
      Toast.makeText(this, "manage", Toast.LENGTH_SHORT).show();
    } else if (id == R.id.nav_share) {
      Toast.makeText(this, "share", Toast.LENGTH_SHORT).show();
    } else if (id == R.id.nav_send) {
      Toast.makeText(this, "send", Toast.LENGTH_SHORT).show();
    }

    mDrawerLayout.closeDrawer(GravityCompat.START);
    return true;
  }
}
