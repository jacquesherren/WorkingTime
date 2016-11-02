package ch.hevs.androidproject644.js.workingtime.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import ch.hevs.androidproject644.js.workingtime.DB.Worker;
import ch.hevs.androidproject644.js.workingtime.DB.DB_Class;

/**
 * Created by Samuel on 29.10.2016.
 */

public class WorkerDataSource {

    private SQLiteDatabase db;
    private Context context;

    public WorkerDataSource (Context context) {
        DB_Class dbclass = DB_Class.getInstance(context);
        db = dbclass.getWritableDatabase();
        this.context = context;
    }

    //insert a new worker

    public long createWorker (Worker worker)
    {
        long id;
        ContentValues values = new ContentValues();
        values.put(DB_Contract.workers.COLUMN_NAME_NAME, worker.getName());
        values.put(DB_Contract.workers.COLUMN_NAME_FIRSTNAME, worker.getPrename());
        values.put(DB_Contract.workers.COLUMN_NAME_BIRTHDATE, worker.getBirthdate());
        values.put(DB_Contract.workers.COLUMN_NAME_SEXE, worker.getSexe());

        id = this.db.insert(DB_Contract.workers.TABLE_WORKERS, null, values);

        return id;
    }

    public Worker getWorkerByID (long id)
    {
        String sql = "SELECT * FROM " + DB_Contract.workers.TABLE_WORKERS + " WHERE " + DB_Contract.workers.COLUMN_NAME_WORKER_ID + " = " + id;

        Cursor cursor = this.db.rawQuery(sql, null);

        if(cursor != null)
        {
            cursor.moveToFirst();
        }

        Worker worker = new Worker();
        worker.setId(cursor.getInt(cursor.getColumnIndex(DB_Contract.workers.COLUMN_NAME_WORKER_ID)));
        worker.setName(cursor.getString(cursor.getColumnIndex(DB_Contract.workers.COLUMN_NAME_NAME)));
        worker.setPrename(cursor.getString(cursor.getColumnIndex(DB_Contract.workers.COLUMN_NAME_FIRSTNAME)));
        worker.setBirthdate(cursor.getString(cursor.getColumnIndex(DB_Contract.workers.COLUMN_NAME_BIRTHDATE)));
        worker.setSexe(cursor.getString(cursor.getColumnIndex(DB_Contract.workers.COLUMN_NAME_SEXE)));

        return worker;
    }
}

