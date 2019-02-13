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
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchArticlesActivity extends AppCompatActivity {

    // BinView CheckBoxes
    @BindView(R.id.checkbox_arts)
    CheckBox mCheckBoxArts;
    @BindView(R.id.checkbox_business)
    CheckBox mCheckBoxBusiness;
    @BindView(R.id.checkbox_entrepreneurs)
    CheckBox mCheckBoxEntrepreneurs;
    @BindView(R.id.checkbox_politics)
    CheckBox mCheckBoxPolitics;
    @BindView(R.id.checkbox_sports)
    CheckBox mCheckBoxSports;
    @BindView(R.id.checkbox_travel)
    CheckBox mCheckBoxTravel;
    // BindView EditText and Button (query, begin_date, end_date and search_button)
    @BindView(R.id.edt_query)
    EditText mEditTextQuery;
    @BindView(R.id.edt_date_begin)
    EditText mEditTextBeginDate;
    @BindView(R.id.edt_date_end)
    EditText mEditTextEndDate;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    public String[] CHECKBOX_VALUES = {"Arts", "Business", "Entrepreneurs", "Politics", "Sports", "Travel"};
    public CheckBox[] checkBoxes;
    public String[] mCheckBoxStatus = new String[6];
    public CheckBox[] mCheckBoxes;
    public String mQuery;
    public String mBeginDate, mEndDate;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_articles);
        ButterKnife.bind(this);

        this.configureToolbar();

        // Initialise checkboxes list
        this.mCheckBoxes = new CheckBox[]{mCheckBoxArts, mCheckBoxBusiness, mCheckBoxEntrepreneurs, mCheckBoxPolitics, mCheckBoxSports, mCheckBoxTravel};

        try {
            Log.i("config", "name : " + Util.getProperty("name", getApplicationContext()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void onCheckboxClicked(View view) {
        checkBoxes = new CheckBox[]{mCheckBoxArts, mCheckBoxBusiness, mCheckBoxEntrepreneurs, mCheckBoxPolitics, mCheckBoxSports, mCheckBoxTravel};
        for (int i = 0; i < checkBoxes.length; i++) {
            if (checkBoxes[i].isChecked()) {
                mCheckBoxStatus[i] = CHECKBOX_VALUES[i];
            } else {
                mCheckBoxStatus[i] = null;
            }
        }
    }

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

    public void getQueryAndDates() {
        mQuery = mEditTextQuery.getText().toString();
        mBeginDate = mEditTextBeginDate.getText().toString();
        mEndDate = mEditTextEndDate.getText().toString();
        //Log.i("SearchArticles Tag", "Query = " + mQuery + ", begin date = " + mBeginDate + ", end date = " + mEndDate);
    }

    @OnClick(R.id.btn_search)
    public void onViewClickedSearch() {
        getQueryAndDates();
        String errorText;
        if (!getNewDesk(mCheckBoxStatus).isEmpty() && !mQuery.isEmpty()) {
            String[] values = {mQuery, getNewDesk(mCheckBoxStatus), mBeginDate, mEndDate};

            Intent searchResultsActivity = new Intent(SearchArticlesActivity.this, SearchResultsActivity.class);
            searchResultsActivity.putExtra("VALUES_SEARCH_ARTICLES", values);
            startActivity(searchResultsActivity);
            overridePendingTransition(R.anim.enter, R.anim.exit);
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
