package ch.hevs.androidproject644.js.workingtime.Controler;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.Switch;

import ch.hevs.androidproject644.js.workingtime.DB.ActivityDataSource;
import ch.hevs.androidproject644.js.workingtime.DB.CompanyDataSource;
import ch.hevs.androidproject644.js.workingtime.R;
import ch.hevs.androidproject644.js.workingtime.model.Company;

/**
 * Created by Jacques on 11.11.2016.
 */

public class C_Company {

    public static boolean controlSwitch(Switch switchC)
    {
        if(switchC.isChecked())
            return true;
        return false;
    }

    public static Drawable get_Logo(Context c) {
        return c.getResources().getDrawable(R.drawable.ic_business_black_24dp);
    }

    public static Company getCompanyById(long id, Context context){
        Company company;
        CompanyDataSource wreq = new CompanyDataSource(context);
        company = wreq.getCompanyByID(id);
        return company;
    }
}
