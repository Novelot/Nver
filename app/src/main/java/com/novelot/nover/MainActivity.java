package com.novelot.nover;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //标题栏透明
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            // 透明状态栏
//            getWindow().addFlags(
//                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            // 透明导航栏
//            getWindow().addFlags(
//                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        }
        //布局
        setContentView(R.layout.activity_main);
        //初始化toolbar
        initToolbar();
        //fragment
        RainbowFragment fragment = new RainbowFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.main_content_frame, fragment).commit();

    }

    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitleTextColor(Color.WHITE);
        mToolbar.setTitle(R.string.rainbow);//必须在setSupportActionBar()之前设置,否则默认为应用名
        setSupportActionBar(mToolbar);
//        mToolbar.setLogo(R.mipmap.ic_launcher);

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                mToolbar, R.string.drawer_open, R.string.drawer_close);
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        //
        NavigationView navView = (NavigationView) findViewById(R.id.navigation_view);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                Fragment fragment;
                switch (menuItem.getItemId()) {
                    case R.id.navigation_item_rainbow:

                        fragment = new RainbowFragment();
                        getSupportFragmentManager().beginTransaction().replace
                                (R.id.main_content_frame, fragment).commit();
                        //
                        mToolbar.setTitle(R.string.rainbow);
                        //
                        mDrawerLayout.closeDrawers();
                        //
                        menuItem.setChecked(true);
                        break;
                    case R.id.navigation_item_bbs:

                        fragment = new BbsFragment();
                        getSupportFragmentManager().beginTransaction().replace
                                (R.id.main_content_frame, fragment).commit();
                        //
                        mToolbar.setTitle(R.string.bbs);
                        //
                        mDrawerLayout.closeDrawers();
                        //
                        menuItem.setChecked(true);
                        break;
                    case R.id.navigation_item_settting:

                        fragment = new SettingFragment();
                        getSupportFragmentManager().beginTransaction().replace
                                (R.id.main_content_frame, fragment).commit();
                        //
                        mToolbar.setTitle(R.string.setting);
                        //
                        mDrawerLayout.closeDrawers();
                        //
                        menuItem.setChecked(true);
                        break;
                }
                return false;
            }
        });
    }

    /******************************************************************/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Toast.makeText(MainActivity.this, "设置", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
