package com.example.zy.sagittarius.view;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.example.zy.sagittarius.R;

public class HomeActivity extends AppCompatActivity {

  private static final String TAG = "com.example.zy.sagittarius.view.HomeActivity:";
  private Toolbar toolbar;
  private DrawerLayout drawerLayout;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home);

    toolbar = (Toolbar) findViewById(R.id.toolbar);
    drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
    toolbar.setNavigationIcon(R.drawable.menu);
    setSupportActionBar(toolbar);

    toolbar.setNavigationOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        Toast.makeText(HomeActivity.this, TAG, Toast.LENGTH_SHORT).show();
        drawerLayout.openDrawer(Gravity.START);
      }
    });
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.toolbar_menu, menu);
    return true;
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.back_up:
        Toast.makeText(this, TAG, Toast.LENGTH_SHORT).show();
        break;
      default:
        break;
    }
    return true;
  }
}
