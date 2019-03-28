package com.nicolappli.mynews.Controllers.Activities;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.nicolappli.mynews.R;
import com.nicolappli.mynews.Utils.Util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
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
    @BindView(R.id.checkbox_science)
    CheckBox mCheckBoxScience;
    @BindView(R.id.checkbox_politics)
    CheckBox mCheckBoxPolitics;
    @BindView(R.id.checkbox_sports)
    CheckBox mCheckBoxSports;
    @BindView(R.id.checkbox_travel)
    CheckBox mCheckBoxTravel;
    // BindView EditText and Button (query, begin_date, end_date and search_button)
    @BindView(R.id.edt_query)
    EditText mEditTextQuery;
    @BindView(R.id.txt_choose_date_begin)
    TextView mTxtChooseDateBegin;
    @BindView(R.id.txt_choose_date_end)
    TextView mTxtChooseDateEnd;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    public String[] CHECKBOX_VALUES = {"Arts", "Business", "Science", "Politics", "Sports", "Travel"};
    public CheckBox[] checkBoxes;
    public String[] mCheckBoxStatus = new String[6];
    public String mQuery;
    public String mBeginDate, mEndDate;
    private Util mUtil = new Util();
    private DatePickerDialog.OnDateSetListener mDateSetListenerBegin;
    private DatePickerDialog.OnDateSetListener mDateSetListenerEnd;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_articles);
        ButterKnife.bind(this);

        this.configureToolbar();

        mDateSetListenerBegin = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month += 1;
                String date = day + "/" + month + "/" + year;
                mTxtChooseDateBegin.setText(date);
            }
        };

        mDateSetListenerEnd = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month += 1;
                String date = day + "/" + month + "/" + year;
                mTxtChooseDateEnd.setText(date);
            }
        };
    }

    /**
     * Test which checkboxes is checked
     *
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
    }

    /**
     * Get sections thanks to checkboxes
     *
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
        return res.toString();
    }

    /**
     * When the user click the button search a new activity is launched
     */
    @OnClick(R.id.btn_search)
    public void onViewClickedSearch() {
        String errorText;
        mQuery = mEditTextQuery.getText().toString();
        mBeginDate = mTxtChooseDateBegin.getText().toString();
        mEndDate = mTxtChooseDateEnd.getText().toString();

        if (!getNewDesk(mCheckBoxStatus).isEmpty() && !mQuery.isEmpty()) { // if minimum a checkbox is checked and if the query is not empty

            if (mBeginDate.isEmpty()) { //if user don't specify a begin date, the current date -1 year is set
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.YEAR, -1);
                mBeginDate = new SimpleDateFormat("ddMMyyyy", Locale.getDefault()).format(cal.getTime());
            }
            if (mEndDate.isEmpty()) { // if user don't specify a end date, the current date is set
                mEndDate = new SimpleDateFormat("ddMMyyyy", Locale.getDefault()).format(new Date());
            }

            String[] values = {mQuery, getNewDesk(mCheckBoxStatus), mUtil.parseDateToyyyyMMdd(mBeginDate), mUtil.parseDateToyyyyMMdd(mEndDate)};

            Intent searchResultsActivity = new Intent(SearchArticlesActivity.this, SearchResultsActivity.class);
            searchResultsActivity.putExtra("VALUES_SEARCH_ARTICLES", values); // put the values (checkboxes and query) to the result activity
            startActivity(searchResultsActivity);
        } else {
            errorText = "You must enter text and check a category !";
            this.alertDialogError(errorText);
        }
    }

    /**
     * display an error message if the user didn't specify query or/and if none checkbox is checked
     *
     * @param errorMessage the error message for the user
     */
    public void alertDialogError(String errorMessage) {
        new AlertDialog.Builder(SearchArticlesActivity.this)
                .setTitle("Error")
                .setMessage(errorMessage)
                .setPositiveButton("I understand", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .show();
    }


    public void configureToolbar() {
        setSupportActionBar(mToolbar);
        ActionBar ab = getSupportActionBar();
        assert ab != null;
        ab.setDisplayHomeAsUpEnabled(true);
    }

    @OnClick({R.id.txt_choose_date_begin, R.id.txt_choose_date_end})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.txt_choose_date_begin:
                this.createDatePicker(mDateSetListenerBegin, "Begin Date");
                break;
            case R.id.txt_choose_date_end:
                this.createDatePicker(mDateSetListenerEnd, "End Date");
                break;
        }
    }

    private void createDatePicker(DatePickerDialog.OnDateSetListener dateSetListener, String stringDate){
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(
                SearchArticlesActivity.this,
                android.R.style.Theme_Holo_Light_Dialog,
                dateSetListener,
                year, month, day);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setTitle(stringDate);
        dialog.show();
    }
}