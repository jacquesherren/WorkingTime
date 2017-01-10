package ch.hevs.androidproject644.js.workingtime;


import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.List;

import ch.hevs.androidproject644.js.workingtime.Adapters.TimeAdapter;
import ch.hevs.androidproject644.js.workingtime.Controler.C_Task;
import ch.hevs.androidproject644.js.workingtime.Controler.C_Time;
import ch.hevs.androidproject644.js.workingtime.DB.TaskDataSource;
import ch.hevs.androidproject644.js.workingtime.DB.TimeDataSource;
import ch.hevs.androidproject644.js.workingtime.model.Datas;
import ch.hevs.androidproject644.js.workingtime.model.Task;
import ch.hevs.androidproject644.js.workingtime.model.Time;

/**
 * Created by Jacques on 25.11.2016.
 */

public class TaskFragmentView extends Fragment {

    private TextView _tv_firstname_lastname;
    private ImageView _image_Sex;
    private TextView _tv_birthdate_value;
    private TextView _tv_activity_value;
    private TextView _tv_company_value;
    private Switch _sw_task_archive;

    private View _view;

    private Task _task;


    public static TaskFragmentView newInstance(int page, String title) {
        TaskFragmentView ftv = new TaskFragmentView();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        ftv.setArguments(args);
        return ftv;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this._view =  inflater.inflate(R.layout.task_fragment_taskview, container, false);

        return _view;
    }

    @Override
    public void onResume() {
        super.onResume();
        findViewById(_view);

        final TaskActivity taskActivity = (TaskActivity) getActivity();

        _task = taskActivity.get_task();

        _tv_firstname_lastname.setText(_task.get_worker().get_firstname() + " " +  _task.get_worker().get_lastname());
        _tv_birthdate_value.setText(Datas.formatDate().format(_task.get_worker().get_birthdate().getTime()));

        if(_task.get_worker().get_sex()=='m')
            _image_Sex.setImageDrawable(ActivityCompat.getDrawable(_view.getContext(), R.mipmap.ic_male));   //.setImageDrawable(new ColorDrawable(Color.CYAN));
        else
            _image_Sex.setImageDrawable(ActivityCompat.getDrawable(_view.getContext(), R.mipmap.ic_female));

        _tv_activity_value.setText(_task.get_activity().get_name());
        _tv_company_value.setText(_task.get_company().get_name());
        _sw_task_archive.setChecked(_task.is_archive());

        _sw_task_archive.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(_sw_task_archive.isChecked())
                    _task.set_archive(true);
                else
                    _task.set_archive(false);

                TaskDataSource updateTask = new TaskDataSource(taskActivity.getBaseContext());
                updateTask.updateTaskArchive(_task);
                Log.e("state saved", "state saved");

            }
        });


    }

    private void findViewById(View view) {
        _tv_firstname_lastname = (TextView) view.findViewById(R.id.tv_firstname_lastname);
        _tv_birthdate_value = (TextView) view.findViewById(R.id.tv_birthdate_value);
        _image_Sex = (ImageView) view.findViewById(R.id.image_Sex);
        _sw_task_archive = (Switch) view.findViewById(R.id.sw_task_archive);
        _tv_activity_value = (TextView) view.findViewById(R.id.tv_activity_value);
        _tv_company_value = (TextView) view.findViewById(R.id.tv_company_value);
    }
}
