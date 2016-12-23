package ch.hevs.androidproject644.js.workingtime.DB;
/**V 1.5**/

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import ch.hevs.androidproject644.js.workingtime.Controler.C_Activity;
import ch.hevs.androidproject644.js.workingtime.Controler.C_Company;
import ch.hevs.androidproject644.js.workingtime.Controler.C_Task;
import ch.hevs.androidproject644.js.workingtime.Controler.C_Time;
import ch.hevs.androidproject644.js.workingtime.Controler.C_Worker;
import ch.hevs.androidproject644.js.workingtime.model.Activity;
import ch.hevs.androidproject644.js.workingtime.model.Company;
import ch.hevs.androidproject644.js.workingtime.model.Task;
import ch.hevs.androidproject644.js.workingtime.model.Time;
import ch.hevs.androidproject644.js.workingtime.model.Worker;

/**
 * Created by Samuel on 08.11.2016.
 */

public class TaskDataSource {
    private DB_Class _dbclass;
    private Context _context;

    public TaskDataSource(Context context) {
        this._context = context;
        this._dbclass = DB_Class.getInstance(context);
    }

    //Insert a new Task

    public long createTask(Task task) {
        long id;

        // TODO : Vérifier si la tâche (task) avec les FK choisies existent déjà.
        SQLiteDatabase db = _dbclass.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DB_Contract.tasks.COLUMN_NAME_TASK_DATE, task.get_date().toString()); //à controler selon date de jacques );
        values.put(DB_Contract.tasks.COLUMN_NAME_TASK_DURATION, task.get_duration());
        values.put(DB_Contract.tasks.COLUMN_NAME_ARCHIVE, task.is_archive_int());
//        values.put(DB_Contract.tasks.COLUMN_NAME_TASK_DESCRIPTION, task.get_description().toString());

        //A voir comment récupérer la FK
        values.put(DB_Contract.tasks.FK_COLUMN_NAME_TASK_COMPANYID, task.get_company().get_id());
        values.put(DB_Contract.tasks.FK_COLUMN_NAME_TASK_WORKERID, task.get_worker().get_id());
        values.put(DB_Contract.tasks.FK_COLUMN_NAME_TASK_ACTIVITYID, task.get_activity().get_id());

        id = db.insert(DB_Contract.tasks.TABLE_TASKS, null, values);
        Log.e("WORKER CREATED", "test created");
        return id;
    }

    public int updateTaskArchive(Task task) {
        SQLiteDatabase db = _dbclass.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DB_Contract.tasks.COLUMN_NAME_ARCHIVE, task.is_archive_int());
        // updating row
        Log.e("update archive", "Update archive");
        return db.update(DB_Contract.tasks.TABLE_TASKS, values, DB_Contract.tasks.COLUMN_NAME_TASK_ID + " = ?",
                new String[]{String.valueOf(task.get_id())});


    }
    public Task getTaskByID(long id)
    {
        //getreadable ici
        SQLiteDatabase db = _dbclass.getReadableDatabase();
        final String sql = "SELECT * FROM " + DB_Contract.tasks.TABLE_TASKS + " WHERE " + DB_Contract.tasks.COLUMN_NAME_TASK_ID + " = " + id;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null) {
            cursor.moveToFirst();
            Task task = cursorToTask(cursor);
            return task;
        }
        return null;
    }

    public boolean getTaskByWorkerID(long id)
    {
        //getreadable ici
        SQLiteDatabase db = _dbclass.getReadableDatabase();
        final String sql = "SELECT * FROM " + DB_Contract.tasks.TABLE_TASKS + " WHERE " + DB_Contract.tasks.FK_COLUMN_NAME_TASK_WORKERID + " = " + id;
        Cursor cursor = db.rawQuery(sql, null);
        if ( cursor.getCount() >0)
            return true;

        return false;
    }

    public boolean getTaskByActivityID(long id)
    {
        //getreadable ici
        SQLiteDatabase db = _dbclass.getReadableDatabase();
        final String sql = "SELECT * FROM " + DB_Contract.tasks.TABLE_TASKS + " WHERE " + DB_Contract.tasks.FK_COLUMN_NAME_TASK_ACTIVITYID + " = " + id;
        Cursor cursor = db.rawQuery(sql, null);
        if ( cursor.getCount() >0)
            return true;

        return false;
    }


    public boolean getTaskByCompanyID(long id)
    {
        //getreadable ici
        SQLiteDatabase db = _dbclass.getReadableDatabase();
        final String sql = "SELECT * FROM " + DB_Contract.tasks.TABLE_TASKS + " WHERE " + DB_Contract.tasks.FK_COLUMN_NAME_TASK_COMPANYID + " = " + id;
        Cursor cursor = db.rawQuery(sql, null);
        if ( cursor.getCount() >0)
            return true;

        return false;
    }
    public List<Task> getTasksByAvailable() {

        List<Task> tasks = new ArrayList<>();
        SQLiteDatabase db = _dbclass.getReadableDatabase();
        String sql = "SELECT * FROM " + DB_Contract.tasks.TABLE_TASKS + " WHERE "  + DB_Contract.tasks.COLUMN_NAME_ARCHIVE + " = " + 0;
        Cursor cursor = db.rawQuery(sql, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Task task = cursorToTask(cursor);
            tasks.add(task);
            cursor.moveToNext();
        }

        cursor.close();
        return tasks;
    }


    public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<>();

        SQLiteDatabase db = _dbclass.getReadableDatabase();
        String sql = "SELECT * FROM " + DB_Contract.tasks.TABLE_TASKS;
        Cursor cursor = db.rawQuery(sql, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Task task = cursorToTask(cursor);
            tasks.add(task);
            cursor.moveToNext();
        }
        cursor.close();
        return tasks;
    }

    private Task cursorToTask(Cursor cursor) {
        Task task = new Task();

        task.set_id(cursor.getInt(cursor.getColumnIndex(DB_Contract.tasks.COLUMN_NAME_TASK_ID)));
        task.set_date(cursor.getLong(cursor.getColumnIndex(DB_Contract.tasks.COLUMN_NAME_TASK_DATE)));
        task.set_duration(cursor.getInt(cursor.getColumnIndex(DB_Contract.tasks.COLUMN_NAME_TASK_DURATION)));
        task.set_active_int(cursor.getInt(cursor.getColumnIndex(DB_Contract.tasks.COLUMN_NAME_ARCHIVE)));

        Worker worker = C_Worker.getWorkerById(cursor.getInt(cursor.getColumnIndex(DB_Contract.tasks.FK_COLUMN_NAME_TASK_WORKERID)), _context);
        task.set_worker(worker);

        Activity activity = C_Activity.getWorkerById(cursor.getInt(cursor.getColumnIndex(DB_Contract.tasks.FK_COLUMN_NAME_TASK_ACTIVITYID)),_context);
        task.set_activity(activity);

        Company company = C_Company.getCompanyById(cursor.getInt(cursor.getColumnIndex(DB_Contract.tasks.FK_COLUMN_NAME_TASK_COMPANYID)),_context);
        task.set_company(company);



        //List<Time> times = C_Time.getTimesByTaskId(task.get_id(),_context);
        //task.set_timeId();

        return task;
    }
}
