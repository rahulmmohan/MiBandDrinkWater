package com.example.rahul.mibanddrinkwater.schedule

/**
 * Created by rahul on 23/11/17.
 */
interface ScheduleContract {

    interface View{
        fun showSettings(startTime:String,endTime:String,repeatOn:String)
        fun showStartWorkAtTimePicker(hour:Int,minutes: Int)
        fun showGetOffWorkAtTimePicker(hour:Int,minutes: Int)
        fun showRepeatOnDialog(minutes:Int)

    }

    interface Presenter{
        fun loadSettings()
        fun onStartWorkAtSelect()
        fun onGetOffWorkAtSelect()
        fun onRepeatOnSelect()
        fun setStartWorkAtTime(hour:Int,minutes: Int)
        fun setGetOffWorkAtTime(hour:Int,minutes: Int)
    }

}