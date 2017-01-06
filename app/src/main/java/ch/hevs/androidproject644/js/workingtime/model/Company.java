package ch.hevs.androidproject644.js.workingtime.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Jacques on 26.10.2016.
 */

public class Company implements Parcelable {
    private long _id;
    private String _name;
    private boolean _active;
    private long _duration = 0;

    public Company(long id, String name, boolean active) {
        this._id = id;
        this._name = name;
        this._active = active;
    }

    public Company() {    }

    // SETTERS
    public void set_id(long id) {
        this._id = id;
    }
    public void set_name(String name) {
        this._name = name;
    }
    public void set_active(boolean active) {
        this._active = active;
    }
    public long get_duration() {
        return _duration;
    }

    // GETTERS
    public long get_id() {
        return _id;
    }
    public String get_name() {
        return _name;
    }
    public boolean is_active() {
        return _active;
    }
    public void set_duration(long _duration) {        this._duration = _duration;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Company> CREATOR = new Creator<Company>() {
        @Override
        public Company createFromParcel(Parcel source) {
            return new Company(source);
        }

        @Override
        public Company[] newArray(int size) {
            return new Company[size];
        }
    };

    protected Company(Parcel in) {
        this._id = in.readLong();
        this._name = in.readString();

        boolean[] myBooleanArr = new boolean[1];
        in.readBooleanArray(myBooleanArr);
        _active = myBooleanArr[0];

        this._duration=in.readLong();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(_id);
        dest.writeString(_name.toString());

        boolean[] myBooleanArr = {_active}; //= new boolean[1];
        dest.writeBooleanArray(myBooleanArr);

        dest.writeLong(_duration);
    }

    public int is_active_int() {
        if (this._active==true)
            return 1;
        return 0;

    }
    public void set_active_int(int active) {
        if(active==1)
            this._active=true;
        else if(active==0)
            this._active=false;
    }

    @Override
    public String toString() {
        return "Company{" +
                "_id=" + _id +
                ", _name='" + _name + '\'' +
                ", _active=" + _active +
                '}';
    }
}
