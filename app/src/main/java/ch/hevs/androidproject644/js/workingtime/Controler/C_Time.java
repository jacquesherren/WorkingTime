package ch.hevs.androidproject644.js.workingtime.Controler;

import android.content.Context;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import ch.hevs.androidproject644.js.workingtime.DB.TimeDataSource;
import ch.hevs.androidproject644.js.workingtime.model.Time;

/**
 * Created by Jacques on 21.11.2016.
 */

public class C_Time {
    private static Time _time;

    public static Calendar getCurrentTimeInSecond(){
        Calendar calendar = Calendar.getInstance();
        calendar.getTimeInMillis();
        return calendar;
    }

    public static Calendar set_longToCalendar(long l){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(l);
        return calendar;
    }

    /*public static List<Time> getTimesByTaskId(long id, Context context){
        List<Time> times = new ArrayList<Time>();

        TimeDataSource req = new TimeDataSource(context);
        times = req.getTimeByTaskId(id);

        return times;
    }*/

    public static void set_Time(Time time){
        _time=time;
    }

    public static Time get_Time() {
        return _time;
    }


    public static String getFormatedDuration(long duration){
        String result="";

        int durationTimerInHours = (int)duration / 3600000;

        int durationTimerInMinutes =  (int) duration%3600000;
        durationTimerInMinutes= durationTimerInMinutes / 60000;

        int durationTimerInSeconds = (int) duration % 60000;
        durationTimerInSeconds = durationTimerInSeconds / 1000;
        result = String.format("%02d",durationTimerInHours) +":" + String.format("%02d",durationTimerInMinutes) + ":" + String.format("%02d",durationTimerInSeconds);
        return result;
    }
}
