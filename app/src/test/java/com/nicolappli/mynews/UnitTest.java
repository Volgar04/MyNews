package com.nicolappli.mynews;

import com.nicolappli.mynews.Utils.Util;

import net.bytebuddy.build.ToStringPlugin;

import org.junit.Test;

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
        String changedDate = util.parseDateToyyyyMMdd("02112018");
        assertEquals("20181102", changedDate);
    }

    @Test
    public void parseDateToddMMyy_isCorrect(){
        String changedDate = util.parseDateToddMMyy("2018-11-02");
        assertEquals("02/11/2018", changedDate);
    }

    @Test
    public void parseDateForNotifications_isCorrect(){
        String changedDate = util.parseDateForNotifications("2018-11-02");
        assertEquals("20181102", changedDate);
    }

}