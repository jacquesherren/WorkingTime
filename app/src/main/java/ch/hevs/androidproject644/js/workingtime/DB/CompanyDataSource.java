package ch.hevs.androidproject644.js.workingtime.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import ch.hevs.androidproject644.js.workingtime.Adapters.CompanyAdapter;
import ch.hevs.androidproject644.js.workingtime.model.Company;

/**
 * Created by Samuel on 08.11.2016.
 */

public class CompanyDataSource {

    private Context context;
    private DB_Class _dbclass;

    public CompanyDataSource(Context context) {
        _dbclass = DB_Class.getInstance(context);
        this.context = context;
    }

    public long createCompany(Company company)
    {
        long id;
        SQLiteDatabase db = _dbclass.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DB_Contract.companies.COLUMN_NAME_COMPANY_NAME, company.get_name().toString());
        values.put(DB_Contract.companies.COLUMN_NAME_AVAILABLE, company.is_active_int());

        id = db.insert(DB_Contract.companies.TABLE_COMPANIES, null, values);
        return id;
    }

    public Company getCompanyByID(long id) {
        //getreadable ici
        SQLiteDatabase db = _dbclass.getReadableDatabase();
        String sql = "SELECT * FROM " + DB_Contract.companies.TABLE_COMPANIES + " WHERE " + DB_Contract.companies.COLUMN_NAME_COMPANY_ID + " = " + id;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null) {
            cursor.moveToFirst();
            Company company = cursorToCompany(cursor);
            return company;
        }
        return null;
    }

    public List<Company> getAllCompanies()
    {
        List<Company> companies = new ArrayList<>();
        SQLiteDatabase db = _dbclass.getReadableDatabase();
        String sql = "SELECT * FROM " + DB_Contract.companies.TABLE_COMPANIES;
        Cursor cursor = db.rawQuery(sql, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Company company = cursorToCompany(cursor);
            companies.add(company);
            cursor.moveToNext();
        }

        cursor.close();
        return companies;
    }

    private Company cursorToCompany(Cursor cursor) {
        Company company = new Company();

        company.set_id(cursor.getInt(cursor.getColumnIndex(DB_Contract.tasks.COLUMN_NAME_TASK_ID)));
        company.set_name(cursor.getString(cursor.getColumnIndex(DB_Contract.companies.COLUMN_NAME_COMPANY_NAME)));
        company.set_active_int(cursor.getInt(cursor.getColumnIndex(DB_Contract.companies.COLUMN_NAME_AVAILABLE)));
        return company;
    }
}
