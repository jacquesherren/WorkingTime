package ch.hevs.androidproject644.js.workingtime;

import android.app.DatePickerDialog;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

import ch.hevs.androidproject644.js.workingtime.Adapters.CompanyAdapter;
import ch.hevs.androidproject644.js.workingtime.Controler.C_Time;
import ch.hevs.androidproject644.js.workingtime.DB.CompanyDataSource;
import ch.hevs.androidproject644.js.workingtime.model.Company;
import ch.hevs.androidproject644.js.workingtime.model.Datas;

/**
 * Created by Jacques on 26.11.2016.
 */

public class ReportingByCompny extends Fragment {

   private EditText _etFromDate;
    private EditText _etTodate;

    private Calendar _fromDate;
    private Calendar _toDate;
    private DatePickerDialog _DatePickerDialog_From;
    private DatePickerDialog _DatePickerDialog_To;

    private Button _btn_submit;

    public ReportingByCompny() {
    }

    public static ReportingByCompny newInstance(int page, String title) {
        ReportingByCompny reportingList = new ReportingByCompny();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        reportingList.setArguments(args);
        return reportingList;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.reporting_bycompany, container, false);

        final ReportingActivity reportingActivity = (ReportingActivity) getActivity();

       _etFromDate = (EditText) view.findViewById(R.id.et_date_from);
        _etFromDate.setInputType(InputType.TYPE_CLASS_NUMBER);

        _etTodate = (EditText) view.findViewById(R.id.et_date_to);
        _etTodate.setInputType(InputType.TYPE_CLASS_NUMBER);

        _btn_submit = (Button) view.findViewById(R.id.btn_submit);
        _btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if((_fromDate!=null) && (_toDate!=null)){
                    Toast.makeText(view.getContext(), "From : " + Datas.formatDate().format(_fromDate.getTimeInMillis()) +" To : " + Datas.formatDate().format(_toDate.getTimeInMillis()), Toast.LENGTH_SHORT).show();
                    CompanyDataSource getTimeByCompany = new CompanyDataSource(reportingActivity);
                    List<Company> companies = getTimeByCompany.getSumTimeByCompanybetweenDate(_fromDate.getTimeInMillis(),_toDate.getTimeInMillis());

                    ListView listView = (ListView) view.findViewById(R.id.lv_reporting);
                    CompanyAdapter adpter = new CompanyAdapter(view.getContext(),R.layout.company_row,companies);
                    listView.setAdapter(adpter);
                }
                else {
                    // TODO Dialog select date befor submit
                }

            }
        });

        setDateTimeField(view);

        return view;
    }

    @Override
    public void onResume(){
        super.onResume();


    }

   private void setDateTimeField(View view) {
        _etFromDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _DatePickerDialog_From.show();
            }
        });

        _etTodate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _DatePickerDialog_To.show();
            }
        });

        Calendar newCalendar = Calendar.getInstance();
        _DatePickerDialog_From = new DatePickerDialog(view.getContext(),new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                //Calendar newDate = Calendar.getInstance();
                _fromDate = Calendar.getInstance();
                _fromDate.set(year, monthOfYear, dayOfMonth);
                _etFromDate.setText(Datas.formatDate().format(_fromDate.getTime()));
            }
        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        _DatePickerDialog_To = new DatePickerDialog(view.getContext(),new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                //Calendar newDate = Calendar.getInstance();
                _toDate = Calendar.getInstance();
                _toDate.set(year, monthOfYear, dayOfMonth);
                _etTodate.setText(Datas.formatDate().format(_toDate.getTime()));
            }
        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }

    public void onClick(View view) {
        if(view == _etFromDate) {
            _DatePickerDialog_From.show();
        }
        if(view == _etTodate) {
            _DatePickerDialog_To.show();
        }
    }
}
