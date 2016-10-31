package ch.hevs.androidproject644.js.workingtime;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import ch.hevs.androidproject644.js.workingtime.model.Company;

/**
 * Created by Jacques on 26.10.2016.
 */

public class CompanyAdapter extends ArrayAdapter<Company> {


    public CompanyAdapter(Context context, List<Company> companies) {
        super(context, 0, companies);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.company_row,parent, false);
        }

        CompanyViewHolder viewHolder = (CompanyViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new CompanyViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.active = (TextView) convertView.findViewById(R.id.tv_active);
            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Tweet> tweets
        Company company = getItem(position);

        //il ne reste plus qu'à remplir notre vue
        viewHolder.name.setText(company.get_name());

        if(company.is_active()==true)
            viewHolder.active.setText("Available");
        else
            viewHolder.active.setText("Unavailable");

        return convertView;
    }

    private class CompanyViewHolder{
        private  TextView name;
        private TextView active;
    }
}
