package ch.hevs.androidproject644.js.workingtime;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;

import static ch.hevs.androidproject644.js.workingtime.R.id.tgb_WorkBreak;

/**
 * A placeholder fragment containing a simple view.
 */
public class TimeRecordingActivityFragment_Infos extends Fragment {



    public TimeRecordingActivityFragment_Infos() {
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.time_recording_infos_fragment, container, false);
    }
}
