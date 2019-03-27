package com.nicolappli.mynews;

import com.nicolappli.mynews.Utils.Util;

import net.bytebuddy.build.ToStringPlugin;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class UnitTest {

    private Util util = new Util();

    @Test
    public void parseDateToyyyyMMdd_isCorrect(){
        String inputDate = "ddMMyyyy";
        String outputDate = "yyyyMMdd";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputDate);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputDate);

        Date date = null;
        String str = null;

        try{
            date = inputFormat.parse("02112018");
            str = outputFormat.format(date);
        }catch(ParseException e){
            e.printStackTrace();
        }

        assertEquals("20181102", str);
    }

    @Test
    public void parseDateToddMMyy_isCorrect(){
        String inputDate = "yyyy-MM-dd";
        String outputDate = "dd/MM/yyyy";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputDate);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputDate);

        Date date = null;
        String str = null;

        try{
            date = inputFormat.parse("2018-11-02");
            str = outputFormat.format(date);
        }catch(ParseException e){
            e.printStackTrace();
        }
        assertEquals("02/11/2018", str);
    }

    @Test
    public void parseDateForNotifications_isCorrect(){
        String inputDate = "yyyy-MM-dd";
        String outputDate = "yyyyMMdd";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputDate);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputDate);

        Date date = null;
        String str = null;

        try{
            date = inputFormat.parse("2018-11-02");
            str = outputFormat.format(date);
        }catch(ParseException e){
            e.printStackTrace();
        }
        assertEquals("20181102", str);
    }

}