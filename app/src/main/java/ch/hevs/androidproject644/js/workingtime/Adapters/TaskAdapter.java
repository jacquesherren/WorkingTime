package ch.hevs.androidproject644.js.workingtime.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import ch.hevs.androidproject644.js.workingtime.R;
import ch.hevs.androidproject644.js.workingtime.model.Task;

/**
 * Created by Jacques on 26.10.2016.
 */

public class TaskAdapter extends ArrayAdapter<Task> {


    public TaskAdapter(Context context, List<Task> tasks) {
        super(context, 0, tasks);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.task_row,parent, false);
        }

        TaskViewHolder viewHolder = (TaskViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new TaskViewHolder();
            viewHolder.date = (TextView) convertView.findViewById(R.id.tv_date);
            viewHolder.duration = (TextView) convertView.findViewById(R.id.tv_duration);
            viewHolder.company_value = (TextView) convertView.findViewById(R.id.tv_company_value);
            viewHolder.activity_value = (TextView) convertView.findViewById(R.id.tv_activity_value);
            viewHolder.worker_value = (TextView) convertView.findViewById(R.id.tv_worker_value);
            viewHolder.company_name = (TextView) convertView.findViewById(R.id.tv_company_name);
            viewHolder.activity_name = (TextView) convertView.findViewById(R.id.tv_activity_name);
            viewHolder.worker_name = (TextView) convertView.findViewById(R.id.tv_worker_name);

            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Tweet> tweets
        Task task = getItem(position);

        //il ne reste plus qu'à remplir notre vue
        viewHolder.date.setText(task.get_date().toString());
        viewHolder.duration.setText(task.get_duration_hhmm());
        viewHolder.company_name.setText(R.string.company_label);
        viewHolder.activity_name.setText(R.string.activity_label);
        viewHolder.worker_name.setText(R.string.worker_label);
        viewHolder.company_value.setText(task.get_company().get_name());
        viewHolder.activity_value.setText(task.get_activity().get_name());
        viewHolder.worker_value.setText(task.get_worker().get_firstname() + " " + task.get_worker().get_lastname());

        return convertView;
    }

    private class TaskViewHolder{
        private  TextView date;
        private TextView duration;
        private TextView company_name;
        private TextView activity_name;
        private TextView worker_name;
        private TextView company_value;
        private TextView activity_value;
        private TextView worker_value;

    }
}
