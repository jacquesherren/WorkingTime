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
    private long _id;
    private Calendar _date;
    private long _duration;
    private Worker _worker;
    private Company _company ;
    private Activity _activity ;
    private boolean _archive;

    public Task(long id, Calendar date, long duration, Worker worker, Company company, Activity activity){
        this._id=id;
        this._date=date;
        this._duration=duration;
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
        dest.writeLong(_id);
        dest.writeValue(_date);
        dest.writeLong(_duration);
        dest.writeParcelable(_worker,flags);
        dest.writeParcelable(_company,flags);
        dest.writeParcelable(_activity,flags);

        boolean[] myBooleanArr = {_archive};
        dest.writeBooleanArray(myBooleanArr);
    }
    protected Task(Parcel in) {
        this._id = in.readLong();
        this._date= (Calendar) in.readValue(getClass().getClassLoader());
        this._duration= in.readLong();

        _worker  = in.readParcelable(Company.class.getClassLoader());
        _company  = in.readParcelable(Company.class.getClassLoader());
        _activity = in.readParcelable(Activity.class.getClassLoader());

        boolean[] myBooleanArr = new boolean[1];
        in.readBooleanArray(myBooleanArr);
        _archive = myBooleanArr[0];


        //List<Time> times = new ArrayList<Time>();
    }

    public static Creator<Task> getCREATOR() { return CREATOR;  }

    //*** GETTERS
    public long get_id() { return _id; }
    public Calendar get_date() {  return _date;  }
    public long get_duration() {  return _duration;  }
    public boolean is_archive() { return _archive; }

    public String get_duration_hhmm() {
        long hours = _duration / 60; //since both are ints, you get an int
        long minutes = _duration % 60;
        String hhmm = hours + "h" + minutes;
        return hhmm;

    }
    public Worker get_worker() {  return _worker; }
    public Company get_company() { return _company;  }
    public Activity get_activity() { return _activity;  }


    //*** SETTERS
    public void set_date(Calendar _date) {
        this._date = _date;
    }
    public void set_duration(long _duration) {
        this._duration = _duration;
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
    public void set_id(long _id) {this._id = _id; }
    public void set_archive(boolean _archive) { this._archive = _archive; }

    public int is_archive_int() {
        if (this._archive==true)
            return 1;
        return 0;

    }
    public void set_active_int(int active) {
        if(active==1)
            this._archive=true;
        else if(active==0)
            this._archive=false;
    }

}
