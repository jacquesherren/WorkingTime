package ch.hevs.androidproject644.js.workingtime;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

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


        Intent intent = getIntent();
        intent.getClass();

        _lvWorkers = (ListView) findViewById(R.id.lv_workers);
        _workers = get_AllWorkers();

        WorkerAdapter adapter = new WorkerAdapter(WorkersListActivity.this, _workers);
        _lvWorkers.setAdapter(adapter);

        _lvWorkers.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                WorkerAdapter adapter = new WorkerAdapter(WorkersListActivity.this, _workers);
                Worker w = adapter.getItem(position);

                Intent intent = new Intent(WorkersListActivity.this, WorkerViewActivity.class);
                intent.putExtra(Datas.WORKER_MODE, Datas.WORKER_VIEW);
                intent.putExtra(Datas.WORKER_VIEW, w);
                WorkersListActivity.this.startActivity(intent);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_newWorker);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(WorkersListActivity.this, WorkerEditActivity.class);
                intent.putExtra(Datas.WORKER_MODE, Datas.WORKER_NEW);
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

    private List<Worker> get_AllWorkers(){
        List<Worker> workers = new ArrayList<Worker>();

        workers.add(new Worker(1,"Duchmol","Florent", Date.valueOf("1980-07-07"),'m',false));
        workers.add(new Worker(2,"Schaler","Noémie", Date.valueOf("1991-12-12"),'f',true));
        workers.add(new Worker(3,"Dupont","Thierry", Date.valueOf("1971-10-09"),'m',false));
        workers.add(new Worker(4,"Fournier","Pierre", Date.valueOf("1996-01-08"),'m',true));
        workers.add(new Worker(5,"Fournier","Pierre", Date.valueOf("1948-12-18"),'m',true));
        workers.add(new Worker(6,"Fournier","Clémentine", Date.valueOf("1996-01-08"),'f',true));
        workers.add(new Worker(7,"Nanchen","Xavier", Date.valueOf("1969-10-27"),'m',true));
        workers.add(new Worker(8,"Coppey","Samuel", Date.valueOf("1991-05-18"),'m',true));
        workers.add(new Worker(9,"Pannatier","Alphonse", Date.valueOf("1990-10-18"),'m',false));
        workers.add(new Worker(10,"Herren","Jacques", Date.valueOf("1975-12-29"),'m',true));
        workers.add(new Worker(11,"Herren","Véronique", Date.valueOf("1981-01-08"),'f',true));
        workers.add(new Worker(12,"Nobs","Olivia", Date.valueOf("1986-05-05"),'f',true));

        return workers;
    }

}
