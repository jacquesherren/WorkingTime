package ch.hevs.androidproject644.js.workingtime.Adapters;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ch.hevs.androidproject644.js.workingtime.MainActivity;
import ch.hevs.androidproject644.js.workingtime.R;
import ch.hevs.androidproject644.js.workingtime.model.Activity;

/**
 * Created by Jacques on 26.10.2016.
 */

public class ActivityAdapter extends ArrayAdapter<Activity> {
    private Context _context;
    private List<Activity> _activities;


    public ActivityAdapter(Context context,int ressource, List<Activity> activities) {
        super(context, ressource, activities);
        this._context = context;
        this._activities = activities;
    }


    public View getCustomView(int position, View convertView, ViewGroup parent){
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_row,parent, false);
        }

        ActivitiyViewHolder viewHolder = (ActivitiyViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new ActivitiyViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.tv_name);
            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Tweet> tweets
        Activity activity = getItem(position);

        //il ne reste plus qu'à remplir notre vue
        viewHolder.name.setText(activity.get_name());

        return convertView;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        return getCustomView(position, convertView, parent);
    }

    public View getDropDownView(int position, View convertView,ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }
    @Override
    public void setDropDownViewResource(int resource) {
        super.setDropDownViewResource(resource);
    }
    private class ActivitiyViewHolder{
        private  TextView name;
    }
}
