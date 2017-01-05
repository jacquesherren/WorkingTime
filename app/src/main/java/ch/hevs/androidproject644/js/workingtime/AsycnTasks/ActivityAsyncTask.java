package ch.hevs.androidproject644.js.workingtime.AsycnTasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;


import ch.hevs.androidproject644.js.workingtime.backend.activityApi.ActivityApi;

import ch.hevs.androidproject644.js.workingtime.backend.activityApi.model.Activity;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jacques on 04.01.2017.
 */

public class ActivityAsyncTask extends AsyncTask<Void, Void, List<Activity>> {
    private static ActivityApi myApiService = null;
    private Context context;
    private static final String TAG = ActivityAsyncTask.class.getName();
    private Activity activity;

    public ActivityAsyncTask(Activity activity)
    {
        this.activity=activity;
        if(myApiService == null) {  // Only do this once
            ActivityApi.Builder builder = new ActivityApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }
    }

    @Override
    protected List<Activity> doInBackground(Void... params) {
        //context = params[0].first;
        //String name = params[0].second;
        try{
            // Call here the wished methods on the Endpoints
            // For instance insert
            if(activity != null){
                myApiService.insert(activity).execute();
                Log.i(TAG, "insert activity");
            }
            // and for instance return the list of all employees
            return myApiService.list().execute().getItems();

        } catch (IOException e){
            Log.e(TAG, e.toString());
            return new ArrayList<Activity>();
        }
    }

    @Override
    protected void onPostExecute(List<Activity> result){

            if(result != null) {
                for (Activity activity : result) {
                    Log.i(TAG, "name: " + activity.getName() + " id: "
                            + activity.getId());

                    /*for (Phone phone : employee.getPhones()) {
                        Log.i(TAG, "Phone number: " + phone.getNumber() + " Type: " + phone.getType());
                    }*/
                }
            }
    }
}

