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

import ch.hevs.androidproject644.js.workingtime.backend.workerApi.WorkerApi;
import ch.hevs.androidproject644.js.workingtime.backend.workerApi.model.Worker;

/**
 * Created by Jacques on 04.01.2017.
 */

public class WorkerAsyncTaskRemove extends AsyncTask<Void, Void, List<Worker>> {
    private static WorkerApi myApiWorkerService = null;
    private static final String TAG = WorkerAsyncTaskRemove.class.getName();

    public WorkerAsyncTaskRemove()
    {
        if(myApiWorkerService == null) {  // Only do this once
            WorkerApi.Builder builder = new WorkerApi.Builder(AndroidHttp.newCompatibleTransport(),
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

            myApiWorkerService = builder.build();
        }
    }

    @Override
    protected List<Worker> doInBackground(Void... params) {
        //context = params[0].first;
        //String name = params[0].second;
        try{
            // Call here the wished methods on the Endpoints
            // For instance insert
            if(myApiWorkerService.list().execute().getItems() != null)
            {
                List<Worker> workers = new ArrayList<Worker>();
                workers = myApiWorkerService.list().execute().getItems();
                long listSize = myApiWorkerService.list().execute().getItems().size();
                Log.i(TAG, "size : " + listSize );
                for (Worker w : workers)
                {
                    myApiWorkerService.remove(w.getId()).execute();
                    Log.i(TAG, "remove worker" );
                }
            }


            // and for instance return the list of all employees
            return myApiWorkerService.list().execute().getItems();

        } catch (IOException e){
            Log.e(TAG, e.toString());
            return new ArrayList<Worker>();
        }
    }

    @Override
    protected void onPostExecute(List<Worker> result){

            if(result != null) {
                for (Worker worker : result) {
                    Log.i(TAG, "id: " + worker.getId() +
                            " firstname : " + worker.getFirstname() +
                            " lastname : " + worker.getLastname() +
                            " sex : " + worker.getSex() +
                            " birthdate : " + worker.getBirthdate() +
                            " is active : " + worker.getActive()

                    );

                    /*for (Phone phone : employee.getPhones()) {
                        Log.i(TAG, "Phone number: " + phone.getNumber() + " Type: " + phone.getType());
                    }*/
                }
            }
    }
}

