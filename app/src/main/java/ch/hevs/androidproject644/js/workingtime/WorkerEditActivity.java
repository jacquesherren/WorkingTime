package ch.hevs.androidproject644.js.workingtime;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.Toast;

import ch.hevs.androidproject644.js.workingtime.model.Datas;
import ch.hevs.androidproject644.js.workingtime.model.Worker;

public class WorkerEditActivity extends AppCompatActivity {

    private EditText _etFirstname;
    private EditText _etLastname;
    private RadioButton _rbSexF;
    private RadioButton _rbSexM;
    private Switch _swActive;
    private EditText _etBirthdate;

    private Worker _worker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.worker_edit_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        _etFirstname = (EditText) findViewById(R.id.et_firstname);
        _etLastname = (EditText) findViewById(R.id.et_lastname);
        _etBirthdate = (EditText) findViewById(R.id.et_Birthdate);
        _rbSexF = (RadioButton) findViewById(R.id.rb_female);
        _rbSexM = (RadioButton) findViewById(R.id.rb_male);
        _swActive = (Switch) findViewById(R.id.sw_worker_activated);

        Intent intent = getIntent();
        String sTypeOf = intent.getStringExtra(Datas.WORKER_MODE);
        if (sTypeOf.equals(Datas.WORKER_EDIT)) {
            _worker = intent.getParcelableExtra(Datas.WORKER_EDIT);

            _etFirstname.setText(_worker.get_firstname());
            _etLastname.setText(_worker.get_lastname());
            _etBirthdate.setText(_worker.get_birthdate().toString());
            if (_worker.get_sex() == 'm') {
                _rbSexM.setActivated(true);
                _rbSexF.setActivated(false);
            } else {
                _rbSexM.setActivated(false);
                _rbSexF.setActivated(true);
            }
            _swActive.setActivated(_worker.is_active());
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
                saveWorker();
                break;

            // action with ID action_settings was selected
            case R.id.action_cancel:
                break;

            case R.id.action_delete:
                deleteWorker();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveWorker(){
        if(_worker!=null){
            Toast.makeText(this, "Saving this worker...", Toast.LENGTH_SHORT)
                    .show();
        }
        else {
            Toast.makeText(this, "Creating a new worker...", Toast.LENGTH_SHORT)
                    .show();
        }
    }
    private  void deleteWorker(){
        if(_worker!=null){
            Toast.makeText(this, "Deleting this worker...", Toast.LENGTH_SHORT)
                    .show();
        }
        else {
            Toast.makeText(this, "Nothing to delete...", Toast.LENGTH_SHORT)
                    .show();
        }
    }
}
