package com.example.rahul.mibanddrinkwater.alarmmanager

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import android.bluetooth.BluetoothAdapter
import android.os.Handler
import android.os.Looper
import com.example.rahul.mibanddrinkwater.BLEMiBand2Helper


/**
 * Created by Rahul on 16/11/17.
 */
class DrinkReminderReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val action = intent.getAction()

        if (action == BluetoothAdapter.ACTION_STATE_CHANGED) {
            val state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE,
                    BluetoothAdapter.ERROR)
            when (state) {
                BluetoothAdapter.STATE_ON -> {
                     val myBluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
                    val bleMiBand2Helper =BLEMiBand2Helper.getInstance(context)
                    bleMiBand2Helper.findBluetoothDevice(myBluetoothAdapter,"MI")
                    bleMiBand2Helper.ConnectToGatt()
                }
            }
        }
    }
}