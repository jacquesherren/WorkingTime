package ch.hevs.androidproject644.js.workingtime.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.samuel.projet_android_644.DB.DB_Contract;
import com.example.samuel.projet_android_644.Object.Worker;

import ch.hevs.androidproject644.js.workingtime.DB.Object.Worker;

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
        dbh.execSQL(DB_Contract.workers.CREATE_TABLE_WORKER);
        Log.e("DATABASE OPERATIONS", "Table created");

    }

    public void addInfo(String firstname, String lastname, String sexe, SQLiteDatabase db)
    {
        db = dbh.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(DB_Contract.workers.COLUMN_NAME_FIRSTNAME, firstname);
        contentValues.put(DB_Contract.workers.COLUMN_NAME_NAME, lastname);
        contentValues.put(DB_Contract.workers.COLUMN_NAME_SEXE, sexe);
        db.insert(DB_Contract.workers.TABLE_WORKERS,null,contentValues);
        Log.e("DATABASE OPERATIONS", "Worker added");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


   Worker getWorker (int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(DB_Contract.workers.TABLE_WORKERS, new String[]{DB_Contract.workers.COLUMN_NAME_FIRSTNAME
                ,DB_Contract.workers.COLUMN_NAME_NAME, DB_Contract.workers.COLUMN_NAME_SEXE}, null, null, null, null, null);


        //Filtrer les résultat WHERE si besoin
        Worker worker = new Worker(cursor.getString(0), cursor.getString(1),  09.09.1990, 'm', true);

    }
}

