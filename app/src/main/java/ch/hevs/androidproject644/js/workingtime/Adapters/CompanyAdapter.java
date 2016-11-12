package ch.hevs.androidproject644.js.workingtime.Adapters;

import android.content.Context;
import android.content.res.Resources;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ch.hevs.androidproject644.js.workingtime.Controler.C_Company;
import ch.hevs.androidproject644.js.workingtime.MainActivity;
import ch.hevs.androidproject644.js.workingtime.R;
import ch.hevs.androidproject644.js.workingtime.model.Company;

/**
 * Created by Jacques on 26.10.2016.
 */

public class CompanyAdapter extends ArrayAdapter<Company> {
    private Context _context;
    private List<Company> _companies;

    public CompanyAdapter(Context context,int ressource, List<Company> companies) {
        super(context, ressource, companies);
        this._context = context;
        this._companies = companies;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        return getCustomView(position, convertView, parent);
    }

    @Override
    public void setDropDownViewResource(int resource) {
        super.setDropDownViewResource(resource);
    }

    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public View getCustomView(int position, View convertView, ViewGroup parent){
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.company_row,parent, false);
        }

        CompanyViewHolder viewHolder = (CompanyViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new CompanyViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.img_Logo = (ImageView) convertView.findViewById(R.id.img_logo);
            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Tweet> tweets
        Company company = getItem(position);

        //il ne reste plus qu'à remplir notre vue
        viewHolder.name.setText(company.get_name());
        viewHolder.img_Logo.setImageDrawable(C_Company.get_Logo(_context));


        return convertView;
    }

    private class CompanyViewHolder{
        private  TextView name;
        private ImageView img_Logo;
    }
}
