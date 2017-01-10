package ch.hevs.androidproject644.js.workingtime.backend;



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
    private long _id;

    private long _start;
    private long _stop;
    private long _duration;
    private long taskId;


    public Time(){

    }

    // SETTERS
    public void set_start(long _start) {
        this._start = _start;
    }
    public void set_stop(long _stop) {
        this._stop = _stop;
    }
    public void set_id(long _id) {
        this._id = _id;
    }
    public void set_task(long taskid) {
        this.taskId = taskid;
    }
    public void set_duration(long _duration) {
        this._duration = _duration;
    }

    // GETTERS
    public long get_id() {
        return _id;
    }
    public long  get_task() {
        return taskId;
    }
    public long get_duration() {
        return _duration;
    }
    public long get_stop() {
        return _stop;
    }
    public long get_start() {
        return _start;
    }
}


