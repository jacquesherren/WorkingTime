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

import ch.hevs.androidproject644.js.workingtime.Adapters.CompanyAdapter;
import ch.hevs.androidproject644.js.workingtime.DB.CompanyDataSource;
import ch.hevs.androidproject644.js.workingtime.model.Company;
import ch.hevs.androidproject644.js.workingtime.model.Datas;

public class CompaniesListActivity extends AppCompatActivity {

    private ListView _lvCompanies;
    List<Company> _companies = new ArrayList<Company>();
    Resources _res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.companies_list_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        _lvCompanies = (ListView) findViewById(R.id.lv_companies);
        CompanyDataSource getAll = new CompanyDataSource(this);
        _companies = getAll.getAllCompanies();

        CompanyAdapter adapter = new CompanyAdapter(CompaniesListActivity.this, _companies,_res);
        _lvCompanies.setAdapter(adapter);

        _lvCompanies.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CompanyAdapter adapter = new CompanyAdapter(CompaniesListActivity.this, _companies,_res);
                Company c = adapter.getItem(position);

                Intent intent = new Intent(CompaniesListActivity.this, CompanyViewActivity.class);
                intent.putExtra(Datas.MODE, Datas.VIEW);
                intent.putExtra(Datas.VIEW, c);
                CompaniesListActivity.this.startActivityForResult(intent,1);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CompaniesListActivity.this, CompanyEditActivity.class);
                intent.putExtra(Datas.MODE, Datas.NEW);
                CompaniesListActivity.this.startActivityForResult(intent,1);
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
            if(resultCode == CompanyEditActivity.RESULT_OK){
                Company result=data.getParcelableExtra("result");
            }
            if (resultCode == CompanyEditActivity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }//onActivityResult

}
