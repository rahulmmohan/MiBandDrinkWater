package com.example.rahul.mibanddrinkwater.home

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.example.rahul.mibanddrinkwater.R

import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.content_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        //waveview2.progress=1
        fab.setOnClickListener { view ->
            waveLoadingView.progressValue+=10
        }

    }

}
