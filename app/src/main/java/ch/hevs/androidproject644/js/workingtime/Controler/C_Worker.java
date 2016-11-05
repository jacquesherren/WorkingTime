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
    
    public static Date convertStringToDate(String sDate) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date parsed = format.parse(sDate);
        java.sql.Date sql = new java.sql.Date(parsed.getTime());
        return sql;
    }
}
