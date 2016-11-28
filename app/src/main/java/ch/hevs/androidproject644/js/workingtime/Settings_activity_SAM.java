package ch.hevs.androidproject644.js.workingtime;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Locale;

import ch.hevs.androidproject644.js.workingtime.model.Datas;

public class Settings_activity_SAM extends AppCompatActivity {

    private Spinner _sp_settings_dateFormat;
    private TextView _tv_settings_dateFormat;
    private TextView tv_choose_language;
    private String dateFormat;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings__sam);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        _sp_settings_dateFormat = (Spinner) findViewById(R.id.sp_setting_date);
        _tv_settings_dateFormat = (TextView) findViewById(R.id.tv_settings_dateFormat);
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

