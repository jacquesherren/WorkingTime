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
import ch.hevs.androidproject644.js.workingtime.model.Datas;


public class ActivityViewActivity extends AppCompatActivity {

    private TextView _tv_activity_name_value;
    private TextView _tv_activity_activated_value;

    private Activity _activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        _tv_activity_name_value = (TextView) findViewById(R.id.tv_activity_name_value);
        _tv_activity_activated_value = (TextView) findViewById(R.id.tv_activity_activated_value);

        Intent intent = getIntent();
        String sTypeOf = intent.getStringExtra(Datas.MODE);
        if (sTypeOf.equals(Datas.VIEW)) {
            _activity = intent.getParcelableExtra(Datas.VIEW);

            _tv_activity_name_value.setText(_activity.get_name());
            if (_activity.is_active() == true)
                _tv_activity_activated_value.setText("Available");
            else
                _tv_activity_activated_value.setText("Unavailable");
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
                    Toast.makeText(this, "Editing activity...", Toast.LENGTH_SHORT)
                            .show();
                    Intent intent = new Intent(ActivityViewActivity.this, ActivityEditActivity.class);
                    intent.putExtra(Datas.MODE, Datas.EDIT);
                    intent.putExtra(Datas.EDIT, _activity);
                    ActivityViewActivity.this.startActivityForResult(intent,1);
                    break;
            }

            return super.onOptionsItemSelected(item);
        }
        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {

            if (requestCode == 1) {
                if(resultCode == ActivityEditActivity.RESULT_OK){
                    Activity result=data.getParcelableExtra("result");
                }
                if (resultCode == ActivityEditActivity.RESULT_CANCELED) {
                    //Write your code if there's no result
                }
            }
        }


}
