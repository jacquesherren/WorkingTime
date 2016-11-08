package ch.hevs.androidproject644.js.workingtime.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ch.hevs.androidproject644.js.workingtime.Controler.C_Worker;
import ch.hevs.androidproject644.js.workingtime.R;
import ch.hevs.androidproject644.js.workingtime.model.Datas;
import ch.hevs.androidproject644.js.workingtime.model.Worker;

/**
 * Created by Jacques on 26.10.2016.
 */

public class WorkerAdapter extends ArrayAdapter<Worker> {
    private Context _context;
    public WorkerAdapter(Context context, List<Worker> workers) {

        super(context, 0, workers);
        this._context=context;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.worker_row,parent, false);
        }

        WorkerViewHolder viewHolder = (WorkerViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new WorkerViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.birthdate = (TextView) convertView.findViewById(R.id.tv_birthdate_value);
            viewHolder.sex = (ImageView) convertView.findViewById(R.id.tv_sex);
            viewHolder.active = (TextView) convertView.findViewById(R.id.tv_active);
            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Worker> worker
        Worker worker = getItem(position);

        //il ne reste plus qu'à remplir notre vue
        viewHolder.name.setText(worker.get_lastname() + " " + worker.get_firstname());
        viewHolder.birthdate.setText(Datas.DATE_FORMATTER.format(worker.get_birthdate().getTime()));
        //if(worker.get_sex()=='m')
        viewHolder.sex.setImageDrawable(C_Worker.get_ImageSex(_context,worker.get_sex()));
        //else
        //    viewHolder.sex.setImageDrawable(new ColorDrawable(Color.MAGENTA));

        if(worker.is_active()==true)
            viewHolder.active.setText("Available");
        else
            viewHolder.active.setText("Unavailable");

        return convertView;
    }

    private class WorkerViewHolder{
        private  TextView name;
        private TextView birthdate;
        private ImageView sex;
        private TextView active;
    }
}
