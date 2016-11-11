package ch.hevs.androidproject644.js.workingtime.Controler;

import android.widget.Switch;

/**
 * Created by Jacques on 11.11.2016.
 */

public class C_Company {

    public static boolean controlSwitch(Switch switchC)
    {
        if(switchC.isChecked())
            return true;
        return false;
    }
}
