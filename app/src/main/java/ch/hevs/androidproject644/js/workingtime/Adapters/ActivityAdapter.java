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
    private android.app.Activity _act;
    public Resources _res;
    LayoutInflater _inflater;

    private ArrayList<Activity> _activities;


    public ActivityAdapter(Context context, List<Activity> activities) {
        super(context, 0, activities);
    }

    public ActivityAdapter(MainActivity act, int resId, ArrayList<Activity> activities, Resources resLocal){
        super(act, resId, activities);

        this._act = act;
        this._activities = activities;
        this._res  =resLocal;

        _inflater = (LayoutInflater)act.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    public View getCustomView(int position, View convertView, ViewGroup parent){
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

    public View getView(int position, View convertView, ViewGroup parent){
        return getCustomView(position, convertView, parent);
    }

    public View getDropDownView(int position, View convertView,ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    private class ActivitiyViewHolder{
        private  TextView name;
        private TextView active;
    }
}
