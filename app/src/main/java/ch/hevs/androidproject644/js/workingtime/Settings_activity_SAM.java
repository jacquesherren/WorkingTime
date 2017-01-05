package ch.hevs.androidproject644.js.workingtime;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Pair;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import ch.hevs.androidproject644.js.workingtime.DB.ActivityDataSource;
import ch.hevs.androidproject644.js.workingtime.AsycnTasks.*;

import ch.hevs.androidproject644.js.workingtime.model.Activity;
import ch.hevs.androidproject644.js.workingtime.model.Datas;

public class Settings_activity_SAM extends AppCompatActivity {

    private Spinner _sp_settings_dateFormat;
    private TextView _tv_settings_dateFormat;
    private TextView tv_choose_language;
    private String dateFormat;
    private Button _btn_AsyncTask;



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
                List<Activity> activities = new ArrayList<Activity>();
                ActivityDataSource getAll = new ActivityDataSource(v.getContext());
                activities = getAll.getAllActivities();
                for (Activity a : activities){
                    ch.hevs.androidproject644.js.workingtime.backend.activityApi.model.Activity ab = new ch.hevs.androidproject644.js.workingtime.backend.activityApi.model.Activity();
                    ab.setId(a.get_id());
                    ab.setName(a.get_name());
                    ab.setActive(a.is_active());
                    new ActivityAsyncTask(ab).execute();
                }
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

}

