package com.example.Samuel.myapplication.backend;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * Created by Jacques on 26.10.2016.
 */
@Entity
public class Company  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int _id;

    private String _name;
    private boolean _active;
    private long _duration = 0;

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
    public long get_duration() {
        return _duration;
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
    public void set_duration(long _duration) {        this._duration = _duration;
    }
}
