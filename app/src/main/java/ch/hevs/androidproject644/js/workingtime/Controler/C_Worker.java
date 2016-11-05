package ch.hevs.androidproject644.js.workingtime.Controler;

import android.widget.RadioButton;
import android.widget.Switch;
import java.util.Calendar;

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
    public static Calendar convertStringToDate(String date){
        Calendar cdate = Calendar.getInstance();
        convertStringToDate(date);
        return cdate;
    }
}
