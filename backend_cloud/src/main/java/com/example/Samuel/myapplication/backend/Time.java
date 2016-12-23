package com.example.Samuel.myapplication.backend;



import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * Created by Jacques on 10.11.2016.
 */
@Entity
public class Time  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int _id;

    private Calendar _start;
    private Calendar _stop;
    private long _duration;
    private int taskId;


    public Time(){

    }

    // SETTERS
    public void set_start(Calendar _start) {
        this._start = _start;
    }
    public void set_stop(Calendar _stop) {
        this._stop = _stop;
    }
    public void set_id(int _id) {
        this._id = _id;
    }
    public void set_task(int taskid) {
        this.taskId = taskid;
    }
    public void set_duration(int _duration) {
        this._duration = _duration;
    }


    // GETTERS
    public int get_id() {
        return _id;
    }
    public int  get_task() {
        return taskId;
    }
    public long get_duration() {
        return _duration;
    }
    public Calendar get_stop() {
        return _stop;
    }
    public Calendar get_start() {
        return _start;
    }
}


