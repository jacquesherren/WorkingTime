package ch.hevs.androidproject644.js.workingtime.AsycnTasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ch.hevs.androidproject644.js.workingtime.backend.companyApi.CompanyApi;
import ch.hevs.androidproject644.js.workingtime.backend.companyApi.model.Company;

/**
 * Created by Jacques on 04.01.2017.
 */

public class CompanyAsyncTaskRemove extends AsyncTask<Void, Void, List<Company>> {
    private static CompanyApi myApiCompanyService = null;
    private static final String TAG = CompanyAsyncTaskRemove.class.getName();

    public CompanyAsyncTaskRemove()
    {
        if(myApiCompanyService == null) {  // Only do this once
            CompanyApi.Builder builder = new CompanyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    //.setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setRootUrl("https://workingtime-154701.appspot.com/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiCompanyService = builder.build();
        }
    }

    @Override
    protected List<Company> doInBackground(Void... params) {
        //context = params[0].first;
        //String name = params[0].second;
        try{
            // Call here the wished methods on the Endpoints
            // For instance insert
            if(myApiCompanyService.list().execute().getItems() != null)
            {
                long listSize = myApiCompanyService.list().execute().getItems().size();
                Log.i(TAG, "size : " + listSize );
                //if(activity != null){
                for(long i = 1; i<= listSize;i++)
                {
                    myApiCompanyService.remove(i).execute();
                    Log.i(TAG, "remove companies" );
                }
            }
            // and for instance return the list of all employees
            return myApiCompanyService.list().execute().getItems();

        } catch (IOException e){
            Log.e(TAG, e.toString());
            return new ArrayList<Company>();
        }
    }

    @Override
    protected void onPostExecute(List<Company> result){

            if(result != null) {
                for (Company company : result) {
                    Log.i(TAG, "name: " + company.getName() + " id: "
                            + company.getId());

                    /*for (Phone phone : employee.getPhones()) {
                        Log.i(TAG, "Phone number: " + phone.getNumber() + " Type: " + phone.getType());
                    }*/
                }
            }
    }
}

