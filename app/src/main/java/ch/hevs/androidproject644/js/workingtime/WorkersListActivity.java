package ch.hevs.androidproject644.js.workingtime;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import ch.hevs.androidproject644.js.workingtime.Adapters.WorkerAdapter;
import ch.hevs.androidproject644.js.workingtime.DB.WorkerDataSource;
import ch.hevs.androidproject644.js.workingtime.model.Datas;
import ch.hevs.androidproject644.js.workingtime.model.Worker;

public class WorkersListActivity extends AppCompatActivity {

    private ListView _lvWorkers;
    List<Worker> _workers = new ArrayList<Worker>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workers_list_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_newWorker);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WorkersListActivity.this, WorkerEditActivity.class);
                intent.putExtra(Datas.MODE, Datas.NEW);
                WorkersListActivity.this.startActivity(intent);
            }
        });

    }

    protected void onResume(){
        super.onResume();

        _lvWorkers = (ListView) findViewById(R.id.lv_workers);
        WorkerDataSource getAll = new WorkerDataSource(this);
        _workers = getAll.getAllWorkers();

        WorkerAdapter adapter = new WorkerAdapter(WorkersListActivity.this,R.layout.worker_row, _workers);
        _lvWorkers.setAdapter(adapter);

        setListener();

    }

    private void setListener() {
        _lvWorkers.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                WorkerAdapter adapter = new WorkerAdapter(WorkersListActivity.this,R.layout.worker_row, _workers);
                Worker w = adapter.getItem(position);

                Intent intent = new Intent(WorkersListActivity.this, WorkerViewActivity.class);
                intent.putExtra(Datas.MODE, Datas.VIEW);
                intent.putExtra(Datas.VIEW, w);
                WorkersListActivity.this.startActivity(intent);
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
