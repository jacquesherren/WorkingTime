package ch.hevs.androidproject644.js.workingtime.DB;

import android.provider.BaseColumns;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DB_Contract  {

    public static abstract class workers implements BaseColumns {   //table name
        public static final String TABLE_WORKERS = "Workers";

        //column from table Worker
        public static final String COLUMN_NAME_WORKER_ID = "idWorker";
        public static final String COLUMN_NAME_FIRSTNAME = "Firstname";
        public static final String COLUMN_NAME_NAME = "Name";
        public static final String COLUMN_NAME_BIRTHDATE = "Birthdate";
        public static final String COLUMN_NAME_SEXE = "Sexe";
        public static final String COLUMN_NAME_AVAIABLE = "Avaiability";

        //column from

        //création de la table + les type
        public static final String CREATE_TABLE_WORKER = "CREATE TABLE "
                + TABLE_WORKERS + "("
                + workers.COLUMN_NAME_WORKER_ID + " INTEGER PRIMARY KEY, "
                + workers.COLUMN_NAME_FIRSTNAME + " TEXT, "
                + workers.COLUMN_NAME_NAME + " TEXT, "
                + workers.COLUMN_NAME_BIRTHDATE + " DATETIME ,"
                + workers.COLUMN_NAME_SEXE + " TEXT"
                + workers.COLUMN_NAME_AVAIABLE + "BOOLEAN" + ");";
    }

    public static abstract class company implements BaseColumns {
        public static final String TABLE_COMPANY = "Company";

        public static final String COLUMN_NAME_COMPANY_ID = "idCompany";
        public static final String COLUMN_NAME_COMPANY_NAME = "Name";

        public  static final String CREATE_TABLE_COMPANY = "CREATE TABLE "
                + TABLE_COMPANY + "("
                + company.COLUMN_NAME_COMPANY_ID + "INTEGER PRIMARY KEY, "
                + company.COLUMN_NAME_COMPANY_NAME + "TEXT, " + ");";
    }

    public static abstract class activity implements  BaseColumns {
        public  static final String TABLE_TASKLIST = "TasksList";

        public static final String COLUMN_NAME_ACTIVITY_ID = "idTaskList";
        public static final String COLUMN_NAME_ACTIVITY_NAME = "Name";
        public static final String COLUMN_NAME_ACTIVITY_STATUS = "Status";

        public static final String CREATE_TABLE_TASK = "CREATE TABLE "
                + TABLE_TASKLIST + "("
                + activity.COLUMN_NAME_ACTIVITY_ID + "INTEGER PRIMARY KEY, "
                + activity.COLUMN_NAME_ACTIVITY_NAME + "TEXT, "
                + activity.COLUMN_NAME_ACTIVITY_STATUS + "BOOLEAN, " + ");";
    }

    public static abstract class task implements BaseColumns {
        public static final String TABLE_TASK = "Task";

        public static final String COLUMN_NAME_TASK_ID = "idTask";
        public static final String COLUMN_NAME_TASK_DATE = "Date";
        public static final String COLUMN_NAME_TASK_DURATION = "Duration";
        public static final String COLUMN_NAME_TASK_DESCRIPTION = "Description";
        public static final String FK_COLUMN_NAME_TASK_COMPANYID = "FK_idCompany";
        public static final String FK_COLUMN_NAME_TASK_WORKERID ="FK_idWorker";
        public static final String FK_COLUMN_NAME_TASK_ACTIVITYID = "FK_idActivity";

        public static final String CREATE_TABLE_TASK = "CREATE TABLE "
                + TABLE_TASK + "("
                + task.COLUMN_NAME_TASK_ID + "INTEGER PRIMARY KEY, "
                + task.COLUMN_NAME_TASK_DATE + "DATE, "
                + task.COLUMN_NAME_TASK_DURATION + "INTEGER, "
                + task.COLUMN_NAME_TASK_DESCRIPTION + "TEXT, "
                + task.FK_COLUMN_NAME_TASK_COMPANYID + "INTEGER, "
                + task.FK_COLUMN_NAME_TASK_ACTIVITYID + "INTEGER, "
                + task.FK_COLUMN_NAME_TASK_WORKERID + "INTEGER, "
                + "FOREIGN KEY (" + task.FK_COLUMN_NAME_TASK_COMPANYID + ") REFERENCES " + company.+ " (" +
    }

    public static  abstract class time implements  BaseColumns{
        public static final String TABLE_TIME = "Time";

        public static final String COLUMN_NAME_TIME_ID = "idTime";
        public static final String COLUMN_NAME_TIME_STARTTIME = "Start";
        public static final String COLUMN_NAME_TIME_ENDTIME = "Stop";
        public static final String COLUMN_NAME_TIME_DURATION = "Duration";
        public static final String FK_COLUMN_NAME_TIME_IDTASK = "FK_idTask";

        public static final String CREATE_TABLE_TIME = "CREATE TABLE "
                + TABLE_TIME + "("
                + time.COLUMN_NAME_TIME_ID + "INTEGER PRIMARY KEY, "
                + time.COLUMN_NAME_TIME_STARTTIME + "TIME, "
                + time.COLUMN_NAME_TIME_ENDTIME + "TIME, "
                + time.COLUMN_NAME_TIME_DURATION + "INTEGER, "
                + time.FK_COLUMN_NAME_TIME_IDTASK + "INTEGER, "
                + "FOREIGN KEY (" + time.FK_COLUMN_NAME_TIME_IDTASK +") REFERENCES " + task.TABLE_TASK + " (" + task.COLUMN_NAME_TASK_ID + "));";

    }

}
