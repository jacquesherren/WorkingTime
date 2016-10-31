package ch.hevs.androidproject644.js.workingtime.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by Jacques on 26.10.2016.
 */

public class Worker implements Parcelable {
    private int _id;
    private String _lastname;
    private String _firstname;
    private Date _birthdate;
    private char _sex;
    private boolean _active;


    public Worker(int id,String lastname, String firstname, Date birthdate, char sex, boolean active){
        this._id = id;
        this._lastname=lastname;
        this._firstname=firstname;
        this._birthdate=birthdate;
        this._sex=sex;
        this._active=active;

    }

    public Worker(int id , String lastname, String firstname, String birthdate, String sex, String active){
        this._id = id;
        this._lastname=lastname;
        this._firstname=firstname;
        this._birthdate= java.sql.Date.valueOf(birthdate);
        this._sex= sex.charAt(0);
        if(active.equals("Yes"))
            _active=true;
        else
            _active=false;

    }

    // GETTERS
    public int get_id() { return _id; }
    public String get_lastname() {
        return _lastname;
    }
    public String get_firstname() {
        return _firstname;
    }
    public Date get_birthdate() {
        return _birthdate;
    }
    public char get_sex() {
        return _sex;
    }
    public boolean is_active() { return _active;  }

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
        this._birthdate= java.sql.Date.valueOf(in.readString());

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
        dest.writeString(_birthdate.toString());

        char[] myCharArr = {_sex}; //= new boolean[1];
        dest.writeCharArray(myCharArr);

        boolean[] myBooleanArr = {_active}; //= new boolean[1];
        dest.writeBooleanArray(myBooleanArr);
    }
}
