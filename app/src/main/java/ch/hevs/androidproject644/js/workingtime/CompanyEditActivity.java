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

        _et_company_name_value = (EditText) findViewById(R.id.et_company_name_value);
        _sw_company_activated = (Switch) findViewById(R.id.sw_company_activated);

        Intent intent = getIntent();
        String sTypeOf = intent.getStringExtra(Datas.MODE);
        if (sTypeOf.equals(Datas.EDIT)) {
            _company = intent.getParcelableExtra(Datas.EDIT);

            _et_company_name_value.setText(_company.get_name());
            if (_company.is_active() == true)
                _sw_company_activated.setChecked(true);
            else
                _sw_company_activated.setChecked(false);
        }
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
            Intent returnIntent = new Intent();
            returnIntent.putExtra("result",_company);
            setResult(CompanyEditActivity.RESULT_OK,returnIntent);
            finish();
        }
        else {
            Toast.makeText(this, "Creating a new company...", Toast.LENGTH_SHORT)
                    .show();
        }
    }
    private  void delete(){
        if(_company!=null){
            Toast.makeText(this, "Deleting this company...", Toast.LENGTH_SHORT)
                    .show();
        }
        else {
            Toast.makeText(this, "Nothing to delete...", Toast.LENGTH_SHORT)
                    .show();
        }
    }

}

