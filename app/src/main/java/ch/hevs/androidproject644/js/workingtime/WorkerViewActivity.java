package ch.hevs.androidproject644.js.workingtime;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.VectorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import ch.hevs.androidproject644.js.workingtime.model.Datas;
import ch.hevs.androidproject644.js.workingtime.model.Worker;

public class WorkerViewActivity extends AppCompatActivity {

    private TextView _tv_firstname_lastname;
    private ImageView _image_Sex;
    private TextView _tv_Active;
    private TextView _tv_birthdate_value;

    private Worker _worker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.worker_view_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        _tv_firstname_lastname = (TextView) findViewById(R.id.tv_firstname_lastname);
        _tv_birthdate_value = (TextView) findViewById(R.id.tv_birthdate_value);
        _image_Sex = (ImageView) findViewById(R.id.image_Sex);
        _tv_Active = (TextView) findViewById(R.id.tv_active);


        Intent intent = getIntent();
        String sTypeOf = intent.getStringExtra(Datas.WORKER_MODE);
        if (sTypeOf.equals(Datas.WORKER_VIEW))
        {
            _worker = intent.getParcelableExtra(Datas.WORKER_VIEW);

            _tv_firstname_lastname.setText(_worker.get_firstname() + " " +  _worker.get_lastname());
            _tv_birthdate_value.setText(_worker.get_birthdate().toString());

            if(_worker.get_sex()=='m')
                _image_Sex.setImageDrawable(ActivityCompat.getDrawable(getBaseContext(), R.mipmap.ic_male));   //.setImageDrawable(new ColorDrawable(Color.CYAN));
            else
                _image_Sex.setImageDrawable(ActivityCompat.getDrawable(getBaseContext(), R.mipmap.ic_female));


            if(_worker.is_active()==true)
                _tv_Active.setText("Available");
            else
                _tv_Active.setText("Unavailable");
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
                Toast.makeText(this, "Editing worker...", Toast.LENGTH_SHORT)
                        .show();
                Intent intent = new Intent(WorkerViewActivity.this, WorkerEditActivity.class);
                intent.putExtra(Datas.WORKER_MODE, Datas.WORKER_EDIT);
                intent.putExtra(Datas.WORKER_EDIT, _worker);
                WorkerViewActivity.this.startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
