package ch.hevs.androidproject644.js.workingtime;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import ch.hevs.androidproject644.js.workingtime.Controler.C_Worker;
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

        findViewsById();

        Intent intent = getIntent();
        String sTypeOf = intent.getStringExtra(Datas.MODE);
        if (sTypeOf.equals(Datas.VIEW))
        {
            _worker = intent.getParcelableExtra(Datas.VIEW);

            _tv_firstname_lastname.setText(_worker.get_firstname() + " " +  _worker.get_lastname());
            _tv_birthdate_value.setText(Datas.DATE_FORMATTER.format(_worker.get_birthdate().getTime()));
            _image_Sex.setImageDrawable(C_Worker.get_ImageSex(this,_worker.get_sex()));

            if(_worker.is_active()==true)
                _tv_Active.setText("Available");
            else
                _tv_Active.setText("Unavailable");
        }
    }

    private void findViewsById(){
        _tv_firstname_lastname = (TextView) findViewById(R.id.tv_firstname_lastname);
        _tv_birthdate_value = (TextView) findViewById(R.id.tv_birthdate_value);
        _image_Sex = (ImageView) findViewById(R.id.image_Sex);
        _tv_Active = (TextView) findViewById(R.id.tv_active);
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
                intent.putExtra(Datas.MODE, Datas.EDIT);
                intent.putExtra(Datas.EDIT, _worker);
                WorkerViewActivity.this.startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
