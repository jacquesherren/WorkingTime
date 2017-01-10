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

import ch.hevs.androidproject644.js.workingtime.backend.timeApi.TimeApi;
import ch.hevs.androidproject644.js.workingtime.backend.timeApi.model.Time;

/**
 * Created by Jacques on 04.01.2017.
 */

public class TimeAsyncTask extends AsyncTask<Void, Void, List<Time>> {
    private static TimeApi myApiTimeService = null;
    private Context context;
    private static final String TAG = TimeAsyncTask.class.getName();
    private Time time;

    public TimeAsyncTask(Time time)
    {
        this.time=time;
        if(myApiTimeService == null) {  // Only do this once
            TimeApi.Builder builder = new TimeApi.Builder(AndroidHttp.newCompatibleTransport(),
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

            myApiTimeService = builder.build();
        }
    }

    @Override
    protected List<Time> doInBackground(Void... params) {
        //context = params[0].first;
        //String name = params[0].second;
        try{
            // Call here the wished methods on the Endpoints
            // For instance insert

            if(time != null) {
                myApiTimeService.insert(time).execute();
                Log.i(TAG, "insert time");
            }
            // and for instance return the list of all employees
            return myApiTimeService.list().execute().getItems();

        } catch (IOException e){
            Log.e(TAG, e.toString());
            return new ArrayList<Time>();
        }
    }

    @Override
    protected void onPostExecute(List<Time> result){

            if(result != null) {
                for (Time time : result) {
                    Log.i(TAG, "id: " + time.getId() +
                            " start : " + time.getStart() +
                            " stop : " + time.getStop() +
                            " duration : " + time.getDuration() +
                            " taskid : " + time.getTask()

                    );

                    /*for (Phone phone : employee.getPhones()) {
                        Log.i(TAG, "Phone number: " + phone.getNumber() + " Type: " + phone.getType());
                    }*/
                }
            }
    }
}

