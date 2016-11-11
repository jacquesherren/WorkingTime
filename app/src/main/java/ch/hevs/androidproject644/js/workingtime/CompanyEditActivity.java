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
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import ch.hevs.androidproject644.js.workingtime.Controler.C_Company;
import ch.hevs.androidproject644.js.workingtime.DB.CompanyDataSource;
import ch.hevs.androidproject644.js.workingtime.DB.WorkerDataSource;
import ch.hevs.androidproject644.js.workingtime.model.Company;
import ch.hevs.androidproject644.js.workingtime.model.Datas;

public class CompanyEditActivity extends AppCompatActivity {
    private EditText _et_company_name_value;
    private Switch _sw_company_activated;

    private Company _company;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.company_edit_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        findViewById();

        Intent intent = getIntent();
        String sTypeOf = intent.getStringExtra(Datas.MODE);
        if (sTypeOf.equals(Datas.EDIT)) {
            _company = intent.getParcelableExtra(Datas.EDIT);

            _et_company_name_value.setText(_company.get_name());
            _sw_company_activated.setChecked(_company.is_active());
        }
    }
    private void findViewById() {
        _et_company_name_value = (EditText) findViewById(R.id.et_company_name_value);
        _sw_company_activated = (Switch) findViewById(R.id.sw_company_activated);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.save_cancel_delete_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_refresh was selected
            case R.id.action_save:
                save();
                break;

            // action with ID action_settings was selected
            case R.id.action_cancel:
                Intent returnIntent = new Intent();
                setResult(CompanyEditActivity.RESULT_CANCELED, returnIntent);
                finish();
                break;

            case R.id.action_delete:
                delete();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void save(){
        if(_company!=null){
            Toast.makeText(this, "Saving this company...", Toast.LENGTH_SHORT)
                    .show();

            setCompany();

            CompanyDataSource updateCompany = new CompanyDataSource(this);
            updateCompany.updateCompany(_company);

        }
        else {
            Toast.makeText(this, "Creating a new company...", Toast.LENGTH_SHORT)
                    .show();

            _company = new Company();
            setCompany();

            CompanyDataSource addCompany = new CompanyDataSource(this);
            addCompany.createCompany(_company);
        }
        Intent returnIntent = new Intent();
        returnIntent.putExtra("result",_company);
        setResult(CompanyEditActivity.RESULT_OK,returnIntent);
        finish();
    }


    private  void delete(){
        if(_company!=null){
            Toast.makeText(this, "Deleting this company...", Toast.LENGTH_SHORT)
                    .show();
            CompanyDataSource deleteCompany = new CompanyDataSource(this);
            deleteCompany.deleteCompany(_company);
        }
        else {
            Toast.makeText(this, "Nothing to delete...", Toast.LENGTH_SHORT)
                    .show();
        }
    }

    private void setCompany() {
        _company.set_name(_et_company_name_value.getText().toString());
        _company.set_active(C_Company.controlSwitch(_sw_company_activated));
    }

}

