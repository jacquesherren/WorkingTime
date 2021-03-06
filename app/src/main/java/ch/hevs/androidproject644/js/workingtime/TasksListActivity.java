package ch.hevs.androidproject644.js.workingtime;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import ch.hevs.androidproject644.js.workingtime.model.Datas;
import ch.hevs.androidproject644.js.workingtime.model.Task;
import ch.hevs.androidproject644.js.workingtime.Adapters.TaskAdapter;
import ch.hevs.androidproject644.js.workingtime.model.TasksManager;

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

        _lvTasks = (ListView) findViewById(R.id.lv_tasks);
        TasksManager.set_AllTask();
        _tasks = TasksManager.getAllTasks();

        TaskAdapter adapter = new TaskAdapter(TasksListActivity.this, _tasks);
        _lvTasks.setAdapter(adapter);

        _lvTasks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TaskAdapter adapter = new TaskAdapter(TasksListActivity.this, _tasks);
                Task t = adapter.getItem(position);

                Intent intent = new Intent(TasksListActivity.this, TaskViewActivity.class);
                intent.putExtra(Datas.MODE, Datas.VIEW);
                intent.putExtra(Datas.VIEW, t);
                TasksListActivity.this.startActivityForResult(intent,1);
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
