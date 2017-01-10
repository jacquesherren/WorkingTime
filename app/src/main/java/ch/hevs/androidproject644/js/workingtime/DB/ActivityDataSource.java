package ch.hevs.androidproject644.js.workingtime.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import ch.hevs.androidproject644.js.workingtime.model.Activity;
import ch.hevs.androidproject644.js.workingtime.model.Company;

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

    public int updateActivity(Activity activity) {
        SQLiteDatabase db = _dbclass.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DB_Contract.activities.COLUMN_NAME_ACTIVITY_NAME, activity.get_name());
        values.put(DB_Contract.activities.COLUMN_NAME_AVAILABLE, activity.is_active_int());
        // updating row
        return db.update(DB_Contract.activities.TABLE_ACTIVITIES, values, DB_Contract.activities.COLUMN_NAME_ACTIVITY_ID + " = ?",
                new String[] { String.valueOf(activity.get_id()) });
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

    public List<Activity> getActivityByAvaiable()
    {
        List<Activity> activities = new ArrayList<Activity>();
        SQLiteDatabase db = _dbclass.getReadableDatabase();
        String sql = "SELECT * FROM " + DB_Contract.activities.TABLE_ACTIVITIES + " WHERE " + DB_Contract.activities.COLUMN_NAME_AVAILABLE + " = " + 1;
        Cursor cursor = db.rawQuery(sql, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Activity activity = cursorToActivity(cursor);
            activities.add(activity);
            cursor.moveToNext();
        }

        cursor.close();
        return activities;
    }

    public List<Activity> getAllActivities()
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

    public void deleteActivity(Activity activity) {
        SQLiteDatabase db = _dbclass.getWritableDatabase();
        db.delete(DB_Contract.activities.TABLE_ACTIVITIES, DB_Contract.activities.COLUMN_NAME_ACTIVITY_ID + " = ?",
                new String[] { String.valueOf(activity.get_id()) });
        db.close();
    }

    public List<Activity> getSumTimeByActivitybetweenDate(long dateFrom, long dateTo)
    {
        List<Activity> activities = new ArrayList<Activity>();
        SQLiteDatabase db = _dbclass.getReadableDatabase();
        String sql = "SELECT " + DB_Contract.activities.TABLE_ACTIVITIES + "." + DB_Contract.activities.COLUMN_NAME_ACTIVITY_ID + ", " + DB_Contract.activities.TABLE_ACTIVITIES + "." + DB_Contract.activities.COLUMN_NAME_ACTIVITY_NAME + ", " + DB_Contract.activities.TABLE_ACTIVITIES + "." + DB_Contract.activities.COLUMN_NAME_AVAILABLE + ", SUM(" + DB_Contract.times.TABLE_TIMES + "." +  DB_Contract.times.COLUMN_NAME_TIME_DURATION + ") FROM " + DB_Contract.activities.TABLE_ACTIVITIES + ", " + DB_Contract.tasks.TABLE_TASKS + ", " + DB_Contract.times.TABLE_TIMES + " WHERE " + DB_Contract.activities.TABLE_ACTIVITIES + "." + DB_Contract.activities.COLUMN_NAME_ACTIVITY_ID + " = " + DB_Contract.tasks.TABLE_TASKS + "." + DB_Contract.tasks.FK_COLUMN_NAME_TASK_ACTIVITYID + " AND " + DB_Contract.tasks.TABLE_TASKS + "." + DB_Contract.tasks.COLUMN_NAME_TASK_ID + " = " + DB_Contract.times.TABLE_TIMES + "." + DB_Contract.times.FK_COLUMN_NAME_TIME_IDTASK + " AND " + DB_Contract.times.TABLE_TIMES + "." + DB_Contract.times.COLUMN_NAME_TIME_STARTTIME + " BETWEEN " + dateFrom   + " AND " + dateTo  + " GROUP BY " + DB_Contract.activities.TABLE_ACTIVITIES + "." + DB_Contract.activities.COLUMN_NAME_ACTIVITY_ID + ", " + DB_Contract.activities.TABLE_ACTIVITIES + "." + DB_Contract.activities.COLUMN_NAME_ACTIVITY_NAME + ", " + DB_Contract.activities.TABLE_ACTIVITIES + "." + DB_Contract.activities.COLUMN_NAME_AVAILABLE;
        //String sql = "SELECT SUM(" + DB_Contract.times.TABLE_TIMES + "." +  DB_Contract.times.COLUMN_NAME_TIME_DURATION + ") FROM " + DB_Contract.companies.TABLE_COMPANIES + ", " + DB_Contract.tasks.TABLE_TASKS + ", " + DB_Contract.times.TABLE_TIMES + " WHERE " + DB_Contract.companies.TABLE_COMPANIES + "." + DB_Contract.companies.COLUMN_NAME_COMPANY_ID + " = " + DB_Contract.tasks.TABLE_TASKS + "." + DB_Contract.tasks.FK_COLUMN_NAME_TASK_COMPANYID + " AND " + DB_Contract.tasks.TABLE_TASKS + "." + DB_Contract.tasks.COLUMN_NAME_TASK_ID + " = " + DB_Contract.times.TABLE_TIMES + "." + DB_Contract.times.FK_COLUMN_NAME_TIME_IDTASK ;
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Activity activity = cursorToActivity(cursor);
            activity.set_duration(cursor.getInt(3));
            activities.add(activity);
            cursor.moveToNext();
        }
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
