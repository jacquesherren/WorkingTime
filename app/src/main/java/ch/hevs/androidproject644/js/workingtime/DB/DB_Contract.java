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

        //création de la table + les type
        public static final String CREATE_TABLE_WORKER = "CREATE TABLE "
                + TABLE_WORKERS + "("
                + workers.COLUMN_NAME_WORKER_ID + " INTEGER PRIMARY KEY, "
                + workers.COLUMN_NAME_FIRSTNAME + " TEXT, "
                + workers.COLUMN_NAME_NAME + " TEXT, "
                + workers.COLUMN_NAME_BIRTHDATE + " DATE ,"
                + workers.COLUMN_NAME_SEXE + " TEXTE" + ");";
    }

}
