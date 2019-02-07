package com.nicolappli.mynews.Controllers.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatCheckBox;
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
    @BindView(R.id.checkbox_entrepreneurs)
    AppCompatCheckBox mCheckboxEntrepreneurs;
    @BindView(R.id.checkbox_politics)
    AppCompatCheckBox mCheckboxPolitics;
    @BindView(R.id.checkbox_sports)
    AppCompatCheckBox mCheckboxSports;
    @BindView(R.id.checkbox_travel)
    AppCompatCheckBox mCheckboxTravel;
    @BindView(R.id.switch_enable_notifications)
    Switch mSwitchEnableNotifications;

    public String[] CHECKBOX_VALUES = {"Arts", "Business", "Entrepreneurs", "Politics", "Sports", "Travel"};
    public CheckBox[] checkBoxes;
    public String[] mCheckBoxStatus = new String[6];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        ButterKnife.bind(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mSwitchEnableNotifications.isChecked()) {
            Log.i("Notif Tag", "Normalement ça s'enregistre dans le fichier de config...!");
        }else{
            Log.i("Notif Tag", "Ca marche pas mon pote !");
        }
    }

    public void onCheckboxClicked(View view) {
        checkBoxes = new CheckBox[]{mCheckboxArts, mCheckboxBusiness, mCheckboxEntrepreneurs, mCheckboxPolitics, mCheckboxSports, mCheckboxTravel};
        for (int i = 0; i < checkBoxes.length; i++) {
            if (checkBoxes[i].isChecked()) {
                mCheckBoxStatus[i] = CHECKBOX_VALUES[i];
            } else {
                mCheckBoxStatus[i] = null;
            }
        }
    }
}
