package ch.hevs.androidproject644.js.workingtime.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;



import ch.hevs.androidproject644.js.workingtime.Controler.C_Time;

/**
 * Created by Jacques on 10.11.2016.
 */

public class Time implements Parcelable {
    private long _id;
    private Calendar _start;
    private Calendar _stop;
    private long _duration;
    private long taskId;


    protected Time(Parcel in) {
        this._id = in.readLong();
        this._duration = in.readLong();
        this._start = (Calendar) in.readValue(getClass().getClassLoader());
        this._stop = (Calendar) in.readValue(getClass().getClassLoader());
        //this._task = in.readParcelable(Task.class.getClassLoader());
        this.taskId = in.readLong();

    }

    // Constructor to start timer
    public Time()
    {
        set_start(C_Time.getCurrentTimeInSecond());
    }

    // Constructor to finish timer
    /*public Time(Task _task) {
        set_start(C_Time.getCurrentTimeInSecond());
        this._task = _task;
    }*/
    public Time (long taskId){
        set_start(C_Time.getCurrentTimeInSecond());
        this.taskId=taskId;
    }

    public static final Creator<Time> CREATOR = new Creator<Time>() {
        @Override
        public Time createFromParcel(Parcel source) {  return new Time(source);   }
        @Override
        public Time[] newArray(int size) { return new Time[size];  }
    };

    @Override
    public int describeContents() { return 0;  }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(_id);
        dest.writeValue(_start);
        dest.writeValue(_stop);
        dest.writeLong(_duration);
        //dest.writeParcelable(_task,flags);
        dest.writeLong(taskId);
    }

    public void cal_duration() {
        this._duration = _stop.getTimeInMillis() - _start.getTimeInMillis();
    }

    public void set_stop() {
        long time = System.currentTimeMillis();
        Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("GMT+1"));
        calendar.setTimeInMillis(time);
        this._stop=calendar;
    }
    public void set_start() {
        long time = System.currentTimeMillis();
        Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("GMT+1"));
        calendar.setTimeInMillis(time);
        this._start=calendar;
    }

    public void set_start(Calendar _start) {
        this._start = _start;
    }

    public void set_stop(Calendar _stop) {
        this._stop = _stop;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public void set_task(long taskid) {
        this.taskId = taskid;
    }

    public void set_duration(int _duration) {
        this._duration = _duration;
    }

    public long get_id() {
        return _id;
    }

    public long  get_task() {
        return taskId;
    }

    public long get_duration() {
        return _duration;
    }

    public Calendar get_stop() {
        return _stop;
    }

    public Calendar get_start() {
        return _start;
    }


}


