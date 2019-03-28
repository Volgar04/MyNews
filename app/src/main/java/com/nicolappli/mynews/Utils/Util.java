package com.nicolappli.mynews.Utils;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class Util {

    // Convert the API date to dd-MM-yyyy
    public String parseDateToddMMyy(String dateToChange){
        String inputDate = "yyyy-MM-dd";
        String outputDate = "dd/MM/yyyy";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputDate);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputDate);

        Date date = null;
        String str = null;

        try{
            date = inputFormat.parse(dateToChange);
            str = outputFormat.format(date);
        }catch(ParseException e){
            e.printStackTrace();
        }
        return str;
    }

    // Convert the date for searchArticles
    public String parseDateToyyyyMMdd(String dateToChange){
        String inputDate = "dd/MM/yyyy";
        String outputDate = "yyyyMMdd";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputDate);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputDate);

        Date date = null;
        String str = null;

        try{
            date = inputFormat.parse(dateToChange);
            str = outputFormat.format(date);
        }catch(ParseException e){
            e.printStackTrace();
        }
        return str;
    }

    //get the current date
    public String setCurrentDate(){
        return new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());
    }

    public String parseDateForNotifications(String dateToChange){
        String inputDate = "yyyy-MM-dd";
        String outputDate = "yyyyMMdd";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputDate);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputDate);

        Date date = null;
        String str = null;

        try{
            date = inputFormat.parse(dateToChange);
            str = outputFormat.format(date);
        }catch(ParseException e){
            e.printStackTrace();
        }
        return str;
    }

    public void datePickerDialog(Context context, DatePickerDialog.OnDateSetListener dateSetListener, String title){
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(
                context,
                android.R.style.Theme_Holo_Light_Dialog,
                dateSetListener,
                year, month, day);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setTitle(title);
        dialog.show();
    }
}
