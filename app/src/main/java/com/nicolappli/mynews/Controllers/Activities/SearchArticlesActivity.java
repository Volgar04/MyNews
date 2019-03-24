package com.nicolappli.mynews.Controllers.Activities;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.nicolappli.mynews.R;
import com.nicolappli.mynews.Utils.Util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchArticlesActivity extends AppCompatActivity {

    // BinView CheckBoxes
    @BindView(R.id.checkbox_arts) CheckBox mCheckBoxArts;
    @BindView(R.id.checkbox_business) CheckBox mCheckBoxBusiness;
    @BindView(R.id.checkbox_science) CheckBox mCheckBoxScience;
    @BindView(R.id.checkbox_politics) CheckBox mCheckBoxPolitics;
    @BindView(R.id.checkbox_sports) CheckBox mCheckBoxSports;
    @BindView(R.id.checkbox_travel) CheckBox mCheckBoxTravel;
    // BindView EditText and Button (query, begin_date, end_date and search_button)
    @BindView(R.id.edt_query) EditText mEditTextQuery;
    @BindView(R.id.edt_date_begin) EditText mEditTextBeginDate;
    @BindView(R.id.edt_date_end) EditText mEditTextEndDate;
    @BindView(R.id.toolbar) Toolbar mToolbar;

    public String[] CHECKBOX_VALUES = {"Arts", "Business", "Science", "Politics", "Sports", "Travel"};
    public CheckBox[] checkBoxes;
    public String[] mCheckBoxStatus = new String[6];
    public CheckBox[] mCheckBoxes;
    public String mQuery;
    public String mBeginDate, mEndDate;
    private Util mUtil = new Util();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_articles);
        ButterKnife.bind(this);

        this.configureToolbar();

        // Initialise checkboxes list
        this.mCheckBoxes = new CheckBox[]{mCheckBoxArts, mCheckBoxBusiness, mCheckBoxScience, mCheckBoxPolitics, mCheckBoxSports, mCheckBoxTravel};
    }

    /**
     * Test which checkboxes is checked
     * @param view every checkbox as one view
     */
    public void onCheckboxClicked(View view) {
        checkBoxes = new CheckBox[]{mCheckBoxArts, mCheckBoxBusiness, mCheckBoxScience, mCheckBoxPolitics, mCheckBoxSports, mCheckBoxTravel};
        for (int i = 0; i < checkBoxes.length; i++) {
            if (checkBoxes[i].isChecked()) {
                mCheckBoxStatus[i] = CHECKBOX_VALUES[i];
            } else {
                mCheckBoxStatus[i] = null;
            }
        }

        Log.d("testag", "cochées : "+ Arrays.toString(mCheckBoxStatus));
    }

    /**
     * Get sections thanks to checkboxes
     * @param strings The array of checkboxes checked
     * @return new desk
     */
    public String getNewDesk(String[] strings) {
        StringBuilder res = new StringBuilder();

        for (String string : strings) {
            if (string != null) {
                res.append("\"");
                res.append(string);
                res.append("\" ");
            }
        }
        Log.d("testag", "cochées : "+ res.toString());
        return res.toString();
    }

    /**
     * When the user click the button search a new activity is launched
     */
    @OnClick(R.id.btn_search)
    public void onViewClickedSearch() {
        String errorText;
        mQuery = mEditTextQuery.getText().toString();
        mBeginDate = mEditTextBeginDate.getText().toString();
        mEndDate = mEditTextEndDate.getText().toString();

        if (!getNewDesk(mCheckBoxStatus).isEmpty() && !mQuery.isEmpty()) { // if minimum a checkbox is checked and if the query is not empty

            if(mBeginDate.isEmpty()){ //if user don't specify a begin date, the current date -1 year is set
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.YEAR, -1);
                mBeginDate = new SimpleDateFormat("ddMMyyyy", Locale.getDefault()).format(cal.getTime());
            }
            if(mEndDate.isEmpty()){ // if user don't specify a end date, the current date is set
                mEndDate = new SimpleDateFormat("ddMMyyyy", Locale.getDefault()).format(new Date());
            }



            String[] values = {mQuery, getNewDesk(mCheckBoxStatus), mUtil.parseDateToyyyyMMdd(mBeginDate), mUtil.parseDateToyyyyMMdd(mEndDate)};

            Log.d("nicotagtest", "values = " + values[0]);
            Log.d("nicotagtest", "values = " + values[1]);
            Log.d("nicotagtest", "values = " + values[2]);
            Log.d("nicotagtest", "values = " + values[3]);

            Intent searchResultsActivity = new Intent(SearchArticlesActivity.this, SearchResultsActivity.class);
            searchResultsActivity.putExtra("VALUES_SEARCH_ARTICLES", values); // put the values (checkboxes and query) to the result activity
            startActivity(searchResultsActivity);
        } else {
            errorText = "Il faut écrire du text dans la barre de recherche";
            this.alertDialogError(errorText);
        }
    }

    public void alertDialogError(String errorMessage) {
        final AlertDialog.Builder mBuilder = new AlertDialog.Builder(SearchArticlesActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.dialog_search_error, null);
        TextView mErrorDescription = mView.findViewById(R.id.txt_error_description);
        Button mUnderstand = mView.findViewById(R.id.btn_understand);
        mErrorDescription.setText(errorMessage); // set the good error message

        mBuilder.setView(mView);
        final AlertDialog dialog = mBuilder.create();
        dialog.show();
        mUnderstand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
    }

    public void configureToolbar() {
        setSupportActionBar(mToolbar);
        ActionBar ab = getSupportActionBar();
        assert ab != null;
        ab.setDisplayHomeAsUpEnabled(true);
    }
}
