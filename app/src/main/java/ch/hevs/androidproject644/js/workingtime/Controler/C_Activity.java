package ch.hevs.androidproject644.js.workingtime.Controler;

import android.content.Context;
import android.widget.Switch;

import ch.hevs.androidproject644.js.workingtime.DB.ActivityDataSource;
import ch.hevs.androidproject644.js.workingtime.model.Activity;

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

    public static Activity getWorkerById(long id, Context context){
        Activity activity;
        ActivityDataSource wreq = new ActivityDataSource(context);
        activity = wreq.getActivityByID(id);
        return activity;
    }
}
