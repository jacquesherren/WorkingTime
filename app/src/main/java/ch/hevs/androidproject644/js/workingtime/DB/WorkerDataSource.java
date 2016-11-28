package ch.hevs.androidproject644.js.workingtime.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import ch.hevs.androidproject644.js.workingtime.Controler.C_Worker;
import ch.hevs.androidproject644.js.workingtime.model.Activity;
import ch.hevs.androidproject644.js.workingtime.model.Worker;

/**
 * Created by Samuel on 29.10.2016.
 */

public class WorkerDataSource {
    private DB_Class _dbclass;

    public WorkerDataSource(Context context) {
        _dbclass = DB_Class.getInstance(context);
    }

    //insert a new worker
    public long createWorker(Worker worker) {
        long id;
        SQLiteDatabase db = _dbclass.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DB_Contract.workers.COLUMN_NAME_FIRSTNAME, worker.get_firstname());
        values.put(DB_Contract.workers.COLUMN_NAME_LASTNAME, worker.get_lastname());
        values.put(DB_Contract.workers.COLUMN_NAME_BIRTHDATE, worker.get_birthdate().getTimeInMillis());
        values.put(DB_Contract.workers.COLUMN_NAME_SEXE, Character.toString(worker.get_sex()));
        values.put(DB_Contract.workers.COLUMN_NAME_AVAILABLE, worker.is_active_int());

