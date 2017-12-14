package com.example.rahul.mibanddrinkwater.schedule

import android.app.TimePickerDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.rahul.mibanddrinkwater.R
import kotlinx.android.synthetic.main.activity_schedule.*




class ScheduleActivity : AppCompatActivity(), ScheduleContract.View{

    private lateinit var presenter:SchedulePresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        presenter = SchedulePresenter(this)
        startAtSelector.setOnClickListener { presenter.onStartWorkAtSelect() }
        endAtSelector.setOnClickListener { presenter.onGetOffWorkAtSelect() }
    }

    override fun onResume() {
        super.onResume()
       // presenter.loadSettings()
    }

    override fun showSettings(startTime:String,endTime:String,repeatOn:String) {
        startAtView.text = startTime
        endAtView.text = endTime
    }

    override fun showStartWorkAtTimePicker(hour:Int,minutes: Int) {
        val timePickerDialog = TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { timePicker, hour, minutes ->
            presenter.setStartWorkAtTime(hour, minutes)
        }, hour, minutes, false)
        timePickerDialog.setTitle("Start work at")
        timePickerDialog.show()
    }

    override fun showGetOffWorkAtTimePicker(hour:Int,minutes: Int) {
        val timePickerDialog = TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { timePicker, hour, minutes ->
            presenter.setGetOffWorkAtTime(hour, minutes)
        }, hour, minutes, false)
        timePickerDialog.setTitle("Get off work at")
        timePickerDialog.show()
    }

    override fun showRepeatOnDialog(minutes: Int) {
    }

}
