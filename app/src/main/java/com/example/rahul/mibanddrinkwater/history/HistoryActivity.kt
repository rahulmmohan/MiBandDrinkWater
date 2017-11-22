package com.example.rahul.mibanddrinkwater.history

import android.graphics.Rect
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import com.db.chart.animation.Animation
import com.db.chart.model.BarSet
import com.db.chart.renderer.AxisRenderer
import com.db.chart.tooltip.Tooltip
import com.db.chart.util.Tools
import com.db.chart.view.StackBarChartView
import com.example.rahul.mibanddrinkwater.R
import kotlinx.android.synthetic.main.activity_history.*


class HistoryActivity : AppCompatActivity() {
    private val mLabels = arrayOf(
            arrayOf("SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"),
            arrayOf("JAN", "FEB", "MAR", "ABR", "MAI", "JUN", "JUL", "AGO", "SET", "OUT", "NOV", "DEZ")
    )


    private val mValuesOne = arrayOf(
            floatArrayOf(30f, 40f, 25f, 25f, 40f, 25f, 25f),
            floatArrayOf(30f, 30f, 25f, 40f, 25f, 30f, 40f),
            floatArrayOf(30f, 40f, 25f, 25f, 40f, 25f, 25f, 30f, 30f, 25f, 40f, 25f),
            floatArrayOf(30f, 30f, 25f, 40f, 25f, 30f, 40f, 30f, 30f, 25f, 25f, 25f)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        randomSet(chart, 0)
        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                randomSet(chart, tab.position)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })

    }

    private fun randomSet(barView: StackBarChartView, position: Int) {
        barView.reset()
        barView.removeAllViews()
        var stackBarSet = BarSet(mLabels[position], mValuesOne[position*2])
        stackBarSet.color = ContextCompat.getColor(this, R.color.ocean_blue)
        barView.addData(stackBarSet)

        stackBarSet = BarSet(mLabels[position], mValuesOne[position*2+1])
        stackBarSet.color = ContextCompat.getColor(this, R.color.background_white)
        barView.addData(stackBarSet)

        barView.setOnEntryClickListener({ i: Int, i1: Int, rect: Rect ->
            tip.prepare(barView.getEntriesArea(0)[i1], 1f)
            //tip.postInvalidate()
        })
        barView.setXLabels(AxisRenderer.LabelPosition.OUTSIDE)
                .setYLabels(AxisRenderer.LabelPosition.OUTSIDE)
                .show(Animation().inSequence(.5f).withEndAction({

                    showTooltip(barView)
                }))

    }

    private lateinit var tip: Tooltip

    private fun showTooltip(barView: StackBarChartView) {

        // Tooltip
        tip = Tooltip(this, R.layout.tooltip_1)
        tip.setVerticalAlignment(Tooltip.Alignment.BOTTOM_TOP)
        tip.setDimensions(Tools.fromDpToPx(20f).toInt(), Tools.fromDpToPx(20f).toInt())
        tip.setMargins(0, 0, 0, Tools.fromDpToPx(10f).toInt())
        tip.prepare(barView.getEntriesArea(0).get(0), 1f)

        barView.showTooltip(tip, true)
    }

}
