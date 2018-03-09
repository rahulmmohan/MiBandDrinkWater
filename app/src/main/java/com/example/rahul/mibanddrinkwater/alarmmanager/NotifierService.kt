package com.example.rahul.mibanddrinkwater.alarmmanager

import android.app.job.JobParameters
import android.app.job.JobService
import android.bluetooth.BluetoothGatt
import android.bluetooth.BluetoothGattCharacteristic
import com.example.rahul.mibanddrinkwater.BLEMiBand2Helper
import com.example.rahul.mibanddrinkwater.btle.GattCharacteristic

/**
 * Created by rahul.mohan on 09/03/18.
 */
class NotifierService : JobService(), BLEMiBand2Helper.BLEAction{

    override fun onStopJob(params: JobParameters?): Boolean {
        return true
    }

    override fun onStartJob(params: JobParameters?): Boolean {
        return false
    }

    override fun onDisconnect() {
    }

    override fun onConnect() {
    }

    override fun onRead(gatt: BluetoothGatt?, characteristic: BluetoothGattCharacteristic?, status: Int) {
    }

    override fun onWrite(gatt: BluetoothGatt?, characteristic: BluetoothGattCharacteristic?, status: Int) {
    }

    override fun onNotification(gatt: BluetoothGatt, characteristic: BluetoothGattCharacteristic) {
        val uuid = characteristic.uuid
        if(uuid == GattCharacteristic.UUID_BUTTON_TOUCH){

        }
    }
}