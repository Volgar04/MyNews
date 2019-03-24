package com.nicolappli.mynews.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

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
        String inputDate = "ddMMyyyy";
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

    //get the current date -1 year
    public String setCurrentDateMinusOneYear(){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.US);
        return String.format("%s", sdf.format(cal.getTime()));
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
}
