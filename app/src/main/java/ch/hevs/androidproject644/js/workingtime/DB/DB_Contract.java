package ch.hevs.androidproject644.js.workingtime.DB;

import android.provider.BaseColumns;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DB_Contract  {

    //WORKERS ***
    public static abstract class workers implements BaseColumns {
        //table name
        public static final String TABLE_WORKERS = "Workers";

        //column from table Worker
        public static final String COLUMN_NAME_WORKER_ID = "idWorker";
        public static final String COLUMN_NAME_FIRSTNAME = "Firstname";
        public static final String COLUMN_NAME_LASTNAME = "Name";
        public static final String COLUMN_NAME_BIRTHDATE = "Birthdate";
        public static final String COLUMN_NAME_SEXE = "Sexe";
        public static final String COLUMN_NAME_AVAILABLE = "Availability";

        //création de la table + les type
        public static final String CREATE_TABLE_WORKERS = "CREATE TABLE "
                + TABLE_WORKERS + "("
                + workers.COLUMN_NAME_WORKER_ID + " INTEGER PRIMARY KEY, "
                + workers.COLUMN_NAME_FIRSTNAME + " TEXT, "
                + workers.COLUMN_NAME_LASTNAME + " TEXT, "
                + workers.COLUMN_NAME_BIRTHDATE + " NUMERIC, "
                + workers.COLUMN_NAME_SEXE + " TEXT , " //+");";
                + workers.COLUMN_NAME_AVAILABLE + " INTEGER" + ");";
    }

    //COMPANIES ***
    public static abstract class companies implements BaseColumns {
        //table name
        public static final String TABLE_COMPANIES = "Companies";

        //column from table companies
        public static final String COLUMN_NAME_COMPANY_ID = "idCompany";
        public static final String COLUMN_NAME_COMPANY_NAME = "Name";
        public static final String COLUMN_NAME_AVAILABLE = "Availability";

        //création de la table + les type
        public  static final String CREATE_TABLE_COMPANIES = "CREATE TABLE "
                + TABLE_COMPANIES + "("
                + companies.COLUMN_NAME_COMPANY_ID + " INTEGER PRIMARY KEY, "
                + companies.COLUMN_NAME_COMPANY_NAME + " TEXT, "
                + companies.COLUMN_NAME_AVAILABLE + " INTEGER" + ");";
    }

    //ACTIVITIES ***
    public static abstract class activities implements BaseColumns {
        //table name
        public static final String TABLE_ACTIVITIES = "Activities";

        //column from table activities
        public static final String COLUMN_NAME_ACTIVITY_ID = "idActivity";
        public static final String COLUMN_NAME_ACTIVITY_NAME = "Name";
        public static final String COLUMN_NAME_AVAILABLE = "Availability";

        //création de la table + les type
        public  static final String CREATE_TABLE_ACTIVITIES = "CREATE TABLE "
                + TABLE_ACTIVITIES + "("
                + activities.COLUMN_NAME_ACTIVITY_ID + " INTEGER PRIMARY KEY, "
                + activities.COLUMN_NAME_ACTIVITY_NAME + " TEXT, "
                + activities.COLUMN_NAME_AVAILABLE + " INTEGER" + ");";
    }

    public static abstract class tasks implements BaseColumns {
        //table name
        public static final String TABLE_TASKS = "Tasks";

        //column from table tasks
        public static final String COLUMN_NAME_TASK_ID = "idTask";
        public static final String COLUMN_NAME_TASK_DATE = "Date";
        public static final String COLUMN_NAME_TASK_DURATION = "Duration";
        public static final String COLUMN_NAME_TASK_DESCRIPTION = "Description";
        public static final String FK_COLUMN_NAME_TASK_COMPANYID = "FK_idCompany";
        public static final String FK_COLUMN_NAME_TASK_WORKERID ="FK_idWorker";
        public static final String FK_COLUMN_NAME_TASK_ACTIVITYID = "FK_idActivity";

        //création de la table + les type
        public static final String CREATE_TABLE_TASKS = "CREATE TABLE "
                + TABLE_TASKS + "("
                + tasks.COLUMN_NAME_TASK_ID + " INTEGER PRIMARY KEY, "
                + tasks.COLUMN_NAME_TASK_DATE + " NUMERIC, "
                + tasks.COLUMN_NAME_TASK_DURATION + " INTEGER, "
                + tasks.COLUMN_NAME_TASK_DESCRIPTION + " TEXT, "
                + tasks.FK_COLUMN_NAME_TASK_COMPANYID + " INTEGER, "
                + tasks.FK_COLUMN_NAME_TASK_ACTIVITYID + " INTEGER, "
                + tasks.FK_COLUMN_NAME_TASK_WORKERID + " INTEGER, "
                + "FOREIGN KEY (" + tasks.FK_COLUMN_NAME_TASK_WORKERID + ") REFERENCES " + workers.TABLE_WORKERS + " (" + workers.COLUMN_NAME_WORKER_ID +"), "
                + "FOREIGN KEY (" + tasks.FK_COLUMN_NAME_TASK_ACTIVITYID + ") REFERENCES " + activities.TABLE_ACTIVITIES + "( " + activities.COLUMN_NAME_ACTIVITY_ID +"), "
                + "FOREIGN KEY (" + tasks.FK_COLUMN_NAME_TASK_COMPANYID + ") REFERENCES " + companies.TABLE_COMPANIES + " (" + companies.COLUMN_NAME_COMPANY_ID + "));";

    }

    public static  abstract class times implements  BaseColumns{
        //table name
        public static final String TABLE_TIMES = "Time";

        public static final String COLUMN_NAME_TIME_ID = "idTime";
        public static final String COLUMN_NAME_TIME_STARTTIME = "Start";
        public static final String COLUMN_NAME_TIME_ENDTIME = "Stop";
        public static final String COLUMN_NAME_TIME_DURATION = "Duration";
        public static final String FK_COLUMN_NAME_TIME_IDTASK = "FK_idTask";

        public static final String CREATE_TABLE_TIME = "CREATE TABLE "
                + TABLE_TIMES + "("
                + times.COLUMN_NAME_TIME_ID + " INTEGER PRIMARY KEY, "
                + times.COLUMN_NAME_TIME_STARTTIME + " NUMERIC, "
                + times.COLUMN_NAME_TIME_ENDTIME + " NUMERIC, "
                + times.COLUMN_NAME_TIME_DURATION + " INTEGER, "
                + times.FK_COLUMN_NAME_TIME_IDTASK + " INTEGER, "
                + "FOREIGN KEY (" + times.FK_COLUMN_NAME_TIME_IDTASK +") REFERENCES " + tasks.TABLE_TASKS + " (" + tasks.COLUMN_NAME_TASK_ID + "));";

    }

}
