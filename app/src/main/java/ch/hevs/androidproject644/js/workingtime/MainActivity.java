package ch.hevs.androidproject644.js.workingtime;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;


import ch.hevs.androidproject644.js.workingtime.model.Datas;
import ch.hevs.androidproject644.js.workingtime.model.Task;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private Spinner _sp_chooseWorker;
    private Spinner _sp_chooseCompany;
    private Spinner _sp_chooseActivity;
    private Button _btn_newWorker;
    private Button _btn_newCompany;
    private Button _btn_newActivity;
    private Button _btn_ReadyToStart;
    private Button _btn_EndSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.main_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        findViewById();
        setListener();


        spinner_filler(_sp_chooseWorker.getId(),R.array.workers);
        spinner_filler(_sp_chooseCompany.getId(), R.array.companies);
        spinner_filler(_sp_chooseActivity.getId(), R.array.activities);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void setListener() {
        _btn_ReadyToStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO : Create Task and set id (When?)
                Task task = new Task();
                Intent intent = new Intent(MainActivity.this, TimeRecordingActivity.class);
                intent.putExtra(Datas.MODE, Datas.NEW);
                MainActivity.this.startActivityForResult(intent,1);

            }
        });
        _btn_EndSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void findViewById() {
        _sp_chooseWorker = (Spinner) findViewById(R.id.sp_chooseWorker);
        _sp_chooseCompany = (Spinner) findViewById(R.id.sp_chooseCompany);
        _sp_chooseActivity = (Spinner) findViewById(R.id.sp_chooseActivity);
        _btn_newWorker = (Button) findViewById(R.id.btn_newWorker);
        _btn_newCompany = (Button) findViewById(R.id.btn_newCompany);
        _btn_newActivity = (Button) findViewById(R.id.btn_newActivity);

        _btn_ReadyToStart = (Button) findViewById(R.id.btn_ReadyToStart);
        _btn_EndSave = (Button) findViewById(R.id.btn_EndSave);
    }

    public void spinner_filler(int spinner_name, int array_name)
    {
        Spinner spinner = (Spinner) findViewById(spinner_name);
        //Remplissage du spinner avec le string-array du strings.xml
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, array_name, android.R.layout.simple_spinner_item);
        //spécification du layout
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //application de l'adapter au spinner
        spinner.setAdapter(adapter);
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

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
