package ch.hevs.androidproject644.js.workingtime;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import ch.hevs.androidproject644.js.workingtime.model.Activity;
import ch.hevs.androidproject644.js.workingtime.model.Company;
import ch.hevs.androidproject644.js.workingtime.model.Datas;

public class CompanyViewActivity extends AppCompatActivity {

    private TextView _tv_company_name_value;
    private TextView _tv_company_activated_value;

    private Company _company;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.company_view_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    protected void onResume(){
        super.onResume();

        findViewsById();

        Intent intent = getIntent();
        String sTypeOf = intent.getStringExtra(Datas.MODE);
        if (sTypeOf.equals(Datas.VIEW)) {
            _company = intent.getParcelableExtra(Datas.VIEW);

            _tv_company_name_value.setText(_company.get_name());
            if (_company.is_active() == true)
                _tv_company_activated_value.setText("Available");
            else
                _tv_company_activated_value.setText("Unavailable");
        }
    }

    private void findViewsById() {
        _tv_company_name_value = (TextView) findViewById(R.id.tv_company_name_value);
        _tv_company_activated_value = (TextView) findViewById(R.id.tv_company_activated_value);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.edit_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_refresh was selected
            case R.id.action_edit:
                Toast.makeText(this, "Editing company...", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(CompanyViewActivity.this, CompanyEditActivity.class);
                intent.putExtra(Datas.MODE, Datas.EDIT);
                intent.putExtra(Datas.EDIT, _company);
                CompanyViewActivity.this.startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
