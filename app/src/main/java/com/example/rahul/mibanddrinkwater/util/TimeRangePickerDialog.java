package com.example.rahul.mibanddrinkwater.util;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TimePicker;

import com.example.rahul.mibanddrinkwater.R;


public class TimeRangePickerDialog extends DialogFragment implements View.OnClickListener {
    TabHost tabs;
    Button next,done;
    TimePicker startTimePicker, endTimePicker;
    OnTimeRangeSelectedListener onTimeRangeSelectedListener;
    boolean is24HourMode;

    public static TimeRangePickerDialog newInstance(OnTimeRangeSelectedListener callback, boolean is24HourMode) {
        TimeRangePickerDialog ret = new TimeRangePickerDialog();
        ret.initialize(callback, is24HourMode);
        return ret;
    }

    public void initialize(OnTimeRangeSelectedListener callback,
                           boolean is24HourMode) {
        onTimeRangeSelectedListener = callback;
        this.is24HourMode = is24HourMode;
    }

    public interface OnTimeRangeSelectedListener {
        void onTimeRangeSelected(int startHour, int startMin, int endHour, int endMin);
    }

    public void setOnTimeRangeSetListener(OnTimeRangeSelectedListener callback) {
        onTimeRangeSelectedListener = callback;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.timerange_picker_dialog, container, false);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        tabs = (TabHost) root.findViewById(R.id.tabHost);
        next = (Button) root.findViewById(R.id.next);
        done = (Button) root.findViewById(R.id.done);
        startTimePicker = (TimePicker) root.findViewById(R.id.startTimePicker);
        endTimePicker = (TimePicker) root.findViewById(R.id.endTimePicker);
        next.setOnClickListener(this);
        done.setOnClickListener(this);
        tabs.findViewById(R.id.tabHost);
        tabs.setup();
        TabHost.TabSpec tabpage1 = tabs.newTabSpec("one");
        tabpage1.setContent(R.id.startTimeGroup);
        tabpage1.setIndicator("Start Time");

        TabHost.TabSpec tabpage2 = tabs.newTabSpec("two");
        tabpage2.setContent(R.id.endTimeGroup);
        tabpage2.setIndicator("End Time");

        tabs.addTab(tabpage1);
        tabs.addTab(tabpage2);

        return root;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    @Override
    public void onStart() {
        super.onStart();

        // safety check
        if (getDialog() == null)
            return;
        getDialog().getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.next) {
            tabs.setCurrentTab(1);

            } else if (v.getId() == R.id.done) {
                dismiss();
                int startHour = startTimePicker.getCurrentHour();
                int startMin = startTimePicker.getCurrentMinute();
                int endHour = endTimePicker.getCurrentHour();
                int endMin = endTimePicker.getCurrentMinute();
                onTimeRangeSelectedListener.onTimeRangeSelected(startHour, startMin, endHour, endMin);
            }
        }
}