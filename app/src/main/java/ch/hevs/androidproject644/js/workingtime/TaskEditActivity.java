package ch.hevs.androidproject644.js.workingtime;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ch.hevs.androidproject644.js.workingtime.Adapters.ActivityAdapter;
import ch.hevs.androidproject644.js.workingtime.Adapters.CompanyAdapter;
import ch.hevs.androidproject644.js.workingtime.Adapters.WorkerAdapter;
import ch.hevs.androidproject644.js.workingtime.DB.ActivityDataSource;
import ch.hevs.androidproject644.js.workingtime.DB.CompanyDataSource;
import ch.hevs.androidproject644.js.workingtime.DB.TaskDataSource;
import ch.hevs.androidproject644.js.workingtime.DB.WorkerDataSource;
import ch.hevs.androidproject644.js.workingtime.model.Activity;
import ch.hevs.androidproject644.js.workingtime.model.Company;
import ch.hevs.androidproject644.js.workingtime.model.Datas;
import ch.hevs.androidproject644.js.workingtime.model.Task;
import ch.hevs.androidproject644.js.workingtime.model.Worker;

public class TaskEditActivity extends AppCompatActivity {
    private Spinner _sp_chooseWorker;
    private Spinner _sp_chooseCompany;
    private Spinner _sp_chooseActivity;

    private Button _btn_newWorker;
    private Button _btn_newCompany;
    private Button _btn_newActivity;

    private Button btn_Create;

    private List<Worker> _workers = new ArrayList<Worker>();
    private List<Activity> _activities = new ArrayList<Activity>();
    private List<Company> _companies = new ArrayList<Company>();

    private Company _c;
    private Worker _w;
    private Activity _a;

    private Task _task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_edit_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        findViewById();
        setListener();

        WorkerDataSource wreq = new WorkerDataSource(this);
        _workers = wreq.getAllWorkers();
        ActivityDataSource areq = new ActivityDataSource(this);
        _activities = areq.getAllActivities();
        CompanyDataSource creq = new CompanyDataSource(this);
        _companies = creq.getAllCompanies();

        // Create custom adapter object ( see below CustomAdapter.java )
        WorkerAdapter wAdapter = new WorkerAdapter(this,R.layout.worker_row, _workers);
        ActivityAdapter aAdapter = new ActivityAdapter(this,R.layout.activity_row,_activities);
        CompanyAdapter cAdapter = new CompanyAdapter(this,R.layout.company_row, _companies);

        // Set adapter to spinner
        _sp_chooseWorker.setAdapter(wAdapter);
        _sp_chooseActivity.setAdapter(aAdapter);
        _sp_chooseCompany.setAdapter(cAdapter);


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.save_cancel_delete_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_refresh was selected
            case R.id.action_save:
                save();
                break;

            // action with ID action_settings was selected
            case R.id.action_cancel:
                Intent returnIntent = new Intent();
                setResult(CompanyEditActivity.RESULT_CANCELED, returnIntent);
                finish();
                break;

            case R.id.action_delete:
                //delete();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void save(){


        if(_task!=null){
            Toast.makeText(this, "Saving this task...", Toast.LENGTH_SHORT)
                    .show();

            setTask();

            TaskDataSource updateTask = new TaskDataSource(this);
            // TODO : updateTask.updateTask(_task);

        }
        else {
            Toast.makeText(this, "Creating a new task...", Toast.LENGTH_SHORT)
                    .show();

            _task = new Task();
            setTask();


            TaskDataSource addTask = new TaskDataSource(this);
            addTask.createTask(_task);
        }
        Intent returnIntent = new Intent();
        returnIntent.putExtra("result",_task);
        setResult(CompanyEditActivity.RESULT_OK,returnIntent);
        finish();
    }

    private void setTask() {
        _task.set_date(Calendar.getInstance());

        if(_a!=null)
            _task.set_activity(_a);
        if(_w!=null)
            _task.set_worker(_w);
        if(_c!=null)
            _task.set_company(_c);

    }
    private void findViewById() {
        _sp_chooseWorker = (Spinner) findViewById(R.id.sp_chooseWorker);
        _sp_chooseCompany = (Spinner) findViewById(R.id.sp_chooseCompany);
        _sp_chooseActivity = (Spinner) findViewById(R.id.sp_chooseActivity);
        _btn_newWorker = (Button) findViewById(R.id.btn_newWorker);
        _btn_newCompany = (Button) findViewById(R.id.btn_newCompany);
        _btn_newActivity = (Button) findViewById(R.id.btn_newActivity);

    }

    private void setListener() {

        // Listener called when spinner item selected
        _sp_chooseCompany.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View v, int position, long id) {
                CompanyAdapter adapter = new CompanyAdapter(TaskEditActivity.this,R.layout.company_row, _companies);
                _c = adapter.getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        _sp_chooseWorker.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                WorkerAdapter adapter = new WorkerAdapter(TaskEditActivity.this,R.layout.worker_row,_workers);
                _w = adapter.getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        _sp_chooseActivity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ActivityAdapter adapter = new ActivityAdapter(TaskEditActivity.this,R.layout.activity_row,_activities);
                _a = adapter.getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

}
