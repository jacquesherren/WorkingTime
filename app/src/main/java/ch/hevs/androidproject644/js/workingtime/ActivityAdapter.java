package ch.hevs.androidproject644.js.workingtime;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import ch.hevs.androidproject644.js.workingtime.model.Activity;

/**
 * Created by Jacques on 26.10.2016.
 */

public class ActivityAdapter extends ArrayAdapter<Activity> {


    public ActivityAdapter(Context context, List<Activity> activities) {
        super(context, 0, activities);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_row,parent, false);
        }

        ActivitiyViewHolder viewHolder = (ActivitiyViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new ActivitiyViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.active = (TextView) convertView.findViewById(R.id.tv_active);
            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Tweet> tweets
        Activity activity = getItem(position);

        //il ne reste plus qu'à remplir notre vue
        viewHolder.name.setText(activity.get_name());

        if(activity.is_active()==true)
            viewHolder.active.setText("Available");
        else
            viewHolder.active.setText("Unavailable");

        return convertView;
    }

    private class ActivitiyViewHolder{
        private  TextView name;
        private TextView active;
    }
}