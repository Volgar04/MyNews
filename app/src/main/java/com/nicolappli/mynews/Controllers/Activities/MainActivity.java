package com.nicolappli.mynews.Controllers.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import com.nicolappli.mynews.Adapters.PageAdapter;
import com.nicolappli.mynews.R;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    //FOR DESIGN
    private DrawerLayout mDrawerLayout;
    private Toolbar mToolbar;
    private ViewPager mViewPager;

    //FOR FRAGMENTS
    private String tabTitle[] = {"TOP STORIES", "MOST POPULAR", "ARTS", "BUSINESS", "SCIENCE", "POLITICS", "SPORTS", "TRAVEL"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.configureToolbar();
        this.configureViewPagerAndTabs();
        this.configureDrawerLayout();
        this.configureNavigationView();
    }

    @Override
    public void onBackPressed() {
        if (this.mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch(id){
            case R.id.activity_main_drawer_top_stories :
                mViewPager.setCurrentItem(0);
                break;
            case R.id.activity_main_drawer_most_popular:
                mViewPager.setCurrentItem(1);
                break;
            case R.id.activity_main_drawer_search_articles:
                Intent searchArticlesActivity = new Intent(MainActivity.this, SearchArticlesActivity.class);
                startActivity(searchArticlesActivity);
                break;
            case R.id.activity_main_drawer_arts:
                mViewPager.setCurrentItem(2);
                break;
            case R.id.activity_main_drawer_Business:
                mViewPager.setCurrentItem(3);
                break;
            case R.id.activity_main_drawer_sciences:
                mViewPager.setCurrentItem(4);
                break;
            case R.id.activity_main_drawer_politics:
                mViewPager.setCurrentItem(5);
                break;
            case R.id.activity_main_drawer_sports:
                mViewPager.setCurrentItem(6);
                break;
            case R.id.activity_main_drawer_travel:
                mViewPager.setCurrentItem(7);
                break;
            case R.id.activity_main_drawer_notifications:
                Intent notificationsActivity = new Intent(MainActivity.this, NotificationsActivity.class);
                startActivity(notificationsActivity);
                break;
            case R.id.activity_main_drawer_about:
                break;
            default:
                break;
        }

        this.mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu and add it to the toolbar
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle actions on menu items
        switch (item.getItemId()){
            case R.id.menu_activity_main_help:
                Toast.makeText(this,"section help", Toast.LENGTH_LONG).show();
                return true;
            case R.id.menu_activity_main_about:
                Toast.makeText(this,"section about", Toast.LENGTH_LONG).show();
                return true;
            case R.id.menu_activity_main_notifications:
                Intent notificationsActivity = new Intent(MainActivity.this, NotificationsActivity.class);
                startActivity(notificationsActivity);
                return true;
            case R.id.menu_activity_main_search:
                Intent searchArticlesActivity = new Intent(MainActivity.this, SearchArticlesActivity.class);
                startActivity(searchArticlesActivity);
                return true;
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void configureToolbar(){
        // Get the toolbar view inside the activity
        this.mToolbar = findViewById(R.id.toolbar);
        // Sets the Toolbar
        setSupportActionBar(mToolbar);
    }

    private void configureViewPagerAndTabs(){
        TabLayout tabLayout = findViewById(R.id.activity_main_tabs);
        for (int i = 0; i < 8; i++) {
            tabLayout.addTab(tabLayout.newTab().setText(tabTitle[i]));
        }
        tabLayout.setTabMode(TabLayout.GRAVITY_FILL);

        PageAdapter pageAdapter = new PageAdapter(getSupportFragmentManager());
        mViewPager=findViewById(R.id.activity_main_viewpager);
        mViewPager.setAdapter(pageAdapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void configureDrawerLayout(){
        this.mDrawerLayout= findViewById(R.id.activity_main_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void configureNavigationView(){
        NavigationView navigationView = findViewById(R.id.activity_main_nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
}
