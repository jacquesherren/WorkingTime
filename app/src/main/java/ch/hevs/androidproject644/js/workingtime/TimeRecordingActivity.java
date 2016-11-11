package ch.hevs.androidproject644.js.workingtime;

import android.icu.text.DateFormat;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import ch.hevs.androidproject644.js.workingtime.model.Datas;
import ch.hevs.androidproject644.js.workingtime.model.Task;
import ch.hevs.androidproject644.js.workingtime.model.Time;

public class TimeRecordingActivity extends AppCompatActivity {
    ToggleButton _tgb_WorkBreak;
    ImageView _img_WorkBreak;
    Chronometer _tv_duration_value;

    private Task _task;
    private Time _time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_recording_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        findViewById();
        setListener();
        _tv_duration_value.setBase(SystemClock.elapsedRealtime());
        //_tv_duration_value.setFormat("HH:MM.SS");
        //_tv_duration_value.setText(Datas.TIME_FORMATTER.format(_task.get_duration()));


        _tv_duration_value.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {

                //long minutes = ((SystemClock.elapsedRealtime()-chronometer.getBase())/1000)/60;
                //long seconds = ((SystemClock.elapsedRealtime()-chronometer.getBase())/1000)%60;
                //String currentTime = minutes+":"+seconds;
                //chronometer.setText(currentTime);


            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.save_cancel_delete_menu, menu);
        return true;
    }

    private void setListener() {
        _tgb_WorkBreak.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    _img_WorkBreak.setImageDrawable(getResources().getDrawable(R.mipmap.ic_work_in_progress));
                    _tv_duration_value.start();
                    // TODO : insert in Time table
                }
                else
                {
                    _img_WorkBreak.setImageDrawable(getResources().getDrawable(R.mipmap.ic_break));
                    _tv_duration_value.stop();
                }
            }
        });
    }

    private void findViewById(){
        _tgb_WorkBreak = (ToggleButton) findViewById(R.id.tgb_WorkBreak);
        _img_WorkBreak = (ImageView) findViewById(R.id.img_WorkBreak);
        _tv_duration_value = (Chronometer) findViewById(R.id.tv_duration_value);

    }

}
