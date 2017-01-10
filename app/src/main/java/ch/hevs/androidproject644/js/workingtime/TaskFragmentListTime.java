package ch.hevs.androidproject644.js.workingtime;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import ch.hevs.androidproject644.js.workingtime.Adapters.TimeAdapter;
import ch.hevs.androidproject644.js.workingtime.DB.TimeDataSource;
import ch.hevs.androidproject644.js.workingtime.model.Task;
import ch.hevs.androidproject644.js.workingtime.model.Time;

/**
 * Created by Jacques on 25.11.2016.
 */

public class TaskFragmentListTime extends Fragment {
    private List<Time> _times;

    public static TaskFragmentListTime newInstance(int page, String title) {
        TaskFragmentListTime ftlt = new TaskFragmentListTime();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        ftlt.setArguments(args);
        return ftlt;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.task_fragment_timelist, container, false);

        TaskActivity taskActivity = (TaskActivity) getActivity();
        _times = taskActivity.get_times();
        ListView lv_Time = (ListView) view.findViewById(R.id.lv_times);

        TimeAdapter adapter = new TimeAdapter(view.getContext(),R.layout.time_row,_times);
        lv_Time.setAdapter(adapter);



        return view;
    }
}
