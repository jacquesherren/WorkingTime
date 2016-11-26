package ch.hevs.androidproject644.js.workingtime;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ch.hevs.androidproject644.js.workingtime.Adapters.CompanyAdapter;
import ch.hevs.androidproject644.js.workingtime.Adapters.TaskAdapter;
import ch.hevs.androidproject644.js.workingtime.Controler.C_Time;
import ch.hevs.androidproject644.js.workingtime.DB.CompanyDataSource;
import ch.hevs.androidproject644.js.workingtime.DB.TaskDataSource;
import ch.hevs.androidproject644.js.workingtime.DB.TimeDataSource;
import ch.hevs.androidproject644.js.workingtime.model.Company;
import ch.hevs.androidproject644.js.workingtime.model.Datas;
import ch.hevs.androidproject644.js.workingtime.model.Task;
import ch.hevs.androidproject644.js.workingtime.model.Time;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private ListView _lvtasks;
    private List<Task> _tasks = new ArrayList<Task>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TaskEditActivity.class);
                intent.putExtra(Datas.MODE, Datas.NEW);
                MainActivity.this.startActivityForResult(intent,1);
            }
        });
    }




    protected void onResume(){
        super.onResume();

        _lvtasks = (ListView) findViewById(R.id.lv_tasks);
        TaskDataSource getAll = new TaskDataSource(this);
        _tasks = getAll.getTasksByAvailable();

        Time currentTime = C_Time.get_Time();
        TaskAdapter adapter;
        if(currentTime!=null)
            {
            Toast.makeText(this, "Task id : " + currentTime.get_task() + " is running...", Toast.LENGTH_SHORT).show();
                adapter= new TaskAdapter(MainActivity.this,R.layout.task_row, _tasks,false,currentTime.get_task());
                setListener(currentTime.get_task());
            }
            else
            {
                adapter = new TaskAdapter(MainActivity.this,R.layout.task_row, _tasks,false,0);
                setListener(0);
            }

        _lvtasks.setAdapter(adapter);



    }

    private void setListener(final int taskId) {
        _lvtasks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TaskAdapter adapter = new TaskAdapter(MainActivity.this,R.layout.task_row, _tasks,false,taskId);
                Task t = adapter.getItem(position);

                Intent intent = new Intent(MainActivity.this, TaskViewActivity.class);
                intent.putExtra(Datas.MODE, Datas.VIEW);
                intent.putExtra(Datas.VIEW, t);
                MainActivity.this.startActivityForResult(intent,1);
            }
        });
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        Intent intent;
        int id = item.getItemId();

        if (id == R.id.nav_task) {
            intent = new Intent(MainActivity.this, TasksListActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_worker) {
            intent = new Intent(MainActivity.this, WorkersListActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_company) {
            intent = new Intent(MainActivity.this, CompaniesListActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_activity) {
            intent = new Intent(MainActivity.this, ActivitiesListActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_reporting) {
            intent = new Intent(MainActivity.this, ReportingActivity.class);
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
