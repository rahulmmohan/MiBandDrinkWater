package com.example.rahul.mibanddrinkwater.schedule

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.rahul.mibanddrinkwater.R
import com.example.rahul.mibanddrinkwater.util.TimeRangePickerDialog
import kotlinx.android.synthetic.main.activity_schedule.*




class ScheduleActivity : AppCompatActivity(), ScheduleContract.View, TimeRangePickerDialog.OnTimeRangeSelectedListener {
    override fun onTimeRangeSelected(startHour: Int, startMin: Int, endHour: Int, endMin: Int) {

    }

    val TIMERANGEPICKER_TAG = "timerangepicker"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        time.setOnClickListener {
            val timePickerDialog = TimeRangePickerDialog.newInstance(this, false)
            timePickerDialog.show(supportFragmentManager, TIMERANGEPICKER_TAG)
        }
    }

}
