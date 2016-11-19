package ch.hevs.androidproject644.js.workingtime.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * Created by Jacques on 10.11.2016.
 */

public class Time implements Parcelable {
    private int _id;
    private Calendar _start;
    private Calendar _stop;
    private int _duration;
    private Task _task;
    final DateFormat dateFormat = SimpleDateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL);

    protected Time(Parcel in) {
        this._id = in.readInt();
        this._duration = in.readInt();
        this._start = (Calendar) in.readValue(getClass().getClassLoader());
        this._stop = (Calendar) in.readValue(getClass().getClassLoader());
        this._task = in.readParcelable(Task.class.getClassLoader());
    }

    public Time()
    {

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
        dest.writeInt(_id);
        dest.writeValue(_start);
        dest.writeValue(_stop);
        dest.writeInt(_duration);
        dest.writeValue(_task);
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

    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_task(Task _task) {
        this._task = _task;
    }

    public void set_duration(int _duration) {
        this._duration = _duration;
    }

    public int get_id() {

        return _id;
    }

    public Task get_task() {
        return _task;
    }

    public int get_duration() {
        return _duration;
    }

    public Calendar get_stop() {
        return _stop;
    }

    public Calendar get_start() {
        return _start;
    }
}


