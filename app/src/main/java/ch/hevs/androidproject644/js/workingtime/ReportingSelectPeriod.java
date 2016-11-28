package ch.hevs.androidproject644.js.workingtime;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

import ch.hevs.androidproject644.js.workingtime.model.Datas;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReportingSelectPeriod extends Fragment {

    public ReportingSelectPeriod() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.reporting_select_period, container, false);



        return view;
    }

}
