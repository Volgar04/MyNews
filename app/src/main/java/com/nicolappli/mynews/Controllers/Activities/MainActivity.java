package com.nicolappli.mynews.Controllers.Activities;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import com.nicolappli.mynews.Adapters.PageAdapter;
import com.nicolappli.mynews.R;
import com.nicolappli.mynews.Utils.MyAlarmReceiver;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private PendingIntent mPendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.configureToolbar();
        this.configureViewPagerAndTabs();
        //this.configureAlarmManager();
        //this.startAlarm();
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
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void configureToolbar(){
        // Get the toolbar view inside the activity
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.activity_main_toolbar);
        // Change the color of the overflow button
        Objects.requireNonNull(toolbar.getOverflowIcon()).setColorFilter(ContextCompat.getColor(this,R.color.white), PorterDuff.Mode.SRC_ATOP);
        // Sets the Toolbar
        setSupportActionBar(toolbar);
    }

    private void configureViewPagerAndTabs(){
        // Get ViewPager from layout
        ViewPager pager = findViewById(R.id.activity_main_viewpager);
        // Set Adapter PageAdapter and glue it together
        pager.setAdapter(new PageAdapter(getSupportFragmentManager()) {
        });
        // Get TabLayout from layout
        TabLayout tabs= findViewById(R.id.activity_main_tabs);
        // Glue TabLayout and ViewPager together
        tabs.setupWithViewPager(pager);
        // Design purpose. Tabs have the same width
        tabs.setTabMode(TabLayout.GRAVITY_FILL);
    }

    private void configureAlarmManager(){
        Intent alarmIntent = new Intent(MainActivity.this, MyAlarmReceiver.class);
        mPendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    private void startAlarm(){
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        assert manager != null;
        manager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,0,AlarmManager.INTERVAL_FIFTEEN_MINUTES,mPendingIntent);
        Toast.makeText(this, "Alarm set !", Toast.LENGTH_SHORT).show();
    }
}
