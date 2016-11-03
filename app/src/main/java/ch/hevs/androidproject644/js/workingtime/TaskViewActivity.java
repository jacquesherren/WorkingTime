package ch.hevs.androidproject644.js.workingtime;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import ch.hevs.androidproject644.js.workingtime.model.Datas;
import ch.hevs.androidproject644.js.workingtime.model.Task;
import ch.hevs.androidproject644.js.workingtime.model.Worker;

public class TaskViewActivity extends AppCompatActivity {

    private  TextView _tv_date;
    private TextView _tv_duration;

    private TextView _tv_firstname_lastname;
    private ImageView _image_Sex;
    private TextView _tv_birthdate_value;

    private TextView _tv_activity_value;
    private ImageView _image_activity;

    private TextView _tv_company_value;
    private ImageView _image_company;

    private Task _task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_view_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        _tv_date = (TextView) findViewById(R.id.tv_date);
        _tv_duration = (TextView) findViewById(R.id.tv_duration);

        _tv_firstname_lastname = (TextView) findViewById(R.id.tv_firstname_lastname);
        _tv_birthdate_value = (TextView) findViewById(R.id.tv_birthdate_value);
        _image_Sex = (ImageView) findViewById(R.id.image_Sex);

        _tv_activity_value = (TextView) findViewById(R.id.tv_activity_value);
        _image_activity = (ImageView) findViewById(R.id.image_activity);

        _tv_company_value = (TextView) findViewById(R.id.tv_company_value);
        _image_company = (ImageView) findViewById(R.id.image_company);

        Intent intent = getIntent();
        String sTypeOf = intent.getStringExtra(Datas.MODE);
        if (sTypeOf.equals(Datas.VIEW))
        {
            _task = intent.getParcelableExtra(Datas.VIEW);

            _tv_firstname_lastname.setText(_task.get_worker().get_firstname() + " " +  _task.get_worker().get_lastname());
            _tv_birthdate_value.setText(_task.get_worker().get_birthdate().toString());

            if(_task.get_worker().get_sex()=='m')
                _image_Sex.setImageDrawable(ActivityCompat.getDrawable(getBaseContext(), R.mipmap.ic_male));   //.setImageDrawable(new ColorDrawable(Color.CYAN));
            else
                _image_Sex.setImageDrawable(ActivityCompat.getDrawable(getBaseContext(), R.mipmap.ic_female));

            _image_company.setImageDrawable(ActivityCompat.getDrawable(getBaseContext(), R.drawable.ic_business_black_24dp));
            _tv_activity_value.setText(_task.get_activity().get_name());

            _image_activity.setImageDrawable(ActivityCompat.getDrawable(getBaseContext(), R.drawable.ic_view_list_black_24dp));
            _tv_company_value.setText(_task.get_company().get_name());

            _tv_duration.setText(_task.get_duration_hhmm());
            _tv_date.setText(_task.get_date().toString());

        }

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
                Toast.makeText(this, "Editing task...", Toast.LENGTH_SHORT)
                        .show();
                /*Intent intent = new Intent(TaskViewActivity.this, TaskEditActivity.class);
                intent.putExtra(Datas.MODE, Datas.EDIT);
                intent.putExtra(Datas.EDIT, _task);
                TaskViewActivity.this.startActivityForResult(intent,1);*/
                break;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

       /* if (requestCode == 1) {
            if(resultCode == TaskEditActivity.RESULT_OK){
                Task result=data.getParcelableExtra("result");
            }
            if (resultCode == TaskEditActivity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }*/
    }
}
