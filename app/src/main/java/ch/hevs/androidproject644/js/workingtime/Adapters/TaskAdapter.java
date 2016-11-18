package ch.hevs.androidproject644.js.workingtime.Adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ch.hevs.androidproject644.js.workingtime.DB.TimeDataSource;
import ch.hevs.androidproject644.js.workingtime.MainActivity;
import ch.hevs.androidproject644.js.workingtime.R;
import ch.hevs.androidproject644.js.workingtime.model.Datas;
import ch.hevs.androidproject644.js.workingtime.model.Task;
import ch.hevs.androidproject644.js.workingtime.model.Time;

/**
 * Created by Jacques on 26.10.2016.
 */

public class TaskAdapter extends ArrayAdapter<Task> {
    private Context _context;
    private List<Task> _tasks;
    private boolean _hideButton;

    private ArrayList<Boolean> _mToggles = new ArrayList<Boolean>();

    public TaskAdapter(Context context,int ressource, List<Task> tasks, boolean hideButton) {
        super(context, ressource, tasks);
        this._context = context;
        this._tasks = tasks;
        this._hideButton=hideButton;
        for(int i = 0;i<tasks.size();i++) {
            _mToggles.add(false);
        }
        setToggleList(_mToggles);
    }

    public void setButtonVisibility(boolean hideButton){
        this._hideButton = hideButton;
    }

    public View getCustomView(final int position, View convertView, ViewGroup parent){
        final int which=position;
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.task_row,parent, false);
        }

        TaskViewHolder viewHolder = (TaskViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new TaskViewHolder();
            //viewHolder.tv_date = (TextView) convertView.findViewById(R.id.tv_date);
            viewHolder.tv_duration = (TextView) convertView.findViewById(R.id.tv_duration);
            viewHolder.tv_company_value = (TextView) convertView.findViewById(R.id.tv_company_value);
            viewHolder.tv_activity_value = (TextView) convertView.findViewById(R.id.tv_activity_value);
            viewHolder.tv_worker_value = (TextView) convertView.findViewById(R.id.tv_worker_value);
            viewHolder.image_company = (ImageView) convertView.findViewById(R.id.image_company);
            viewHolder.image_activity = (ImageView) convertView.findViewById(R.id.image_activity);
            viewHolder.image_worker = (ImageView) convertView.findViewById(R.id.image_worker);
            viewHolder.btn_start = (ToggleButton) convertView.findViewById(R.id.btn_start);


            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Tweet> tweets
        Task task = getItem(position);

        //il ne reste plus qu'à remplir notre vue
        //viewHolder.tv_date.setText(Datas.DATE_FORMATTER.format(task.get_date().getTime()));

        viewHolder.image_company.setImageDrawable(_context.getResources().getDrawable(R.drawable.ic_business_black_24dp));
        viewHolder.image_activity.setImageDrawable(_context.getResources().getDrawable(R.drawable.ic_view_list_black_24dp));
        viewHolder.image_worker.setImageDrawable(_context.getResources().getDrawable(R.drawable.ic_person_black_24dp));
        viewHolder.tv_company_value.setText(task.get_company().get_name());
        viewHolder.tv_activity_value.setText(task.get_activity().get_name());
        viewHolder.tv_worker_value.setText(task.get_worker().get_firstname() + " " + task.get_worker().get_lastname());



        if(!_hideButton) {
            viewHolder.btn_start.setVisibility(View.VISIBLE);
            viewHolder.tv_duration.setVisibility(View.INVISIBLE);
            viewHolder.btn_start.setChecked( _mToggles.get( position ) );
            viewHolder.tv_duration.setText(task.get_duration_hhmm());
            viewHolder.btn_start.setText("GO");
        }
        else {
            viewHolder.btn_start.setVisibility(View.INVISIBLE);
            viewHolder.tv_duration.setVisibility(View.VISIBLE);
            viewHolder.tv_duration.setText(task.get_duration_hhmm());
        }

        viewHolder.btn_start.setOnClickListener(new View.OnClickListener() {

            TimeDataSource addTime = new TimeDataSource(_context);
            public void onClick(View v) {
                boolean listState = _mToggles.get(position);
                _mToggles.set(position,new Boolean(!listState));
                notifyDataSetChanged();
                addTime.addTime(getTime());

            }});

        /*if(_mToggles.get(position) == false)
        {
            //TODO
        }else
        {
            //TODO
        }*/

        return convertView;
    }

    public Time getTime()
    {
        Calendar calendar = Calendar.getInstance();
        int seconds = calendar.get(Calendar.SECOND);

        Time time = new Time();
        time.set_start(seconds);

        return time;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, View convertView,ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }


    public void setToggleList( ArrayList<Boolean> list ){
        this._mToggles = list;
        notifyDataSetChanged();
    }

    private static class TaskViewHolder{
        //private  TextView tv_date;
        private TextView tv_duration;
        private ImageView image_company;
        private ImageView image_activity;
        private ImageView image_worker;
        private TextView tv_company_value;
        private TextView tv_activity_value;
        private TextView tv_worker_value;
        private ToggleButton btn_start;

    }
}
