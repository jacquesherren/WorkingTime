package ch.hevs.androidproject644.js.workingtime.Controler;

import android.widget.Switch;

/**
 * Created by Samuel on 08.11.2016.
 */

public class C_Activity {

    public static boolean controlSwitch(Switch switchWorker)
    {
        if(switchWorker.isChecked())
            return true;
        return false;
    }
}
