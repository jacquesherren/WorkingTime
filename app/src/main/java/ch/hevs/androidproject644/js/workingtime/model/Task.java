package ch.hevs.androidproject644.js.workingtime.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Jacques on 31.10.2016.
 */

public class Task implements Parcelable{
    private int _id;
    private Calendar _date;
    private int _duration;
    private String _description;
    private Worker _worker;
    private Company _company ;
    private Activity _activity ;

    public Task(int id, Calendar date, int duration, String description, Worker worker, Company company, Activity activity){
        this._id=id;
        this._date=date;
        this._duration=duration;
        this._description=description;
        this._worker=worker;
        this._company=company;
        this._activity=activity;
    }

    public Task()
    {

    }

    public void set_date(long date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date);
        this._date=calendar;
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
        dest.writeValue(_date);
        dest.writeInt(_duration);
        dest.writeString(_description);
        dest.writeParcelable(_worker,flags);
        dest.writeParcelable(_company,flags);
        dest.writeParcelable(_activity,flags);
    }
    protected Task(Parcel in) {
        this._id = in.readInt();
        this._date= (Calendar) in.readValue(getClass().getClassLoader());
        this._duration= in.readInt();
        this._description= in.readString();

        _worker  = in.readParcelable(Company.class.getClassLoader());
        _company  = in.readParcelable(Company.class.getClassLoader());
        _activity = in.readParcelable(Activity.class.getClassLoader());
    }

    public static Creator<Task> getCREATOR() { return CREATOR;  }

    //*** GETTERS
    public int get_id() { return _id; }
    public Calendar get_date() {  return _date;  }
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

    //*** SETTERS
    public void set_date(Calendar _date) {
        this._date = _date;
    }
    public void set_duration(int _duration) {
        this._duration = _duration;
    }
    public void set_description(String _description) {
        this._description = _description;
    }
    public void set_worker(Worker _worker) {
        this._worker = _worker;
    }
    public void set_company(Company _company) {
        this._company = _company;
    }
    public void set_activity(Activity _activity) {
        this._activity = _activity;
    }
    public void set_id(int _id) {this._id = _id;    }
}
