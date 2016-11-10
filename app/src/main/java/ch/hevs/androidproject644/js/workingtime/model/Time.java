package ch.hevs.androidproject644.js.workingtime.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Calendar;

/**
 * Created by Jacques on 10.11.2016.
 */

public class Time implements Parcelable {
    private int _id;
    private Calendar _start;
    private Calendar _stop;
    private int _duration;
    private Task _task;


    protected Time(Parcel in) {
        this._id = in.readInt();
        this._duration = in.readInt();
        this._start = (Calendar) in.readValue(getClass().getClassLoader());
        this._stop = (Calendar) in.readValue(getClass().getClassLoader());
        this._task = in.readParcelable(Task.class.getClassLoader());
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

    public void set_stop(long stop) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(stop);
        this._stop=calendar;
    }
    public void set_start(long start) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(start);
        this._start=calendar;
    }
}
