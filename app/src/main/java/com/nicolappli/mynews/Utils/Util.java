package com.nicolappli.mynews.Utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.widget.Toolbar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class Util {

    // --------------------
    // For config.properties
    // --------------------

    public static String getProperty(String key,Context context) throws IOException {
        Properties properties = new Properties();
        AssetManager assetManager = context.getAssets();
        InputStream inputStream = assetManager.open("config.properties");
        properties.load(inputStream);
        return properties.getProperty(key);
    }

    public static void setConfigProperties(String key, String value) throws IOException{
        Properties properties = new Properties();
        properties.setProperty(key,value);
        File f = new File("config.properties");
        OutputStream out = new FileOutputStream(f);
        properties.store(out,null);
        out.close();
    }

    //Properties properties = new Properties();
    //AssetManager assetManager = context.getAssets();
    //InputStream inputStream = assetManager.open("config.properties");
    //properties.load(inputStream);
    //properties.setProperty(key,value);
    //properties.store(new FileOutputStream("config.properties"), null);


    // Convert the API date to dd-MM-yyyy
    public static String parseDateToddMMyy(String dateToChange){
        String inputDate = "yyyy-MM-dd";
        String outputDate = "dd-MM-yy";
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
