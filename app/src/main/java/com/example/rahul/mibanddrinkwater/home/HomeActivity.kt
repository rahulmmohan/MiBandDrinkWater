package com.example.rahul.mibanddrinkwater.home

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.rahul.mibanddrinkwater.R
import com.example.rahul.mibanddrinkwater.history.HistoryActivity
import com.example.rahul.mibanddrinkwater.schedule.ScheduleActivity

import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.content_home.*

class HomeActivity : AppCompatActivity() ,HomeContract.View{

    private lateinit var presenter:HomePresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        presenter = HomePresenter(this)
        //waveview2.progress=1
        addWater.setOnClickListener {
            presenter.addWater()
        }
        history.setOnClickListener({
            presenter.showHistory()
        })
        schedule.setOnClickListener({
            presenter.showSchedule()
        })

    }

    override fun onResume() {
        super.onResume()
        presenter.loadDrinkData()
    }

    override fun setDrinkTarget(target: Int) {

    }

    override fun setCurrentDrinkLevel(level: Int) {
        drinkView.progressValue = level
    }

    override fun onUpdateDrinkLevel(level: Int) {
        drinkView.progressValue = level
    }

    override fun openHistory() {
        startActivity(Intent(this, HistoryActivity::class.java))
    }

    override fun openSchedule() {
        startActivity(Intent(this, ScheduleActivity::class.java))
    }

    override fun openSettings() {

    }

}
