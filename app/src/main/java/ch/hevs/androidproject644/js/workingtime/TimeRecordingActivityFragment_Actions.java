package ch.hevs.androidproject644.js.workingtime;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ToggleButton;

import ch.hevs.androidproject644.js.workingtime.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class TimeRecordingActivityFragment_Actions extends Fragment {


    public TimeRecordingActivityFragment_Actions() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.time_recording_actions_fragment, container, false);
    }
}
