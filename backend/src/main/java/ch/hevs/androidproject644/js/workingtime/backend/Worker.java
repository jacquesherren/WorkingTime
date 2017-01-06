package ch.hevs.androidproject644.js.workingtime.backend;


import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import java.util.Calendar;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * Created by Jacques on 26.10.2016.
 */
@Entity
public class Worker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long _id;

    private String _lastname;
    private String _firstname;
    private long _birthdate;
    private String _sex;
    private boolean _active;
    private long _duration = 0;


    public Worker(long id, String lastname, String firstname, long birthdate, String sex, boolean active){
        this._id = id;
        this._lastname=lastname;
        this._firstname=firstname;
        this._birthdate=birthdate;
        this._sex=sex;
        this._active=active;

    }
    public Worker(){

    }

    // SETTERS ***
    public void set_id(long id) {
        this._id = id;
    }
    public void set_lastname(String lastname) {
        this._lastname = lastname;
    }
    public void set_firstname(String firstname) {
        this._firstname = firstname;
    }
    public void set_birthdate(long birthdate) { this._birthdate = birthdate;  }
    public void set_sex(String sex) {
        this._sex = sex;
    }
    public void set_active(boolean active) {
        this._active = active;
    }
    public void set_duration(long _duration) {     this._duration = _duration;  }

    // GETTERS ***
    public long get_id() { return _id; }
    public String get_lastname() {
        return _lastname;
    }
    public String get_firstname() {
        return _firstname;
    }
    public long get_birthdate() {
        return _birthdate;
    }
    public String get_sex() {
        return _sex;
    }
    public boolean is_active() { return _active;  }
    public long get_duration() {       return _duration;   }

}