        id = db.insert(DB_Contract.workers.TABLE_WORKERS, null, values);
        Log.e("WORKER CREATED", "test created");
        return id;
    }

    // Updating single contact
    public int updateWorker(Worker worker) {
        SQLiteDatabase db = _dbclass.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DB_Contract.workers.COLUMN_NAME_FIRSTNAME, worker.get_firstname());
        values.put(DB_Contract.workers.COLUMN_NAME_LASTNAME, worker.get_lastname());
        values.put(DB_Contract.workers.COLUMN_NAME_BIRTHDATE, worker.get_birthdate().getTimeInMillis());
        values.put(DB_Contract.workers.COLUMN_NAME_SEXE, Character.toString(worker.get_sex()));
        values.put(DB_Contract.workers.COLUMN_NAME_AVAILABLE, worker.is_active_int());

        // updating row
        return db.update(DB_Contract.workers.TABLE_WORKERS, values, DB_Contract.workers.COLUMN_NAME_WORKER_ID + " = ?",
                new String[] { String.valueOf(worker.get_id()) });
    }

    public Worker getWorkerByID(long id) {
        //getreadable ici
        SQLiteDatabase db = _dbclass.getReadableDatabase();
        String sql = "SELECT * FROM " + DB_Contract.workers.TABLE_WORKERS + " WHERE " + DB_Contract.workers.COLUMN_NAME_WORKER_ID + " = " + id;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null) {
            cursor.moveToFirst();
            Worker worker = cursorToWorker(cursor);
            return worker;
        }
        return null;
    }

    public Worker getTimeByWorker()
    {
        SQLiteDatabase db = _dbclass.getReadableDatabase();
        String sql = "SELECT " + DB_Contract.workers.COLUMN_NAME_LASTNAME + "SUM (" + DB_Contract.times.COLUMN_NAME_TIME_DURATION + ")" + " FROM " + DB_Contract.workers.TABLE_WORKERS + ", " + DB_Contract.tasks.TABLE_TASKS + ", " + DB_Contract.times.TABLE_TIMES + " WHERE " + DB_Contract.workers.COLUMN_NAME_WORKER_ID + " = " + DB_Contract.tasks.FK_COLUMN_NAME_TASK_WORKERID + " AND " + DB_Contract.tasks.COLUMN_NAME_TASK_ID + " = " + DB_Contract.times.FK_COLUMN_NAME_TIME_IDTASK + " GROUP BY " + DB_Contract.workers.COLUMN_NAME_LASTNAME;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null) {
            cursor.moveToFirst();
            Worker worker = cursorToWorker(cursor);
            return worker;
        }
        return null;
    }

    public List<Worker> getWorkerByAvaiable()
    {
        List<Worker> workers = new ArrayList<Worker>();
        SQLiteDatabase db = _dbclass.getReadableDatabase();
        String sql = "SELECT * FROM " + DB_Contract.workers.TABLE_WORKERS + " WHERE " + DB_Contract.workers.COLUMN_NAME_AVAILABLE + " = " + 1;
        Cursor cursor = db.rawQuery(sql, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Worker worker = cursorToWorker(cursor);
            workers.add(worker);
            cursor.moveToNext();
        }

        cursor.close();
        return workers;
    }

    public List<Worker> getAllWorkers() {
        List<Worker> workers = new ArrayList<Worker>();
        SQLiteDatabase db = _dbclass.getReadableDatabase();
        String sql = "SELECT * FROM " + DB_Contract.workers.TABLE_WORKERS;
        Cursor cursor = db.rawQuery(sql, null);

         cursor.moveToFirst();
         while (!cursor.isAfterLast()) {
              Worker worker = cursorToWorker(cursor);
              workers.add(worker);
              cursor.moveToNext();
         }
        //}

        Log.e("WORKERS LIST ",workers.toString());
        cursor.close();
        return workers;
}

    public void deleteWorker(Worker worker) {
        SQLiteDatabase db = _dbclass.getWritableDatabase();
        db.delete(DB_Contract.workers.TABLE_WORKERS, DB_Contract.workers.COLUMN_NAME_WORKER_ID + " = ?",
                new String[] { String.valueOf(worker.get_id()) });
        db.close();
    }


    public List<Worker> getSumTimeByWorkerbetweenDate(long dateFrom, long dateTo)
    {
        List<Worker> workers = new ArrayList<Worker>();
        SQLiteDatabase db = _dbclass.getReadableDatabase();
        String sql = "SELECT " + DB_Contract.workers.TABLE_WORKERS + "." + DB_Contract.workers.COLUMN_NAME_WORKER_ID + ", " + DB_Contract.workers.TABLE_WORKERS + "." + DB_Contract.workers.COLUMN_NAME_FIRSTNAME + ", " + DB_Contract.workers.TABLE_WORKERS + "." + DB_Contract.workers.COLUMN_NAME_LASTNAME + ", " + DB_Contract.workers.TABLE_WORKERS + "." + DB_Contract.workers.COLUMN_NAME_BIRTHDATE + ", " + DB_Contract.workers.TABLE_WORKERS + "." + DB_Contract.workers.COLUMN_NAME_SEXE + ", " + DB_Contract.workers.TABLE_WORKERS + "." + DB_Contract.workers.COLUMN_NAME_AVAILABLE + ", SUM(" + DB_Contract.times.TABLE_TIMES + "." +  DB_Contract.times.COLUMN_NAME_TIME_DURATION + ") FROM " + DB_Contract.workers.TABLE_WORKERS + ", " + DB_Contract.tasks.TABLE_TASKS + ", " + DB_Contract.times.TABLE_TIMES + " WHERE " + DB_Contract.workers.TABLE_WORKERS + "." + DB_Contract.workers.COLUMN_NAME_WORKER_ID + " = " + DB_Contract.tasks.TABLE_TASKS + "." + DB_Contract.tasks.FK_COLUMN_NAME_TASK_WORKERID + " AND " + DB_Contract.tasks.TABLE_TASKS + "." + DB_Contract.tasks.COLUMN_NAME_TASK_ID + " = " + DB_Contract.times.TABLE_TIMES + "." + DB_Contract.times.FK_COLUMN_NAME_TIME_IDTASK + " AND " + DB_Contract.times.TABLE_TIMES + "." + DB_Contract.times.COLUMN_NAME_TIME_STARTTIME + " BETWEEN " + dateFrom   + " AND " + dateTo  + " GROUP BY " +  DB_Contract.workers.TABLE_WORKERS + "." + DB_Contract.workers.COLUMN_NAME_WORKER_ID + ", " + DB_Contract.workers.TABLE_WORKERS + "." + DB_Contract.workers.COLUMN_NAME_FIRSTNAME + ", " + DB_Contract.workers.TABLE_WORKERS + "." + DB_Contract.workers.COLUMN_NAME_LASTNAME + ", " + DB_Contract.workers.TABLE_WORKERS + "." + DB_Contract.workers.COLUMN_NAME_BIRTHDATE + ", " + DB_Contract.workers.TABLE_WORKERS + "." + DB_Contract.workers.COLUMN_NAME_SEXE + ", " + DB_Contract.workers.TABLE_WORKERS + "." + DB_Contract.workers.COLUMN_NAME_AVAILABLE;
        //String sql = "SELECT SUM(" + DB_Contract.times.TABLE_TIMES + "." +  DB_Contract.times.COLUMN_NAME_TIME_DURATION + ") FROM " + DB_Contract.companies.TABLE_COMPANIES + ", " + DB_Contract.tasks.TABLE_TASKS + ", " + DB_Contract.times.TABLE_TIMES + " WHERE " + DB_Contract.companies.TABLE_COMPANIES + "." + DB_Contract.companies.COLUMN_NAME_COMPANY_ID + " = " + DB_Contract.tasks.TABLE_TASKS + "." + DB_Contract.tasks.FK_COLUMN_NAME_TASK_COMPANYID + " AND " + DB_Contract.tasks.TABLE_TASKS + "." + DB_Contract.tasks.COLUMN_NAME_TASK_ID + " = " + DB_Contract.times.TABLE_TIMES + "." + DB_Contract.times.FK_COLUMN_NAME_TIME_IDTASK ;
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Worker worker = cursorToWorker(cursor);
            worker.set_duration(cursor.getInt(3));
            workers.add(worker);
            cursor.moveToNext();
        }
        return workers;
    }
    private Worker cursorToWorker(Cursor cursor) {
        Worker worker = new Worker();

        worker.set_id(cursor.getInt(cursor.getColumnIndex(DB_Contract.workers.COLUMN_NAME_WORKER_ID)));
        worker.set_firstname(cursor.getString(cursor.getColumnIndex(DB_Contract.workers.COLUMN_NAME_FIRSTNAME)));
        worker.set_lastname(cursor.getString(cursor.getColumnIndex(DB_Contract.workers.COLUMN_NAME_LASTNAME)));
        worker.set_birthdate(cursor.getLong(cursor.getColumnIndex(DB_Contract.workers.COLUMN_NAME_BIRTHDATE)));
        worker.set_sex(cursor.getString(cursor.getColumnIndex(DB_Contract.workers.COLUMN_NAME_SEXE)).charAt(0));
        worker.set_active_int(cursor.getInt(cursor.getColumnIndex(DB_Contract.workers.COLUMN_NAME_AVAILABLE)));

        return worker;
    }
}

