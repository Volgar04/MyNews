package com.nicolappli.mynews.Controllers.Activities;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;
import com.nicolappli.mynews.R;
import com.nicolappli.mynews.Utils.MyAlarmReceiver;
import java.util.Calendar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NotificationsActivity extends AppCompatActivity {

    @BindView(R.id.checkbox_arts)
    AppCompatCheckBox mCheckBoxArts;
    @BindView(R.id.checkbox_business)
    AppCompatCheckBox mCheckBoxBusiness;
    @BindView(R.id.checkbox_science)
    AppCompatCheckBox mCheckBoxScience;
    @BindView(R.id.checkbox_politics)
    AppCompatCheckBox mCheckBoxPolitics;
    @BindView(R.id.checkbox_sports)
    AppCompatCheckBox mCheckBoxSports;
    @BindView(R.id.checkbox_travel)
    AppCompatCheckBox mCheckBoxTravel;
    @BindView(R.id.switch_enable_notifications)
    Switch mSwitchEnableNotifications;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.edt_query)
    EditText mEdtQuery;

    //for data
    public String[] CHECKBOX_VALUES = {"Arts", "Business", "Science", "Politics", "Sports", "Travel"};
    public CheckBox[] checkBoxes;
    public String[] mCheckBoxStatus = new String[6];
    private AlarmManager mAlarmManager;
    private PendingIntent mPendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        ButterKnife.bind(this);

        this.configureAlarm();
        this.getPreferences();
        this.configureToolbar();
    }

    //*****************************************************
    //                    CONFIGURATION
    //*****************************************************

    //configure toolbar
    public void configureToolbar() {
        setSupportActionBar(mToolbar);
        ActionBar ab = getSupportActionBar();
        assert ab != null;
        ab.setDisplayHomeAsUpEnabled(true);
    }

    //configure the alarm
    private void configureAlarm() {
        Intent intent = new Intent(NotificationsActivity.this, MyAlarmReceiver.class);
        mPendingIntent = PendingIntent.getBroadcast(NotificationsActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    //*****************************************************
    //              SAVE OR RETRIEVE DATA
    //*****************************************************

    //save data thanks to shared preferences
    public void savePreferences() {
        SharedPreferences.Editor editor = getSharedPreferences("PREFERENCES", MODE_PRIVATE).edit();
        editor.putBoolean("arts", mCheckBoxArts.isChecked());
        editor.putBoolean("business", mCheckBoxBusiness.isChecked());
        editor.putBoolean("entrepreneurs", mCheckBoxScience.isChecked());
        editor.putBoolean("politics", mCheckBoxPolitics.isChecked());
        editor.putBoolean("sports", mCheckBoxSports.isChecked());
        editor.putBoolean("travel", mCheckBoxTravel.isChecked());
        editor.putBoolean("switch", mSwitchEnableNotifications.isChecked());
        editor.putString("query", mEdtQuery.getText().toString());
        editor.putString("NewDesk", getNewDesk(mCheckBoxStatus));
        editor.apply();
    }

    //get user's preferences to retrieve data when user launch NotificationsActivity
    public void getPreferences() {
        SharedPreferences prefs = getSharedPreferences("PREFERENCES", MODE_PRIVATE);
        if (prefs.getBoolean("arts", false))
            mCheckBoxArts.setChecked(true);
        if (prefs.getBoolean("business", false))
            mCheckBoxBusiness.setChecked(true);
        if (prefs.getBoolean("entrepreneurs", false))
            mCheckBoxScience.setChecked(true);
        if (prefs.getBoolean("politics", false))
            mCheckBoxPolitics.setChecked(true);
        if (prefs.getBoolean("sports", false))
            mCheckBoxSports.setChecked(true);
        if (prefs.getBoolean("travel", false))
            mCheckBoxTravel.setChecked(true);
        if (prefs.getBoolean("switch", false))
            mSwitchEnableNotifications.setChecked(true);
        if (!prefs.getString("query", "").equals("")) {
            mEdtQuery.setText(prefs.getString("query", ""));
        }
    }

    //save data in shared preferences
    public void passingData() {
        SharedPreferences.Editor editor = getSharedPreferences("PREFERENCES", MODE_PRIVATE).edit();
        editor.putString("notification_query", mEdtQuery.getText().toString());
        editor.putString("notification_new_desk", getNewDesk(mCheckBoxStatus));
        editor.apply();
    }

    //load data for MyAlarmManager, return query and all checked checkboxes
    public String[] loadData(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("PREFERENCES", Context.MODE_PRIVATE);
        return new String[]{preferences.getString("notification_query",""), preferences.getString("notification_new_desk","")};
    }

    public void onCheckboxClicked(View view) {
        checkBoxes = new CheckBox[]{mCheckBoxArts, mCheckBoxBusiness, mCheckBoxScience, mCheckBoxPolitics, mCheckBoxSports, mCheckBoxTravel};
        for (int i = 0; i < checkBoxes.length; i++) {
            if (checkBoxes[i].isChecked()) {
                mCheckBoxStatus[i] = CHECKBOX_VALUES[i];
            } else {
                mCheckBoxStatus[i] = null;
            }
        }
    }

    //get new desk thanks to checked checkboxes
    public String getNewDesk(String[] strings) {
        StringBuilder res = new StringBuilder();

        for (String string : strings) {
            if (string != null) {
                res.append("\"");
                res.append(string);
                res.append("\" ");
            }
        }
        return res.toString();
    }

    //put all checkboxes to unchecked
    private void uncheckAllCheckboxes(){
        CheckBox[] checkboxes = new CheckBox[]{mCheckBoxArts, mCheckBoxBusiness, mCheckBoxScience, mCheckBoxPolitics, mCheckBoxSports, mCheckBoxTravel};
        for (CheckBox checkBoxes : checkboxes) {
            checkBoxes.setChecked(false);
        }
    }

    //*****************************************************
    //                  MANAGE ALARM
    //*****************************************************

    //start the alarm every days at 23h59
    private void startAlarm() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 19);
        cal.set(Calendar.MINUTE, 45);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.DATE, 1);

        mAlarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        assert mAlarmManager != null;
        mAlarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, cal.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, mPendingIntent);
        Toast.makeText(this, "Notifications on !", Toast.LENGTH_LONG).show();
    }

    //stop the alarm manager
    private void stopAlarm(){
        mAlarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        assert mAlarmManager != null;
        mAlarmManager.cancel(mPendingIntent);
        Toast.makeText(this, "Notifications off !", Toast.LENGTH_LONG).show();
    }

    //*****************************************************
    //MANAGE SWITCH ACTIONS
    //*****************************************************

    @OnClick(R.id.switch_enable_notifications)
    public void onViewClicked() {
        if(mSwitchEnableNotifications.isChecked()){ // if the switch is checked
            SharedPreferences preferences = getSharedPreferences("PREFERENCES", Context.MODE_PRIVATE);
            this.savePreferences();
            //test if the query is not empty and if at least one checkbox is checked
            if((preferences.getString("query", "").equals("") && getNewDesk(mCheckBoxStatus).isEmpty()) || preferences.getString("query", "").equals("") || getNewDesk(mCheckBoxStatus).isEmpty()){
                Toast.makeText(this, "Please enter text and check at least one box.", Toast.LENGTH_LONG).show();
                mSwitchEnableNotifications.setChecked(false);
                this.savePreferences();
            }else{
                passingData();
                startAlarm();
            }
        }else{ //if the switch is unchecked we reset the query and checkboxes
            mEdtQuery.setText("");
            this.uncheckAllCheckboxes();
            mSwitchEnableNotifications.setChecked(false);
            this.savePreferences();
            this.stopAlarm();
        }
    }
}