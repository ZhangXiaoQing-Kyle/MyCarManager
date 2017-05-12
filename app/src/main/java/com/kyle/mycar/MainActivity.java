package com.kyle.mycar;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.util.ArrayMap;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import com.github.clans.fab.FloatingActionMenu;
import com.kyle.mycar.Fragment.BaseFragment;
import com.kyle.mycar.Fragment.ExpenseFragment;
import com.kyle.mycar.Fragment.MainFragment;
import com.kyle.mycar.Fragment.MaintenanceFragment;
import com.kyle.mycar.Fragment.OilFragment;

import java.util.ArrayList;
import java.util.HashMap;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String MAINFRAGMENT = "MainFragment";
    private static final String OILFRAGMENT = "OilFragment";
    @BindView(R.id.fab_menu)
    FloatingActionMenu fabMenu;

    private Toolbar mToolbar;
    private DrawerLayout drawer;
    private ArrayList<BaseFragment> mFragmentBackList ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initData();
        initView();
    }


    private void initData() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, mToolbar, R.string
                .navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void initView() {

        MainFragment fragment = new MainFragment();
        if (mFragmentBackList==null){
            mFragmentBackList=new ArrayList();
        }
        mFragmentBackList.add(fragment);
        getSupportFragmentManager().beginTransaction().add(R.id.fl_content, fragment, MAINFRAGMENT).commit();
        getSupportActionBar().setTitle(R.string.history);
    }
    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        //noinspection SimplifiableIfStatement
//        if (id == android.R.id.home) {
//            Toast.makeText(MainActivity.this, "hahahhahaha", Toast.LENGTH_SHORT).show();
//            return true;
//        }
//        if (id == R.id.action_settings) {
//            Toast.makeText(MainActivity.this, "22222222222222", Toast.LENGTH_SHORT).show();
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();
        BaseFragment fragment =null;
        if (id == R.id.nav_history) {
            if ((fragment =mFragmentBackList.get(0))!=null){
                getSupportFragmentManager().beginTransaction()
                        .show(fragment)

                        .commit();
                mToolbar.setTitle(R.string.history);
            }

        } else if (id == R.id.nav_statistics) {
            mToolbar.setTitle(R.string.statistics);
        } else if (id == R.id.nav_oil) {

        } else if (id == R.id.nav_maintenance) {

        } else if (id == R.id.nav_expenses) {

        } else if (id == R.id.nav_setting) {

        } else if (id == R.id.nav_about) {

        }
        fabMenu.setVisibility(View.VISIBLE);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //  浮动按钮点击事件
    @OnClick({R.id.fab_1, R.id.fab_2, R.id.fab_3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fab_1:
                OilFragment fragment = new OilFragment();
                getSupportFragmentManager().beginTransaction().add(R.id.fl_content, fragment, OILFRAGMENT).hide
                        (mFragmentBackList.get(0)).commit();
                mToolbar.setTitle(R.string.oil);
                mToolbar.setBackgroundColor(getResources().getColor(R.color.colorCyan));

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Window window = getWindow();
                    window.setStatusBarColor(getResources().getColor(R.color.colorCyanDark));
                    window.setNavigationBarColor(getResources().getColor(R.color.colorCyanDark));
                }

                break;
            case R.id.fab_2:
                MaintenanceFragment mtFragment = new MaintenanceFragment();
                getSupportFragmentManager().beginTransaction().add(R.id.fl_content, mtFragment)
                            .hide(mFragmentBackList.get(0)).commit();
                mToolbar.setTitle(R.string.maintenance);
                mToolbar.setBackgroundColor(getResources().getColor(R.color.colorPurple));

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Window window = getWindow();
                    window.setStatusBarColor(getResources().getColor(R.color.colorPurpleDark));
                    window.setNavigationBarColor(getResources().getColor(R.color.colorPurpleDark));
                }
                break;
            case R.id.fab_3:
                ExpenseFragment exFragment = new ExpenseFragment();
                getSupportFragmentManager().beginTransaction().add(R.id.fl_content, exFragment)
                        .hide(mFragmentBackList.get(0)).commit();
                mToolbar.setTitle(R.string.expense);
                mToolbar.setBackgroundColor(getResources().getColor(R.color.colorAmber));

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Window window = getWindow();
                    window.setStatusBarColor(getResources().getColor(R.color.colorAmberDark));
                    window.setNavigationBarColor(getResources().getColor(R.color.colorAmberDark));
                }
                break;
        }

        fabMenu.close(true);
        fabMenu.setVisibility(View.GONE);

    }
}
