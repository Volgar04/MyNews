package com.nicolappli.mynews.Controllers.Activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Switch;

import com.nicolappli.mynews.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NotificationsActivity extends AppCompatActivity {

    @BindView(R.id.checkbox_arts)
    AppCompatCheckBox mCheckboxArts;
    @BindView(R.id.checkbox_business)
    AppCompatCheckBox mCheckboxBusiness;
    @BindView(R.id.checkbox_science)
    AppCompatCheckBox mCheckboxScience;
    @BindView(R.id.checkbox_politics)
    AppCompatCheckBox mCheckboxPolitics;
    @BindView(R.id.checkbox_sports)
    AppCompatCheckBox mCheckboxSports;
    @BindView(R.id.checkbox_travel)
    AppCompatCheckBox mCheckboxTravel;
    @BindView(R.id.switch_enable_notifications)
    Switch mSwitchEnableNotifications;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        ButterKnife.bind(this);

        this.getPreferences();
        this.configureToolbar();
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.savePreferences();
    }

    public void onCheckboxClicked(View view) {
    }

    public void configureToolbar() {
        setSupportActionBar(mToolbar);
        ActionBar ab = getSupportActionBar();
        assert ab != null;
        ab.setDisplayHomeAsUpEnabled(true);
    }

    public void savePreferences(){
        SharedPreferences.Editor editor = getSharedPreferences("PREFERENCES", MODE_PRIVATE).edit();
        editor.putBoolean("arts", mCheckboxArts.isChecked());
        editor.putBoolean("business", mCheckboxBusiness.isChecked());
        editor.putBoolean("entrepreneurs", mCheckboxScience.isChecked());
        editor.putBoolean("politics", mCheckboxPolitics.isChecked());
        editor.putBoolean("sports", mCheckboxSports.isChecked());
        editor.putBoolean("travel", mCheckboxTravel.isChecked());
        editor.putBoolean("switch",mSwitchEnableNotifications.isChecked());
        editor.apply();
    }

    public void getPreferences(){
        SharedPreferences prefs= getSharedPreferences("PREFERENCES", MODE_PRIVATE);
        if(prefs.getBoolean("arts", false))
            mCheckboxArts.setChecked(true);
        if(prefs.getBoolean("business", false))
            mCheckboxBusiness.setChecked(true);
        if(prefs.getBoolean("entrepreneurs", false))
            mCheckboxScience.setChecked(true);
        if(prefs.getBoolean("politics", false))
            mCheckboxPolitics.setChecked(true);
        if(prefs.getBoolean("sports", false))
            mCheckboxSports.setChecked(true);
        if(prefs.getBoolean("travel", false))
            mCheckboxTravel.setChecked(true);
        if(prefs.getBoolean("switch", false))
            mSwitchEnableNotifications.setChecked(true);
    }
}
