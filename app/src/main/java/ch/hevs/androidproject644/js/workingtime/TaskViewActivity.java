package ch.hevs.androidproject644.js.workingtime;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ch.hevs.androidproject644.js.workingtime.Adapters.TimeAdapter;
import ch.hevs.androidproject644.js.workingtime.Controler.C_Company;
import ch.hevs.androidproject644.js.workingtime.Controler.C_Task;
import ch.hevs.androidproject644.js.workingtime.Controler.C_Time;
import ch.hevs.androidproject644.js.workingtime.DB.TaskDataSource;
import ch.hevs.androidproject644.js.workingtime.DB.TimeDataSource;
import ch.hevs.androidproject644.js.workingtime.model.Datas;
import ch.hevs.androidproject644.js.workingtime.model.Task;
import ch.hevs.androidproject644.js.workingtime.model.Time;
import ch.hevs.androidproject644.js.workingtime.model.Worker;

public class TaskViewActivity extends AppCompatActivity {

    //private  TextView _tv_date;
    //private TextView _tv_duration;

    private TextView _tv_firstname_lastname;
    private ImageView _image_Sex;
    private TextView _tv_birthdate_value;

    private TextView _tv_activity_value;
    //private ImageView _image_activity;

    private TextView _tv_company_value;
    //private ImageView _image_company;

    private Switch _sw_task_archive;

    private ListView _lv_time;

    private Task _task;
    private List<Time> _times = new  ArrayList<Time>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_view_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    protected void onResume(){
        super.onResume();

        findViewById();

        Intent intent = getIntent();
        String sTypeOf = intent.getStringExtra(Datas.MODE);
        if (sTypeOf.equals(Datas.VIEW))
        {
            _task = intent.getParcelableExtra(Datas.VIEW);

            _tv_firstname_lastname.setText(_task.get_worker().get_firstname() + " " +  _task.get_worker().get_lastname());
            _tv_birthdate_value.setText(Datas.formatDate().format(_task.get_worker().get_birthdate().getTime()));

            if(_task.get_worker().get_sex()=='m')
                _image_Sex.setImageDrawable(ActivityCompat.getDrawable(getBaseContext(), R.mipmap.ic_male));   //.setImageDrawable(new ColorDrawable(Color.CYAN));
            else
                _image_Sex.setImageDrawable(ActivityCompat.getDrawable(getBaseContext(), R.mipmap.ic_female));

            //_image_company.setImageDrawable(ActivityCompat.getDrawable(getBaseContext(), R.drawable.ic_business_black_24dp));
            _tv_activity_value.setText(_task.get_activity().get_name());

            //_image_activity.setImageDrawable(ActivityCompat.getDrawable(getBaseContext(), R.drawable.ic_view_list_black_24dp));
            _tv_company_value.setText(_task.get_company().get_name());

            _sw_task_archive.setChecked(_task.is_archive());

            TimeDataSource getTimeByIdTask = new TimeDataSource(this);
            _times = getTimeByIdTask.getTimeByTaskId(_task.get_id());

            TimeAdapter adapter = new TimeAdapter(TaskViewActivity.this,R.layout.time_row,_times);
            _lv_time.setAdapter(adapter);

            long l = C_Task.getTotalDurationByTask(_times);

            getSupportActionBar().setTitle(C_Time.getFormatedDuration(l));

        }

        _sw_task_archive.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(_sw_task_archive.isChecked())
                    _task.set_archive(true);
                else
                    _task.set_archive(false);

                TaskDataSource updateTask = new TaskDataSource(TaskViewActivity.this);
                updateTask.updateTaskArchive(_task);
                Log.e("state saved", "state saved");

            }
        });
    }

    private void findViewById() {
        //_tv_duration = (TextView) findViewById(R.id.tv_duration);

        _tv_firstname_lastname = (TextView) findViewById(R.id.tv_firstname_lastname);
        _tv_birthdate_value = (TextView) findViewById(R.id.tv_birthdate_value);
        _image_Sex = (ImageView) findViewById(R.id.image_Sex);
        _sw_task_archive = (Switch) findViewById(R.id.sw_task_archive);
        _tv_activity_value = (TextView) findViewById(R.id.tv_activity_value);
        //_image_activity = (ImageView) findViewById(R.id.image_activity);

        _tv_company_value = (TextView) findViewById(R.id.tv_company_value);
        //_image_company = (ImageView) findViewById(R.id.image_company);

        _lv_time = (ListView) findViewById(R.id.lv_time);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.edit_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_refresh was selected
            case R.id.action_edit:
                Toast.makeText(this, "Editing task...", Toast.LENGTH_SHORT)
                        .show();
                /*Intent intent = new Intent(TaskViewActivity.this, TaskEditActivity.class);
                intent.putExtra(Datas.MODE, Datas.EDIT);
                intent.putExtra(Datas.EDIT, _task);
                TaskViewActivity.this.startActivityForResult(intent,1);*/
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
