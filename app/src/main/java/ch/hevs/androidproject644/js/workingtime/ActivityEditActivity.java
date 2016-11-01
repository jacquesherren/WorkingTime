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
import android.widget.Toast;

import ch.hevs.androidproject644.js.workingtime.model.Activity;
import ch.hevs.androidproject644.js.workingtime.model.Datas;

public class ActivityEditActivity extends AppCompatActivity {

    private EditText _et_activity_name_value;
    private Switch _sw_activity_activated;

    private Activity _activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        _et_activity_name_value = (EditText) findViewById(R.id.et_activity_name_value);
        _sw_activity_activated = (Switch) findViewById(R.id.sw_activity_activated);

        Intent intent = getIntent();
        String sTypeOf = intent.getStringExtra(Datas.MODE);
        if (sTypeOf.equals(Datas.EDIT)) {
            _activity = intent.getParcelableExtra(Datas.EDIT);

            _et_activity_name_value.setText(_activity.get_name());
            if (_activity.is_active() == true)
                _sw_activity_activated.setChecked(true);
            else
                _sw_activity_activated.setChecked(false);
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
                    setResult(ActivityEditActivity.RESULT_CANCELED, returnIntent);
                    finish();
                    break;

                case R.id.action_delete:
                    delete();
                    break;
            }
            return super.onOptionsItemSelected(item);
        }

    private void save(){
        if(_activity!=null){
            Toast.makeText(this, "Saving this activity...", Toast.LENGTH_SHORT)
                    .show();
            Intent returnIntent = new Intent();
            returnIntent.putExtra("result",_activity);
            setResult(ActivityEditActivity.RESULT_OK,returnIntent);
            finish();
        }
        else {
            Toast.makeText(this, "Creating a new activity...", Toast.LENGTH_SHORT)
                    .show();
        }
    }
    private  void delete(){
        if(_activity!=null){
            Toast.makeText(this, "Deleting this activity...", Toast.LENGTH_SHORT)
                    .show();
        }
        else {
            Toast.makeText(this, "Nothing to delete...", Toast.LENGTH_SHORT)
                    .show();
        }
    }

}
