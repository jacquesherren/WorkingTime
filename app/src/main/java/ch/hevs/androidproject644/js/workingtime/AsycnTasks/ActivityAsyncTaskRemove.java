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

import ch.hevs.androidproject644.js.workingtime.backend.activityApi.ActivityApi;
import ch.hevs.androidproject644.js.workingtime.backend.activityApi.model.Activity;

/**
 * Created by Jacques on 04.01.2017.
 */

public class ActivityAsyncTaskRemove extends AsyncTask<Void, Void, List<Activity>> {
    private static ActivityApi myApiService = null;
    private static final String TAG = ActivityAsyncTaskRemove.class.getName();


    public ActivityAsyncTaskRemove() {
        if (myApiService == null) {  // Only do this once
            ActivityApi.Builder builder = new ActivityApi.Builder(AndroidHttp.newCompatibleTransport(),
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

            myApiService = builder.build();
        }
    }

    /*public List<Activity> getItems() {
        try {
            return myApiService.list().execute().getItems();
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<Activity>();
        }
    }*/


    @Override
    protected List<Activity> doInBackground(Void... params) {
        //context = params[0].first;
        //String name = params[0].second;
        try{
            // Call here the wished methods on the Endpoints
            // For instance insert
            if(myApiService.list().execute().getItems() != null)
            {
                List<Activity> activities = new ArrayList<Activity>();
                activities = myApiService.list().execute().getItems();
                long listSize = activities.size();
                Log.i(TAG, "size : " + listSize );
                for (Activity a : activities)
                {
                    myApiService.remove(a.getId()).execute();
                    Log.i(TAG, "remove activity" );
                }
            }

            //}
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

