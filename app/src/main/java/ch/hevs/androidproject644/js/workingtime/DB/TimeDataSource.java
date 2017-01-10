package ch.hevs.androidproject644.js.workingtime.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import ch.hevs.androidproject644.js.workingtime.Controler.C_Task;
import ch.hevs.androidproject644.js.workingtime.Controler.C_Time;
import ch.hevs.androidproject644.js.workingtime.model.Task;
import ch.hevs.androidproject644.js.workingtime.model.Time;


import static ch.hevs.androidproject644.js.workingtime.R.id.time;

/**
 * Created by Samuel on 08.11.2016.
 */

public class TimeDataSource {
    private Context _context;
    private DB_Class _dbclass;

    public TimeDataSource(Context context) {
        _dbclass = DB_Class.getInstance(context);
        this._context = context;
    }

    public long createTime (Time time) {
        long id;
        SQLiteDatabase db = _dbclass.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DB_Contract.times.COLUMN_NAME_TIME_STARTTIME, time.get_start().getTimeInMillis());
        values.put(DB_Contract.times.COLUMN_NAME_TIME_ENDTIME, time.get_stop().getTimeInMillis());
        values.put(DB_Contract.times.COLUMN_NAME_TIME_DURATION, time.get_duration());
        //values.put(DB_Contract.times.FK_COLUMN_NAME_TIME_IDTASK, time.get_task().get_id());
        values.put(DB_Contract.times.FK_COLUMN_NAME_TIME_IDTASK, time.get_task());

        id = db.insert(DB_Contract.times.TABLE_TIMES, null, values);
        Log.e("Time Table Created", "test created");
        return id;
    }

    public long startingTime(Time time)
    {
        Calendar calendar;
        long id;
        SQLiteDatabase db = _dbclass.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DB_Contract.times.COLUMN_NAME_TIME_STARTTIME, time.get_start().getTimeInMillis());
        //values.put(DB_Contract.times.FK_COLUMN_NAME_TIME_IDTASK, time.get_task().get_id());
        values.put(DB_Contract.times.FK_COLUMN_NAME_TIME_IDTASK, time.get_task());
        id = db.insert(DB_Contract.times.TABLE_TIMES, null, values);
        Log.e("Time add", "Time add");
        return id;
    }

    public int finishingTime(Time time)
    {
        Calendar calendar;
        long id;
        SQLiteDatabase db = _dbclass.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DB_Contract.times.COLUMN_NAME_TIME_ENDTIME, time.get_stop().getTimeInMillis());
        values.put(DB_Contract.times.COLUMN_NAME_TIME_DURATION, time.get_duration());

        Log.e("Time is updating", "Time finishingTime");
        // updating row
        return db.update(DB_Contract.times.TABLE_TIMES, values, DB_Contract.times.COLUMN_NAME_TIME_ID + " = ?",
                new String[] { String.valueOf(time.get_id()) });

    }

    public Time getTimeById(long id)
    {
        SQLiteDatabase db = _dbclass.getReadableDatabase();
        final String sql = "SELECT * FROM " + DB_Contract.times.TABLE_TIMES + " WHERE " + DB_Contract.times.COLUMN_NAME_TIME_ID + " = " + id;
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        return null;
    }

    public List<Time> getAllTime() {
        List<Time> times = new ArrayList<>();

        SQLiteDatabase db = _dbclass.getReadableDatabase();
        String sql = "SELECT * FROM " + DB_Contract.times.TABLE_TIMES;
        Cursor cursor = db.rawQuery(sql, null);

        cursor.moveToFirst();
        while(!cursor.isAfterLast())
        {
            Time time = cursorToTime(cursor);
            times.add(time);
            cursor.moveToNext();
        }

        cursor.close();
        return times;
    }

    public List<Time> getTimeByTaskId(long taskId) {
        List<Time> times = new ArrayList<>();

        SQLiteDatabase db = _dbclass.getReadableDatabase();
        String sql = "SELECT * FROM " + DB_Contract.times.TABLE_TIMES + " WHERE " + DB_Contract.times.FK_COLUMN_NAME_TIME_IDTASK + " = " + taskId;
        Cursor cursor = db.rawQuery(sql, null);

        cursor.moveToFirst();
        while(!cursor.isAfterLast())
        {
            Time time = cursorToTime(cursor);
            times.add(time);
            cursor.moveToNext();
        }

        cursor.close();
        return times;
    }


    private Time cursorToTime(Cursor cursor)
    {
        Time time = new Time();

        time.set_id(cursor.getInt(cursor.getColumnIndex(DB_Contract.times.COLUMN_NAME_TIME_ID)));

        time.set_start(C_Time.set_longToCalendar(cursor.getLong(cursor.getColumnIndex(DB_Contract.times.COLUMN_NAME_TIME_STARTTIME))));
        time.set_stop(C_Time.set_longToCalendar(cursor.getLong(cursor.getColumnIndex(DB_Contract.times.COLUMN_NAME_TIME_ENDTIME))));

        time.set_duration(cursor.getInt(cursor.getColumnIndex(DB_Contract.times.COLUMN_NAME_TIME_DURATION)));

        //Task task = C_Task.getTasksById(cursor.getInt(cursor.getColumnIndex(DB_Contract.times.FK_COLUMN_NAME_TIME_IDTASK)), _context);
        time.set_task(cursor.getInt(cursor.getColumnIndex(DB_Contract.times.FK_COLUMN_NAME_TIME_IDTASK)));

        return time;
    }
}
