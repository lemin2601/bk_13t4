package com.leemin.minhtrung.ateamnews;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import com.facebook.Profile;
import com.leemin.minhtrung.ateamnews.Adapters.ViewPagerAdapter;
import com.leemin.minhtrung.ateamnews.Fragments.MyFragment;
import com.leemin.minhtrung.ateamnews.Login.LoginFragment;
import com.leemin.minhtrung.ateamnews.Settings.SettingsActivity;
import com.leemin.minhtrung.ateamnews.bean.Category;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainViewActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private  ArrayList<Category> tabs;
    FragmentTabHost tabHost;
    ViewPagerAdapter pagerAdapter;
    ViewPager viewPager;
    private TabWidget tabWidget;
    private HorizontalScrollView horizontalScrollView;

    //ArrayList<Category> tabHostCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);
        TextView header_main_profile_name = (TextView) header.findViewById(R.id.header_main_profile_name);
        ImageView header_main_profile_pic = (ImageView) header.findViewById(R.id.header_main_profile_pic);
        TextView header_main_profile_email = (TextView) header.findViewById(R.id.header_main_profile_email);
        // phần dành cho thông tin người dùng fb
        Intent intent = getIntent();
        Profile profile = intent.getParcelableExtra(LoginFragment.PARCEL_KEY);
        if (profile != null) {
            header_main_profile_name.setText(profile.getName());
            header_main_profile_email.setText(profile.getId());
            Picasso.with(getApplicationContext())
                    .load(profile.getProfilePictureUri(100, 100).toString())
                    .into(header_main_profile_pic);
        }
        LayoutInflater inflater = getLayoutInflater();
        View viewAppBarMainView = inflater.inflate(R.layout.app_bar_main_view, null);
        View viewContentMainView = inflater.inflate(R.layout.content_main_view, null);

        //Phần dành cho tab host
        viewPager = (ViewPager) findViewById(R.id.pager);
        tabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        tabWidget = (TabWidget) findViewById(android.R.id.tabs);
        tabHost.setup(this, getSupportFragmentManager(), R.id.realTabContent);

        initializeHorizontalTabs();
 //      initializeTabs();
//        setupTabHost();
        setupTabHostNews();

        pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), tabs);

        viewPager.setAdapter(pagerAdapter);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            /**
             * on swipe select the respective tab
             * */
            @Override
            public void onPageSelected(int position) {
                invalidateOptionsMenu();
                tabHost.setCurrentTab(position);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                invalidateOptionsMenu();
            }
        });

        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                viewPager.setCurrentItem(tabHost.getCurrentTab());
                scrollToCurrentTab();
            }
        });
    }

    private void setupTabHostNews() {
        Category tmp = new Category();
        tabs = tmp.getListCat();
        for(Category category: tabs) {
            tabHost.addTab(tabHost.newTabSpec(String.format("%sTab", category.getName().replace(" ","").toLowerCase())).setIndicator(category.getName()), MyFragment.class, null);
        }
    }

    /**
     * Phần dành cho tabhost
     */
    private void initializeHorizontalTabs() {
        LinearLayout ll = (LinearLayout) tabWidget.getParent();
        horizontalScrollView = new HorizontalScrollView(this);
        horizontalScrollView.setLayoutParams(new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.WRAP_CONTENT));
        ll.addView(horizontalScrollView, 0);
        ll.removeView(tabWidget);
        horizontalScrollView.addView(tabWidget);
        horizontalScrollView.setHorizontalScrollBarEnabled(false);
    }

    private void scrollToCurrentTab() {
        final int screenWidth = getWindowManager().getDefaultDisplay().getWidth();
        final int leftX = tabWidget.getChildAt(tabHost.getCurrentTab()).getLeft();
        int newX = 0;

        newX = leftX + (tabWidget.getChildAt(tabHost.getCurrentTab()).getWidth() / 2) - (screenWidth / 2);
        if (newX < 0) {
            newX = 0;
        }
        horizontalScrollView.scrollTo(newX, 0);
    }
//    private void initializeTabs() {
//        tabs = new String[] { "TV Shows", "Movies", "Music", "News", "Weather" };
//    }

//    private void setupTabHost() {
//
//        for(int i=0; i<tabs.length; i++) {
//            tabHost.addTab(tabHost.newTabSpec(String.format("%sTab", tabs[i].replace(" ","").toLowerCase())).setIndicator(tabs[i]), MyFragment.class, null);
//        }
//    }
    /**
     * End Phần dành cho tabhost
     */


    /**
     * Phần dành cho menu
     */
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if(id == R.id.nav_settings){
             Intent modifySettings=new Intent(getApplicationContext(),SettingsActivity.class);
            startActivity(modifySettings);
            finish();
            return true;
        }
        if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    /**
     *
     * End * Phần dành cho menu
     */

}

