package ch.hevs.androidproject644.js.workingtime.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.sql.Time;

/**
 * Created by Samuel on 08.11.2016.
 */

public class TimeDataSource {
    private Context context;
    private DB_Class _dbclass;

    public TimeDataSource(Context context) {
        _dbclass = DB_Class.getInstance(context);
        this.context = context;
    }
}
