package com.example.Samuel.myapplication.backend;

import com.googlecode.objectify.annotation.Entity;

/**
 * The object model for the data we are sending through endpoints
        */
@Entity
public class MyBean {

    private String myData;

    public String getData() {
        return myData;
    }

    public void setData(String data) {
        myData = data;
    }
}