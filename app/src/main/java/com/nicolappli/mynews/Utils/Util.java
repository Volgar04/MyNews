package com.nicolappli.mynews.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

    // Convert the API date to dd-MM-yyyy
    public static String parseDateToddMMyy(String dateToChange){
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
}
