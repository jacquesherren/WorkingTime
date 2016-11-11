package ch.hevs.androidproject644.js.workingtime.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import ch.hevs.androidproject644.js.workingtime.model.Worker;


public class DB_Class extends SQLiteOpenHelper {
    private SQLiteDatabase dbh;

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "WorkTimeDB.db";
    private static DB_Class instance;

    //le petit singleton
    public DB_Class(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        dbh = this.getWritableDatabase();
    }

    public static DB_Class getInstance (Context context)
    {
        if(instance == null)
        {
            instance = new DB_Class(context.getApplicationContext());
            //Enable foreign key support
            instance.dbh.execSQL("PRAGMA foreign_keys = ON;");
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DB_Contract.workers.CREATE_TABLE_WORKERS);
        db.execSQL(DB_Contract.companies.CREATE_TABLE_COMPANIES);
        db.execSQL(DB_Contract.activities.CREATE_TABLE_ACTIVITIES);
        db.execSQL(DB_Contract.tasks.CREATE_TABLE_TASKS);
        db.execSQL(DB_Contract.times.CREATE_TABLE_TIME);
        Log.e("DATABASE OPERATIONS", "Table created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + DB_Contract.workers.TABLE_WORKERS);
        db.execSQL("DROP TABLE IF EXISTS " + DB_Contract.companies.TABLE_COMPANIES);
        db.execSQL("DROP TABLE IF EXISTS " + DB_Contract.activities.TABLE_ACTIVITIES);
        db.execSQL("DROP TABLE IF EXISTS " + DB_Contract.tasks.TABLE_TASKS);
        db.execSQL("DROP TABLE IF EXISTS " + DB_Contract.times.TABLE_TIMES);
        onCreate(db);
    }

}

