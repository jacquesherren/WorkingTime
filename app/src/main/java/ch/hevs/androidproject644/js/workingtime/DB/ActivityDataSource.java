package ch.hevs.androidproject644.js.workingtime.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import ch.hevs.androidproject644.js.workingtime.model.Activity;

/**
 * Created by Samuel on 08.11.2016.
 */

public class ActivityDataSource {
    private Context context;
    private DB_Class _dbclass;

    public ActivityDataSource(Context context) {
        _dbclass = DB_Class.getInstance(context);
        this.context = context;
    }

    public long createActivity (Activity activity)
    {
        long id;
        SQLiteDatabase db = _dbclass.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DB_Contract.activities.COLUMN_NAME_ACTIVITY_NAME, activity.get_name().toString());
        values.put(DB_Contract.activities.COLUMN_NAME_AVAILABLE, activity.is_active_int());

        id = db.insert(DB_Contract.activities.TABLE_ACTIVITIES, null, values);
        return id;
    }

    public Activity getActivityByID(long id)
    {
        SQLiteDatabase db = _dbclass.getReadableDatabase();
        String sql = "SELECT * FROM " + DB_Contract.activities.TABLE_ACTIVITIES + " WHERE " + DB_Contract.activities.COLUMN_NAME_ACTIVITY_ID + " = " + id;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null){
            cursor.moveToFirst();
            Activity activity = cursorToActivity(cursor);
            return activity;
        }
        return null;
    }

    public List<Activity> getAllWorkers()
    {
        List<Activity> activities = new ArrayList<>();
        //getreadable ici
        SQLiteDatabase db = _dbclass.getReadableDatabase();
        String sql = "SELECT * FROM " + DB_Contract.activities.TABLE_ACTIVITIES;
        Cursor cursor = db.rawQuery(sql, null);

        //if (cursor != null) {
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Activity activity = cursorToActivity(cursor);
            activities.add(activity);
            cursor.moveToNext();
        }
        cursor.close();
        return activities;
    }

    private Activity cursorToActivity(Cursor cursor) {
        Activity activity= new Activity();

        activity.set_id(cursor.getInt(cursor.getColumnIndex(DB_Contract.activities.COLUMN_NAME_ACTIVITY_ID)));
        activity.set_name(cursor.getString(cursor.getColumnIndex(DB_Contract.activities.COLUMN_NAME_ACTIVITY_NAME)));
        activity.set_active_int(cursor.getInt(cursor.getColumnIndex(DB_Contract.activities.COLUMN_NAME_AVAILABLE)));

        return activity;
    }
}
