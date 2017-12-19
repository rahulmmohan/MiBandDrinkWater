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
    val REQUEST_CODE = 12345
    override fun onReceive(context: Context, intent: Intent) {
        val i = Intent(context, NotificationService::class.java)
        context.startService(i)
    }
}