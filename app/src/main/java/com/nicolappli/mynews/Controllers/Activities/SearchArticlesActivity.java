package com.nicolappli.mynews.Controllers.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.nicolappli.mynews.R;

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

    public String[] CHECKBOX_VALUES = {"Arts", "Business", "Entrepreneurs", "Politics", "Sports", "Travel"};
    public String[] mCheckBoxStatus = new String[5];
    public CheckBox[] mCheckBoxes;
    public String mQuery;
    public String mBeginDate, mEndDate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_articles_page);
        ButterKnife.bind(this);
        // Initialise checkboxes list
        this.mCheckBoxes = new CheckBox[]{mCheckBoxArts, mCheckBoxBusiness, mCheckBoxEntrepreneurs, mCheckBoxPolitics, mCheckBoxSports, mCheckBoxTravel};


    }

    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId()) {
            case R.id.checkbox_arts:
                if (checked)
                    mCheckBoxStatus[0] = CHECKBOX_VALUES[0];
                else
                    mCheckBoxStatus[0] = "";
                break;
            case R.id.checkbox_business:
                if (checked)
                    mCheckBoxStatus[1] = CHECKBOX_VALUES[1];
                else
                    mCheckBoxStatus[1] = "";
                break;
            case R.id.checkbox_entrepreneurs:
                if (checked)
                    mCheckBoxStatus[2] = CHECKBOX_VALUES[2];
                else
                    mCheckBoxStatus[2] = "";
            case R.id.checkbox_politics:
                if (checked)
                    mCheckBoxStatus[3] = CHECKBOX_VALUES[3];
                else
                    mCheckBoxStatus[3] = "";
                break;
            case R.id.checkbox_sports:
                if (checked)
                    mCheckBoxStatus[4] = CHECKBOX_VALUES[4];
                else
                    mCheckBoxStatus[4] = "";
                break;
            case R.id.checkbox_travel:
                if (checked)
                    mCheckBoxStatus[5] = CHECKBOX_VALUES[5];
                else
                    mCheckBoxStatus[5] = "";
                break;
        }
    }

    public void getQueryAndDates() {
        mQuery = mEditTextQuery.getText().toString();
        mBeginDate = mEditTextBeginDate.getText().toString();
        mEndDate = mEditTextEndDate.getText().toString();
        Log.e("Tag Search Article", "Query = " + mQuery + ", begin date = " + mBeginDate + ", end date = " + mEndDate);
    }

    @OnClick(R.id.btn_search)
    public void onViewClickedSearch() {
        getQueryAndDates();
        String errorText;
        if(!mQuery.isEmpty()){
            Log.e("Tag Search Article", "Tout c'est bien passé : " + mQuery);}
        else {
            Log.e("Tag Search Article", "il y a eu un problème : " + mQuery);
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
}
