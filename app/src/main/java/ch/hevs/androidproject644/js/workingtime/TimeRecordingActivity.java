package ch.hevs.androidproject644.js.workingtime;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ToggleButton;

public class TimeRecordingActivity extends AppCompatActivity {
    ToggleButton _tgb_WorkBreak;
    ImageView _img_WorkBreak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_recording_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        findViewById();
        setListener();

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
                    // TODO : insert in Time table
                }
                else
                {
                    _img_WorkBreak.setImageDrawable(getResources().getDrawable(R.mipmap.ic_break));
                }
            }
        });
    }

    private void findViewById(){
        _tgb_WorkBreak = (ToggleButton) findViewById(R.id.tgb_WorkBreak);
        _img_WorkBreak = (ImageView) findViewById(R.id.img_WorkBreak);

    }

}
