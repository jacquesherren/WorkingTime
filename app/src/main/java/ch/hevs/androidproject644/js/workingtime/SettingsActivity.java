package ch.hevs.androidproject644.js.workingtime;

import android.media.audiofx.EnvironmentalReverb;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import ch.hevs.androidproject644.js.workingtime.Adapters.CompanyAdapter;
import ch.hevs.androidproject644.js.workingtime.model.Datas;

public class SettingsActivity extends AppCompatActivity {

    private Spinner _sp_settings_dateformat;
    private Button _bt_settings_save_format;
    private TextView et_company_name_value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Spinner spinner = (Spinner) findViewById(R.id.sp_setting_date);
        Button button = (Button) findViewById(R.id.btn_save_format);
        et_company_name_value = (TextView) findViewById(R.id.et_company_name_value);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.dateFormat, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        String dateFormat = spinner.getSelectedItem().toString();


        final ImageButton flag_en = (ImageButton) findViewById(R.id.flag_en);
        flag_en.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LanguageLocalHelper.setLocale(SettingsActivity.this, "en");
                updateText();
            }
        });

        final ImageButton flag_fr = (ImageButton) findViewById(R.id.flag_fr);
        flag_fr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LanguageLocalHelper.setLocale(SettingsActivity.this, "fr");
                updateText();
            }
        });
    }
   public void updateText()
    {
        et_company_name_value.setText("salut");
    }

}
