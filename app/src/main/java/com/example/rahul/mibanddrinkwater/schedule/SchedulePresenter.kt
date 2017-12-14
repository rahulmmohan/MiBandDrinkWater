package com.example.rahul.mibanddrinkwater.schedule

/**
 * Created by rahul on 23/11/17.
 */
class SchedulePresenter(val view: ScheduleActivity) : ScheduleContract.Presenter {

    override fun loadSettings() {
        view.showSettings("9:30 AM","6:00 PM","45")
    }

    override fun setStartWorkAtTime(hour: Int, minutes: Int) {
        loadSettings()
    }

    override fun setGetOffWorkAtTime(hour: Int, minutes: Int) {
        loadSettings()
    }

    override fun onStartWorkAtSelect() {
        view.showStartWorkAtTimePicker(10,0)
    }

    override fun onGetOffWorkAtSelect() {
        view.showStartWorkAtTimePicker(6,30)
    }

    override fun onRepeatOnSelect() {
    }
}