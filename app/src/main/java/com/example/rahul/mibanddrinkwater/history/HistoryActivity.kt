package com.example.rahul.mibanddrinkwater.history

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.rahul.mibanddrinkwater.R
import kotlinx.android.synthetic.main.activity_history.*
import android.R.attr.action
import android.view.animation.AccelerateDecelerateInterpolator
import android.graphics.Color.parseColor
import com.db.chart.model.BarSet
import android.animation.PropertyValuesHolder
import android.graphics.Color
import android.os.Build
import android.view.View
import com.db.chart.animation.Animation
import com.db.chart.tooltip.Tooltip
import com.db.chart.view.BarChartView
import android.R.attr.action
import com.db.chart.renderer.YRenderer
import com.db.chart.renderer.XRenderer
import com.db.chart.util.Tools
import android.graphics.DashPathEffect
import android.graphics.Paint
import android.support.v4.content.ContextCompat
import com.db.chart.renderer.AxisRenderer
import com.db.chart.view.StackBarChartView


class HistoryActivity : AppCompatActivity() {
    private val mLabels = arrayOf("JAN", "FEB", "MAR", "ABR", "MAI", "JUN", "JUL", "AGO", "SET", "OUT", "NOV", "DEZ")
    private val mLabels2 = arrayOf("SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT")

    private val mValuesOne = arrayOf(
            floatArrayOf(30f, 40f, 25f, 25f, 40f, 25f, 25f, 30f, 30f, 25f, 40f, 25f),
            floatArrayOf(30f, 30f, 25f, 40f, 25f, 30f, 40f, 30f, 30f, 25f, 25f, 25f),
            floatArrayOf(30f, 40f, 25f, 25f, 40f, 25f, 25f),
            floatArrayOf(30f, 30f, 25f, 40f, 25f, 30f, 40f))
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
              randomSet(chart)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })

    }

    private fun randomSet(barView: StackBarChartView) {

        var stackBarSet = BarSet(mLabels2, mValuesOne[2])
        stackBarSet.color = ContextCompat.getColor(this,R.color.textColor)
        barView.addData(stackBarSet)

        stackBarSet = BarSet(mLabels2, mValuesOne[3])
        stackBarSet.color = ContextCompat.getColor(this,R.color.background_white)
        barView.addData(stackBarSet)
         /*
        stackBarSet = BarSet(mLabels, mValuesOne[2])
        stackBarSet.color = parseColor("#ff7a57")
        barView.addData(stackBarSet)*/

        val order = intArrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11)
        barView.setXLabels(AxisRenderer.LabelPosition.OUTSIDE)
                .setYLabels(AxisRenderer.LabelPosition.OUTSIDE)
                .show(Animation().inSequence(.5f, order))
    }

}
