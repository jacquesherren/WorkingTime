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

import ch.hevs.androidproject644.js.workingtime.backend.taskApi.TaskApi;
import ch.hevs.androidproject644.js.workingtime.backend.taskApi.model.Task;

/**
 * Created by Jacques on 04.01.2017.
 */

public class TaskAsyncTask extends AsyncTask<Void, Void, List<Task>> {
    private static TaskApi myApiTaskService = null;
    private Context context;
    private static final String TAG = TaskAsyncTask.class.getName();
    private Task task;

    public TaskAsyncTask(Task task)
    {
        this.task=task;
        if(myApiTaskService == null) {  // Only do this once
            TaskApi.Builder builder = new TaskApi.Builder(AndroidHttp.newCompatibleTransport(),
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

            myApiTaskService = builder.build();
        }
    }

    @Override
    protected List<Task> doInBackground(Void... params) {
        //context = params[0].first;
        //String name = params[0].second;
        try{
            // Call here the wished methods on the Endpoints
            // For instance insert

            if(task != null) {
                myApiTaskService.insert(task).execute();
                Log.i(TAG, "insert task");
                myApiTaskService.update(task.getId(), task).execute();
                Log.i(TAG, "update task");

            }
            // and for instance return the list of all employees
            return myApiTaskService.list().execute().getItems();

        } catch (IOException e){
            Log.e(TAG, e.toString());
            return new ArrayList<Task>();
        }
    }

    @Override
    protected void onPostExecute(List<Task> result){

            if(result != null) {
                for (Task task : result) {
                    Log.i(TAG, "id: " + task.getId() +
                            " date : " + task.getDate() +
                            " workerid : " + task.getWorkerid() +
                            " activityid : " + task.getActivityid() +
                            " companyid : " + task.getCompanyid() +
                            " is archived : " + task.getArchive()

                    );

                    /*for (Phone phone : employee.getPhones()) {
                        Log.i(TAG, "Phone number: " + phone.getNumber() + " Type: " + phone.getType());
                    }*/
                }
            }
    }
}

