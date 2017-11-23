package com.example.rahul.mibanddrinkwater.home

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.example.rahul.mibanddrinkwater.R
import com.example.rahul.mibanddrinkwater.history.HistoryActivity

import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.content_home.*

class HomeActivity : AppCompatActivity() ,HomeContract.View{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        //waveview2.progress=1
        fab.setOnClickListener { view ->
            waveLoadingView.progressValue+=10
        }
        fab3.setOnClickListener({
            startActivity(Intent(this,HistoryActivity::class.java))
        })

    }

}
