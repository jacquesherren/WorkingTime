package ch.hevs.androidproject644.js.workingtime.backend;


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
    private long _id;

    private long _date;
    private long _duration;
    private long _workerid;
    private long _companyid ;
    private long _activityid ;
    private boolean _archive;

    public Task(long id, long date, long duration, long workerid, long companyid, long activityid){
        this._id=id;
        this._date=date;
        this._duration=duration;
        this._workerid=workerid;
        this._companyid=companyid;
        this._activityid=activityid;

    }

    public Task()
    {

    }

    //*** GETTERS
    public long get_id() { return _id; }
    public long get_date() {  return _date;  }
    public long get_duration() {  return _duration;  }
    public boolean is_archive() { return _archive; }
    public long get_workerid() { return _workerid;    }
    public long get_companyid() { return _companyid;  }
    public long get_activityid() { return _activityid;  }

    //*** SETTERS
    public void set_date(long date) {this._date = date;}
    public void set_duration(long duration) {this._duration = duration;}
    public void set_workerid(long workerid) {this._workerid = workerid;}
    public void set_companyid(long companyid) {this._companyid = companyid;}
    public void set_activityid(long activityid) {this._activityid = activityid;}
    public void set_id(long id) {this._id = id; }
    public void set_archive(boolean archive) { this._archive = archive; }
}
