package ch.hevs.androidproject644.js.workingtime;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ch.hevs.androidproject644.js.workingtime.DB.TaskDataSource;
import ch.hevs.androidproject644.js.workingtime.DB.TimeDataSource;
import ch.hevs.androidproject644.js.workingtime.model.Datas;
import ch.hevs.androidproject644.js.workingtime.model.Task;
import ch.hevs.androidproject644.js.workingtime.Adapters.TaskAdapter;
import ch.hevs.androidproject644.js.workingtime.model.Time;

public class TasksListActivity extends AppCompatActivity {

    private ListView _lvTasks;
    List<Task> _tasks = new ArrayList<Task>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tasks_list_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TasksListActivity.this, TaskEditActivity.class);
                intent.putExtra(Datas.MODE, Datas.NEW);
                TasksListActivity.this.startActivityForResult(intent,1);
            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();

        _lvTasks = (ListView) findViewById(R.id.lv_tasks);
        TaskDataSource getAll = new TaskDataSource(this);
        _tasks = getAll.getAllTasks();
        TaskAdapter adapter = new TaskAdapter(TasksListActivity.this,R.layout.task_row, _tasks,true,0);
        _lvTasks.setAdapter(adapter);

        _lvTasks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TaskAdapter adapter = new TaskAdapter(TasksListActivity.this,R.layout.task_row, _tasks,true,0);
                Task t = adapter.getItem(position);

                Intent intent = new Intent(TasksListActivity.this, TaskViewActivity.class);
                intent.putExtra(Datas.MODE, Datas.VIEW);
                intent.putExtra(Datas.VIEW, t);
                TasksListActivity.this.startActivity(intent);
            }
        });

        _lvTasks.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("Long Click detected", "Long click");

                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.list_menu, menu);
        return true;
    }

}
