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
    private List<Worker> _workers = new ArrayList<Worker>();
    private List<Company> _companies = new ArrayList<Company>();
    private List<Activity> _activities = new ArrayList<Activity>();


    public Task(int id,Date date, int duration, String description){
        this._id=id;
        this._date=date;
        this._duration=duration;
        this._description=description;
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
        dest.writeList(_workers);
        dest.writeList(_companies);
        dest.writeList(_activities);
    }
    protected Task(Parcel in) {
        this._id = in.readInt();
        this._date= java.sql.Date.valueOf(in.readString());
        this._duration= in.readInt();
        this._description= in.readString();

        in.readList(this._workers,null);
        in.readList(this._companies,null);
        in.readList(this._activities,null);



    }
}
