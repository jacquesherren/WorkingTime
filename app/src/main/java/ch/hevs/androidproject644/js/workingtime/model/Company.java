package ch.hevs.androidproject644.js.workingtime.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Jacques on 26.10.2016.
 */

public class Company implements Parcelable {
    private int _id;
    private String _name;
    private boolean _active;

    public Company(int id, String name, boolean active) {
        this._id = id;
        this._name = name;
        this._active = active;
    }

    public Company() {    }

    // SETTERS
    public void set_id(int id) {
        this._id = id;
    }
    public void set_name(String name) {
        this._name = name;
    }
    public void set_active(boolean active) {
        this._active = active;
    }


    // GETTERS
    public int get_id() {
        return _id;
    }
    public String get_name() {
        return _name;
    }
    public boolean is_active() {
        return _active;
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
        this._id = in.readInt();
        this._name = in.readString();

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
}
