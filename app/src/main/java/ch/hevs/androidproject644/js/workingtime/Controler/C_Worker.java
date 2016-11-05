package ch.hevs.androidproject644.js.workingtime.Controler;


import android.widget.RadioButton;
import android.widget.Switch;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by Samuel on 05.11.2016.
 */

public class C_Worker {

    public static char controlSex(RadioButton male, RadioButton female)
    {
        if(male.isChecked())
            return 'M';
        else if(female.isChecked())
            return 'F';

        return ' ';
    }

    public static boolean controlSwitch(Switch switchWorker)
    {
        if(switchWorker.isChecked())
            return true;
        return false;
    }

    public static Date convertStringToDate(String sDate) {
       // if(!(sDate.equals("")))
     //       java.sql.Date sqlDate = new java.sql.Date(Long.parseLong(sDate));


        //return java.sql.Date.valueOf();
        return null;
    }
}
