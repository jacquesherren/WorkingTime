package ch.hevs.androidproject644.js.workingtime;

import android.content.Intent;
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

import ch.hevs.androidproject644.js.workingtime.model.Datas;
import ch.hevs.androidproject644.js.workingtime.model.Worker;
import ch.hevs.androidproject644.js.workingtime.model.WorkersManager;

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

        //Intent intent = getIntent();

        _lvWorkers = (ListView) findViewById(R.id.lv_workers);
        _workers = WorkersManager.getAllWorker();

        WorkerAdapter adapter = new WorkerAdapter(WorkersListActivity.this, _workers);
        _lvWorkers.setAdapter(adapter);

        _lvWorkers.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                WorkerAdapter adapter = new WorkerAdapter(WorkersListActivity.this, _workers);
                Worker w = adapter.getItem(position);

                Intent intent = new Intent(WorkersListActivity.this, WorkerViewActivity.class);
                intent.putExtra(Datas.MODE, Datas.VIEW);
                intent.putExtra(Datas.VIEW, w);
                WorkersListActivity.this.startActivityForResult(intent,1);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_newWorker);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WorkersListActivity.this, WorkerEditActivity.class);
                intent.putExtra(Datas.MODE, Datas.NEW);
                WorkersListActivity.this.startActivityForResult(intent,1);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.list_menu, menu);
        return true;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == WorkerEditActivity.RESULT_OK){
                Worker result=data.getParcelableExtra("result");
            }
            if (resultCode == WorkerEditActivity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }//onActivityResult
}
