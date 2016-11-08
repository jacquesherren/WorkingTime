package ch.hevs.androidproject644.js.workingtime.Controler;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.ActivityCompat;
import android.widget.RadioButton;
import android.widget.Switch;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import ch.hevs.androidproject644.js.workingtime.R;
import ch.hevs.androidproject644.js.workingtime.model.Worker;


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

    public static Drawable get_ImageSex(Context c,char sex){
        if(sex=='M')
            return c.getResources().getDrawable(R.mipmap.ic_male);
        else if (sex=='F')
            return c.getResources().getDrawable(R.mipmap.ic_female);
        return null;
    }

    public static boolean controlSwitch(Switch switchWorker)
    {
        if(switchWorker.isChecked())
            return true;
        return false;
    }

}
