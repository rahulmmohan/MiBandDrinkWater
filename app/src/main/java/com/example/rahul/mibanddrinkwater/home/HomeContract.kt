package com.example.rahul.mibanddrinkwater.home

/**
 * Created by rahul on 23/11/17.
 */
interface HomeContract {
    interface View{
        fun openHistory()
        fun openSchedule()
        fun openSettings()
        fun setDrinkTarget(target:Int)
        fun setCurrentDrinkLevel(level:Int)
        fun onUpdateDrinkLevel(level: Int)
    }

    interface Presenter{
        fun showHistory()
        fun showSchedule()
        fun showSettings()
        fun loadDrinkData()
        fun addWater()
    }
}