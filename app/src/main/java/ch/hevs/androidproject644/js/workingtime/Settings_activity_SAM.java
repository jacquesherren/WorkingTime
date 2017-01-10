package ch.hevs.androidproject644.js.workingtime;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

import ch.hevs.androidproject644.js.workingtime.DB.ActivityDataSource;
import ch.hevs.androidproject644.js.workingtime.AsycnTasks.*;

import ch.hevs.androidproject644.js.workingtime.DB.CompanyDataSource;
import ch.hevs.androidproject644.js.workingtime.DB.TaskDataSource;
import ch.hevs.androidproject644.js.workingtime.DB.TimeDataSource;
import ch.hevs.androidproject644.js.workingtime.DB.WorkerDataSource;
import ch.hevs.androidproject644.js.workingtime.model.Activity;
import ch.hevs.androidproject644.js.workingtime.model.Company;
import ch.hevs.androidproject644.js.workingtime.model.Datas;
import ch.hevs.androidproject644.js.workingtime.model.Task;
import ch.hevs.androidproject644.js.workingtime.model.Time;
import ch.hevs.androidproject644.js.workingtime.model.Worker;

public class Settings_activity_SAM extends AppCompatActivity {

    private Spinner _sp_settings_dateFormat;
    private TextView _tv_settings_dateFormat;
    private TextView tv_choose_language;
    private String dateFormat;
    private Button _btn_AsyncTask;
    private Button _btn_restore;

    private static final String TAG = Settings_activity_SAM.class.getName();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings__sam);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        _sp_settings_dateFormat = (Spinner) findViewById(R.id.sp_setting_date);
        _tv_settings_dateFormat = (TextView) findViewById(R.id.tv_settings_dateFormat);
        _btn_AsyncTask = (Button) findViewById(R.id.btn_AsyncTask);
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, Datas.formatDate);
        //ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.dateFormat, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        _sp_settings_dateFormat.setAdapter(adapter);
        _sp_settings_dateFormat.setSelection(Datas.dateFormatIndex);
        _sp_settings_dateFormat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Datas.dateFormatIndex=position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        _btn_AsyncTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                syncDatasToGoogleCloud(v);

            }
        });
        tv_choose_language = (TextView) findViewById(R.id.tv_choose_language);
        final Button flag_en = (Button) findViewById(R.id.flag_en);
        flag_en.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String languageToLoad = "en";
                Locale locale = new Locale(languageToLoad);
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;
                getBaseContext().getResources().updateConfiguration(config,getBaseContext().getResources().getDisplayMetrics());

                Intent intent = new Intent(Settings_activity_SAM.this, MainActivity.class);
                startActivity(intent);

            }
        });

        final Button flag_fr = (Button) findViewById(R.id.flag_fr);
        flag_fr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String languageToLoad = "fr";
                Locale locale = new Locale(languageToLoad);
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;
                getBaseContext().getResources().updateConfiguration(config,getBaseContext().getResources().getDisplayMetrics());

                Intent intent = new Intent(Settings_activity_SAM.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }


    private void syncDatasToGoogleCloud(View v) {

        new ActivityAsyncTaskRemove().execute();

        // PUSH All activities
       List<Activity> activities ;//= new ArrayList<Activity>();
        ActivityDataSource getAllActivities = new ActivityDataSource(v.getContext());
        activities = new ArrayList<Activity>(getAllActivities.getAllActivities());
        for (Activity a : activities){
            Log.i(TAG,"current activity is " + a.get_id() );
            ch.hevs.androidproject644.js.workingtime.backend.activityApi.model.Activity ab = new ch.hevs.androidproject644.js.workingtime.backend.activityApi.model.Activity();
            ab.setId(a.get_id());
            ab.setName(a.get_name());
            ab.setActive(a.is_active());
            new ActivityAsyncTask(ab).execute();
        }

        new CompanyAsyncTaskRemove().execute();

        // PUSH All activities
        List<Company> companies ;//= new ArrayList<Activity>();
        CompanyDataSource getAllCompanies = new CompanyDataSource(v.getContext());
        companies = new ArrayList<Company>(getAllCompanies.getAllCompanies());
        for (Company c : companies){
            ch.hevs.androidproject644.js.workingtime.backend.companyApi.model.Company cb = new ch.hevs.androidproject644.js.workingtime.backend.companyApi.model.Company();
            cb.setId(c.get_id());
            cb.setName(c.get_name());
            cb.setActive(c.is_active());
            new CompanyAsyncTask(cb).execute();
        }

        new WorkerAsyncTaskRemove().execute();

        // PUSH All workers
        List<Worker> workers ;//= new ArrayList<Activity>();
        WorkerDataSource getAllWorkers = new WorkerDataSource(v.getContext());
        workers = new ArrayList<Worker>(getAllWorkers.getAllWorkers());
        for (Worker w : workers){
            ch.hevs.androidproject644.js.workingtime.backend.workerApi.model.Worker wb = new ch.hevs.androidproject644.js.workingtime.backend.workerApi.model.Worker();
            wb.setId(w.get_id());
            wb.setFirstname(w.get_firstname());
            wb.setLastname(w.get_lastname());
            wb.setSex(String.valueOf(w.get_sex()));
            wb.setBirthdate(w.get_birthdate().getTimeInMillis());
            wb.setActive(w.is_active());

            new WorkerAsyncTask(wb).execute();
        }

        new TaskAsyncTaskRemove().execute();

        // PUSH All task
        List<Task> tasks ;//= new ArrayList<Activity>();
        TaskDataSource getAllTasks = new TaskDataSource(v.getContext());
        tasks = new ArrayList<Task>(getAllTasks.getAllTasks());
        for (Task t : tasks){
            ch.hevs.androidproject644.js.workingtime.backend.taskApi.model.Task tb = new ch.hevs.androidproject644.js.workingtime.backend.taskApi.model.Task();
            tb.setId(t.get_id());
            tb.setArchive(t.is_archive());
            tb.setCompanyid(t.get_company().get_id());
            tb.setActivityid(t.get_activity().get_id());
            tb.setWorkerid(t.get_worker().get_id());
            tb.setDate(t.get_date().getTimeInMillis());

            new TaskAsyncTask(tb).execute();
        }

        new TimeAsyncTaskRemove().execute();

        // PUSH All times
        List<Time> times ;//= new ArrayList<Activity>();
        TimeDataSource getAllTimes = new TimeDataSource(v.getContext());
        times = new ArrayList<Time>(getAllTimes.getAllTime());
        for (Time t : times){
            ch.hevs.androidproject644.js.workingtime.backend.timeApi.model.Time tb = new ch.hevs.androidproject644.js.workingtime.backend.timeApi.model.Time();
            tb.setId(t.get_id());
            tb.setStart(t.get_start().getTimeInMillis());
            tb.setStop(t.get_stop().getTimeInMillis());
            tb.setDuration(t.get_duration());
            tb.setTask(t.get_task());

            new TimeAsyncTask(tb).execute();
        }
    }

}

