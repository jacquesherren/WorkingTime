package com.example.Samuel.myapplication.backend;


import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import java.util.Calendar;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * Created by Jacques on 31.10.2016.
 */
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int _id;

    private Calendar _date;
    private int _duration;
    private Worker _worker;
    private Company _company ;
    private Activity _activity ;
    private boolean _archive;

    public Task(int id, Calendar date, int duration, Worker worker, Company company, Activity activity){
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

    //*** GETTERS
    public int get_id() { return _id; }
    public Calendar get_date() {  return _date;  }
    public int get_duration() {  return _duration;  }
    public boolean is_archive() { return _archive; }

    //*** SETTERS
    public void set_date(Calendar _date) {this._date = _date;}
    public void set_duration(int _duration) {this._duration = _duration;}
    public void set_worker(Worker _worker) {this._worker = _worker;}
    public void set_company(Company _company) {this._company = _company;}
    public void set_activity(Activity _activity) {this._activity = _activity;}
    public void set_id(int _id) {this._id = _id; }
    public void set_archive(boolean _archive) { this._archive = _archive; }
}
