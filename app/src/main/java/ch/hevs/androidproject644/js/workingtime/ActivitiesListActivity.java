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

import ch.hevs.androidproject644.js.workingtime.Adapters.ActivityAdapter;
import ch.hevs.androidproject644.js.workingtime.DB.ActivityDataSource;
import ch.hevs.androidproject644.js.workingtime.model.Activity;
import ch.hevs.androidproject644.js.workingtime.model.Datas;


public class ActivitiesListActivity extends AppCompatActivity {

    private ListView _lvActivities;
    List<Activity> _activities = new ArrayList<Activity>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activities_list_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivitiesListActivity.this, ActivityEditActivity.class);
                intent.putExtra(Datas.MODE, Datas.NEW);
                ActivitiesListActivity.this.startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        _lvActivities = (ListView) findViewById(R.id.lv_activities);
        ActivityDataSource getAll = new ActivityDataSource(this);
        _activities = getAll.getAllActivities();

        ActivityAdapter adapter = new ActivityAdapter(ActivitiesListActivity.this,R.layout.activity_row, _activities);
        _lvActivities.setAdapter(adapter);

        setListener();

    }

    private void setListener() {
        _lvActivities.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ActivityAdapter adapter = new ActivityAdapter(ActivitiesListActivity.this,R.layout.activity_row, _activities);
                Activity a = adapter.getItem(position);

                Intent intent = new Intent(ActivitiesListActivity.this, ActivityViewActivity.class);
                intent.putExtra(Datas.MODE, Datas.VIEW);
                intent.putExtra(Datas.VIEW, a);
                ActivitiesListActivity.this.startActivity(intent);
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
