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

import java.util.ArrayList;
import java.util.List;

import ch.hevs.androidproject644.js.workingtime.Adapters.CompanyAdapter;
import ch.hevs.androidproject644.js.workingtime.Adapters.TaskAdapter;
import ch.hevs.androidproject644.js.workingtime.DB.CompanyDataSource;
import ch.hevs.androidproject644.js.workingtime.DB.TaskDataSource;
import ch.hevs.androidproject644.js.workingtime.model.Company;
import ch.hevs.androidproject644.js.workingtime.model.Datas;
import ch.hevs.androidproject644.js.workingtime.model.Task;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private ListView _lvtasks;
    private List<Task> _tasks = new ArrayList<Task>();
    Resources _res;

    private ListView _lvCompanies;
    List<Company> _companies = new ArrayList<Company>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        _lvCompanies = (ListView) findViewById(R.id.lv_companies);
        CompanyDataSource getAll = new CompanyDataSource(this);
        _companies = getAll.getAllCompanies();

        CompanyAdapter adapter = new CompanyAdapter(MainActivity.this, R.layout.company_row,_companies);
        _lvCompanies.setAdapter(adapter);

        _lvCompanies.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CompanyAdapter adapter = new CompanyAdapter(MainActivity.this,R.layout.company_row, _companies);
                Company c = adapter.getItem(position);

                Intent intent = new Intent(MainActivity.this, CompanyViewActivity.class);
                intent.putExtra(Datas.MODE, Datas.VIEW);
                intent.putExtra(Datas.VIEW, c);
                MainActivity.this.startActivityForResult(intent,1);
            }
        });

        setListener();

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




    private void setListener(){

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
