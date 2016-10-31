package ch.hevs.androidproject644.js.workingtime.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Jacques on 26.10.2016.
 */

public class Activity implements Parcelable {
    private int _id;
    private String _name;
    private boolean _active;

    public Activity(int id, String name, boolean active) {
        this._id = id;
        this._name = name;
        this._active = active;
    }

    // GETTERS
    public int get_id() { return _id; }
    public String get_name() {
        return _name;
    }
    public boolean is_active() {
        return _active;
    }

    @Override
    public int describeContents() {  return 0; }

    public static final Creator<Activity> CREATOR = new Creator<Activity>() {
        @Override
        public Activity createFromParcel(Parcel source) {
            return new Activity(source);
        }

        @Override
        public Activity[] newArray(int size) {
            return new Activity[size];
        }
    };

    protected Activity(Parcel in){
        this._id = in.readInt();
        this._name= in.readString();
        boolean[] myBooleanArr = new boolean[1];
        in.readBooleanArray(myBooleanArr);
        _active = myBooleanArr[0];
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(_id);
        dest.writeString(_name.toString());
        boolean[] myBooleanArr = {_active}; //= new boolean[1];
        dest.writeBooleanArray(myBooleanArr);
    }
}
