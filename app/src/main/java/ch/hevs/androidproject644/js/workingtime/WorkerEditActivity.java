package ch.hevs.androidproject644.js.workingtime;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.app.DatePickerDialog.OnDateSetListener;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.Toast;

import java.util.Calendar;


import ch.hevs.androidproject644.js.workingtime.Controler.C_Worker;
import ch.hevs.androidproject644.js.workingtime.DB.WorkerDataSource;
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

    private DatePickerDialog _DatePickerDialog_Birthdate;
    private Calendar _birthdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.worker_edit_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        findViewsById();
        setDateTimeField();

        Intent intent = getIntent();
        String sTypeOf = intent.getStringExtra(Datas.MODE);
        if (sTypeOf.equals(Datas.EDIT)) {
            _worker = intent.getParcelableExtra(Datas.EDIT);

            _etFirstname.setText(_worker.get_firstname());
            _etLastname.setText(_worker.get_lastname());
            _etBirthdate.setText(Datas.DATE_FORMATTER.format(_worker.get_birthdate().getTime()));
            if (_worker.get_sex() == 'm') {
                _rbSexM.setChecked(true);
                _rbSexF.setChecked(false);
            } else {
                _rbSexM.setChecked(false);
                _rbSexF.setChecked(true);
            }
            _swActive.setChecked(_worker.is_active());
        }
    }

    private void findViewsById(){
        _etFirstname = (EditText) findViewById(R.id.et_firstname);
        _etLastname = (EditText) findViewById(R.id.et_lastname);
        _etBirthdate = (EditText) findViewById(R.id.et_Birthdate);
        _etBirthdate.setInputType(InputType.TYPE_CLASS_NUMBER);

        _rbSexF = (RadioButton) findViewById(R.id.rb_female);
        _rbSexM = (RadioButton) findViewById(R.id.rb_male);
        _swActive = (Switch) findViewById(R.id.sw_worker_activated);
    }

    private void setDateTimeField() {
        _etBirthdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _DatePickerDialog_Birthdate.show();
            }
        });

        Calendar newCalendar = Calendar.getInstance();
        _DatePickerDialog_Birthdate = new DatePickerDialog(this,new OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                //Calendar newDate = Calendar.getInstance();
                _birthdate = Calendar.getInstance();
                _birthdate.set(year, monthOfYear, dayOfMonth);
                _etBirthdate.setText(Datas.DATE_FORMATTER.format(_birthdate.getTime()));
            }
        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }

    public void onClick(View view) {
        if(view == _etBirthdate) {
            _DatePickerDialog_Birthdate.show();
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
                setResult(WorkerEditActivity.RESULT_CANCELED, returnIntent);
                finish();
                break;

            case R.id.action_delete:
                delete();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void save(){
        if(_worker!=null){
            Toast.makeText(this, "Saving this worker...", Toast.LENGTH_SHORT)
                    .show();


            Intent returnIntent = new Intent();
            returnIntent.putExtra("result",_worker);
            setResult(WorkerEditActivity.RESULT_OK,returnIntent);
            finish();

            //String [] workers = getResources().getStringArray(R.array.)

        }
        else {
            Toast.makeText(this, "Creating a new worker...", Toast.LENGTH_SHORT)
                    .show();

            Worker worker = new Worker();
            worker.set_lastname(_etLastname.getText().toString());
            worker.set_firstname(_etFirstname.getText().toString());
            worker.set_sex(C_Worker.controlSex(_rbSexM, _rbSexF));
            worker.set_active(C_Worker.controlSwitch(_swActive));
            worker.set_birthdate(_birthdate);

            WorkerDataSource addWorker = new WorkerDataSource(this);
            addWorker.createWorker(worker);
        }
    }
    private  void delete(){
        if(_worker!=null){
            Toast.makeText(this, "Deleting this worker...", Toast.LENGTH_SHORT)
                    .show();
        }
        else {
            Toast.makeText(this, "Nothing to delete...", Toast.LENGTH_SHORT)
                    .show();
        }
    }
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.rb_male:
                if (checked)
                    // Pirates are the best
                    break;
            case R.id.rb_female:
                if (checked)
                    // Ninjas rule
                    break;
        }
    }
}
