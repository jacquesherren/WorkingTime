package ch.hevs.androidproject644.js.workingtime;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import ch.hevs.androidproject644.js.workingtime.Adapters.CompanyAdapter;
import ch.hevs.androidproject644.js.workingtime.DB.CompanyDataSource;
import ch.hevs.androidproject644.js.workingtime.DB.TimeDataSource;
import ch.hevs.androidproject644.js.workingtime.model.Company;

/**
 * Created by Jacques on 26.11.2016.
 */

public class ReportingList  extends Fragment {


    public static ReportingList newInstance(int page, String title) {
        ReportingList reportingList = new ReportingList();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        reportingList.setArguments(args);
        return reportingList;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.reporting_list, container, false);

        ReportingActivity reportingActivity = (ReportingActivity) getActivity();

        CompanyDataSource getTimeByCompany = new CompanyDataSource(reportingActivity);
        List<Company> companies = getTimeByCompany.getSumTimeByCompany();

        ListView listView = (ListView) view.findViewById(R.id.lv_reporting);
        CompanyAdapter adpter = new CompanyAdapter(view.getContext(),R.layout.company_row,companies);
        listView.setAdapter(adpter);



        //_times = taskActivity.get_times();
        //ListView lv_Time = (ListView) view.findViewById(R.id.lv_times);

        //TimeAdapter adapter = new TimeAdapter(view.getContext(),R.layout.time_row,_times);
        //lv_Time.setAdapter(adapter);



        return view;
    }

    @Override
    public void onResume(){
        super.onResume();




    }
}
