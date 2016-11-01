package ch.hevs.androidproject644.js.workingtime.model;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jacques on 01.11.2016.
 */

public class CompaniesManager {
    private static List<Company> _companies = new ArrayList<Company>();

    public static void addCompany(Company company){
        _companies.add(company);
    }

    public static List<Company> getAllCompanies(){
        return _companies;
    }

    public static void set_AllCompanies(){

        _companies.add(new Company(1,"HES-So",false));
        _companies.add(new Company(2,"Clip industrie",true));
        _companies.add(new Company(3,"Novelis",false));
        _companies.add(new Company(4,"SAP",true));
    }
}
