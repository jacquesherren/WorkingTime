package ch.hevs.androidproject644.js.workingtime.Controler;

import java.util.Calendar;

import ch.hevs.androidproject644.js.workingtime.model.Time;

/**
 * Created by Jacques on 21.11.2016.
 */

public class C_Time {
    private static Time _time;

    public static int getCurrentTimeInSecond(){
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.SECOND);
    }

    public static void set_Time(Time time){
        _time=time;
    }

    public static Time get_Time() {
        return _time;
    }
}
