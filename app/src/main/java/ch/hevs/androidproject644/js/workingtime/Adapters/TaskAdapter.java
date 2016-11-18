package ch.hevs.androidproject644.js.workingtime.Adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ch.hevs.androidproject644.js.workingtime.MainActivity;
import ch.hevs.androidproject644.js.workingtime.R;
import ch.hevs.androidproject644.js.workingtime.model.Task;

/**
 * Created by Jacques on 26.10.2016.
 */

public class TaskAdapter extends ArrayAdapter<Task> {
    private Context _context;
    private List<Task> _tasks;

    public TaskAdapter(Context context,int ressource, List<Task> tasks) {
        super(context, ressource, tasks);
        this._context = context;
        this._tasks = tasks;
    }

    public View getCustomView(int position, View convertView, ViewGroup parent){
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.task_row,parent, false);
        }

        TaskViewHolder viewHolder = (TaskViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new TaskViewHolder();
            viewHolder.tv_date = (TextView) convertView.findViewById(R.id.tv_date);
            viewHolder.tv_duration = (TextView) convertView.findViewById(R.id.tv_duration);
            viewHolder.tv_company_value = (TextView) convertView.findViewById(R.id.tv_company_value);
            viewHolder.tv_activity_value = (TextView) convertView.findViewById(R.id.tv_activity_value);
            viewHolder.tv_worker_value = (TextView) convertView.findViewById(R.id.tv_worker_value);
            viewHolder.image_company = (ImageView) convertView.findViewById(R.id.image_company);
            viewHolder.image_activity = (ImageView) convertView.findViewById(R.id.image_activity);
            viewHolder.image_worker = (ImageView) convertView.findViewById(R.id.image_worker);
           // if(parent.getClass().getName().equals("MainActivity"))
            viewHolder.btn_start = (Button) convertView.findViewById(R.id.btn_start);

            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Tweet> tweets
        Task task = getItem(position);

        //il ne reste plus qu'à remplir notre vue
        viewHolder.tv_date.setText(task.get_date().toString());
        viewHolder.tv_duration.setText(task.get_duration_hhmm());
        viewHolder.image_company.setImageDrawable(_context.getResources().getDrawable(R.drawable.ic_business_black_24dp));
        viewHolder.image_activity.setImageDrawable(_context.getResources().getDrawable(R.drawable.ic_view_list_black_24dp));
        viewHolder.image_worker.setImageDrawable(_context.getResources().getDrawable(R.drawable.ic_person_black_24dp));
        viewHolder.tv_company_value.setText(task.get_company().get_name());
        viewHolder.tv_activity_value.setText(task.get_activity().get_name());
        viewHolder.tv_worker_value.setText(task.get_worker().get_firstname() + " " + task.get_worker().get_lastname());
        viewHolder.btn_start.setText(R.string.btn_start);

        return convertView;
    }

    private class TaskViewHolder{
        private  TextView tv_date;
        private TextView tv_duration;
        private ImageView image_company;
        private ImageView image_activity;
        private ImageView image_worker;
        private TextView tv_company_value;
        private TextView tv_activity_value;
        private TextView tv_worker_value;
        private Button btn_start;

    }
}
