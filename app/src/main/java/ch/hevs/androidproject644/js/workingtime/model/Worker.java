package ch.hevs.androidproject644.js.workingtime.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.sql.Date;
import java.util.Calendar;

/**
 * Created by Jacques on 26.10.2016.
 */

public class Worker implements Parcelable {
    private int _id;
    private String _lastname;
    private String _firstname;
    private Calendar _birthdate;
    private char _sex;
    private boolean _active;


    public Worker(int id,String lastname, String firstname, Calendar birthdate, char sex, boolean active){
        this._id = id;
        this._lastname=lastname;
        this._firstname=firstname;
        this._birthdate=birthdate;
        this._sex=sex;
        this._active=active;

    }

    public Worker (){

    }

    // SETTERS ***
    public void set_id(int _id) {
        this._id = _id;
    }
    public void set_lastname(String _lastname) {
        this._lastname = _lastname;
    }
    public void set_firstname(String _firstname) {
        this._firstname = _firstname;
    }
    public void set_birthdate(Calendar _birthdate) { this._birthdate = _birthdate;  }
    public void set_sex(char _sex) {
        this._sex = _sex;
    }
    public void set_active(boolean _active) {
        this._active = _active;
    }

    // GETTERS ***
    public int get_id() { return _id; }
    public String get_lastname() {
        return _lastname;
    }
    public String get_firstname() {
        return _firstname;
    }
    public Calendar get_birthdate() {
        return _birthdate;
    }
    public char get_sex() {
        return _sex;
    }
    public boolean is_active() { return _active;  }


    // Methods ***
    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Worker> CREATOR = new Creator<Worker>() {
        @Override
        public Worker createFromParcel(Parcel source) {
            return new Worker(source);
        }

        @Override
        public Worker[] newArray(int size) {
            return new Worker[size];
        }
    };

    protected Worker(Parcel in){
        this._id = in.readInt();
        this._lastname= in.readString();
        this._firstname= in.readString();
        this._birthdate = (Calendar) in.readValue(getClass().getClassLoader());
        char[] myCharArr = new char[1];
        in.readCharArray(myCharArr);
        this._sex= myCharArr[0];

        boolean[] myBooleanArr = new boolean[1];
        in.readBooleanArray(myBooleanArr);
        _active = myBooleanArr[0];
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(_id);
        dest.writeString(_firstname);
        dest.writeString(_lastname);
        dest.writeValue(_birthdate);

        char[] myCharArr = {_sex}; //= new boolean[1];
        dest.writeCharArray(myCharArr);

        boolean[] myBooleanArr = {_active}; //= new boolean[1];
        dest.writeBooleanArray(myBooleanArr);
    }

    public void set_birthdate(long birthdate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(birthdate);
        this._birthdate=calendar;
    }
    public int is_active_int() {
        if(this._active==true)
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
