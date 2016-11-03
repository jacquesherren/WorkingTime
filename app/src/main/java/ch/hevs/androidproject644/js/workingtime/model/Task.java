package ch.hevs.androidproject644.js.workingtime.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jacques on 31.10.2016.
 */

public class Task implements Parcelable{
    private int _id;
    private Date _date;
    private int _duration;
    private String _description;
    private Worker _worker;
    private Company _company ;
    private Activity _activity ;

    public Task(int id, Date date, int duration, String description, Worker worker, Company company, Activity activity){
        this._id=id;
        this._date=date;
        this._duration=duration;
        this._description=description;
        this._worker=worker;
        this._company=company;
        this._activity=activity;
    }

    @Override
    public int describeContents() {
        return 0;
    }
    public static final Creator<Task> CREATOR = new Creator<Task>() {
        @Override
        public Task createFromParcel(Parcel source) {
            return new Task(source);
        }

        @Override
        public Task[] newArray(int size) {
            return new Task[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(_id);
        dest.writeString(_date.toString());
        dest.writeInt(_duration);
        dest.writeString(_description);
        dest.writeParcelable(_worker,flags);
        dest.writeParcelable(_company,flags);
        dest.writeParcelable(_activity,flags);
    }
    protected Task(Parcel in) {
        this._id = in.readInt();
        this._date= java.sql.Date.valueOf(in.readString());
        this._duration= in.readInt();
        this._description= in.readString();

        _worker  = in.readParcelable(Company.class.getClassLoader());
        _company  = in.readParcelable(Company.class.getClassLoader());
        _activity = in.readParcelable(Activity.class.getClassLoader());
    }

    public int get_id() { return _id; }

    public Date get_date() {  return _date;  }

    public int get_duration() {  return _duration;  }

    public String get_duration_hhmm() {
        int hours = _duration / 60; //since both are ints, you get an int
        int minutes = _duration % 60;
        String hhmm = hours + "h" + minutes;
        return hhmm;

    }

    public String get_description() {  return _description;  }

    public Worker get_worker() {  return _worker; }

    public Company get_company() { return _company;  }

    public Activity get_activity() { return _activity;  }

    public static Creator<Task> getCREATOR() { return CREATOR;  }
}
