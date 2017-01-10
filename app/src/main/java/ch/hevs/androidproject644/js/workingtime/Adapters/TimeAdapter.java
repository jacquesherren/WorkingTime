package ch.hevs.androidproject644.js.workingtime.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ch.hevs.androidproject644.js.workingtime.Controler.C_Time;
import ch.hevs.androidproject644.js.workingtime.R;
import ch.hevs.androidproject644.js.workingtime.model.Datas;
import ch.hevs.androidproject644.js.workingtime.model.Time;

/**
 * Created by Jacques on 22.11.2016.
 */

public class TimeAdapter extends ArrayAdapter<Time> {
    private Context _context;
    private List<Time> _times;

    public TimeAdapter(Context context, int resource, List<Time> times) {
        super(context, resource, times);
        this._context=context;
        this._times=times;

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);

    }

    public View getCustomView(int position, View convertView, ViewGroup parent){
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.time_row,parent, false);
        }

        TimeViewHolder viewHolder = (TimeViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new TimeViewHolder();
            viewHolder.tv_start = (TextView) convertView.findViewById(R.id.tv_start);
            viewHolder.tv_start_value = (TextView) convertView.findViewById(R.id.tv_start_value);
            viewHolder.tv_duration = (TextView) convertView.findViewById(R.id.tv_duration);
            viewHolder.tv_duration_value = (TextView) convertView.findViewById(R.id.tv_duration_value);
            viewHolder.tv_stop = (TextView) convertView.findViewById(R.id.tv_stop);
            viewHolder.tv_stop_value = (TextView) convertView.findViewById(R.id.tv_stop_value);

            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Tweet> tweets
        Time time = getItem(position);

        //il ne reste plus qu'à remplir notre vue
        viewHolder.tv_start.setText(R.string.start_label);
        viewHolder.tv_start_value.setText(Datas.TIME_FORMATTER.format(time.get_start().getTimeInMillis()));

        viewHolder.tv_duration.setText(R.string.duration_label);
        viewHolder.tv_duration_value.setText( C_Time.getFormatedDuration(time.get_duration()));

        viewHolder.tv_stop.setText(R.string.stop_label);
        viewHolder.tv_stop_value.setText(Datas.TIME_FORMATTER.format(time.get_stop().getTimeInMillis()));

        return convertView;
    }

    private static class TimeViewHolder{

        private TextView tv_duration;
        private TextView tv_duration_value;

        private TextView tv_start;
        private TextView tv_start_value;

        private TextView tv_stop;
        private TextView tv_stop_value;


    }
}
