package com.example.rahul.mibanddrinkwater.home

/**
 * Created by rahul on 23/11/17.
 */
class HomePresenter(val view: HomeActivity) : HomeContract.Presenter{
    override fun showHistory() {
        view.openHistory()
    }

    override fun showSchedule() {
        view.openSchedule()
    }

    override fun showSettings() {
        view.openSettings()
    }

    override fun loadDrinkData() {
        view.setDrinkTarget(100)
        view.setCurrentDrinkLevel(20)
    }

    override fun addWater() {
        view.onUpdateDrinkLevel(12)
    }

}