package com.example.rahul.mibanddrinkwater.alarmmanager;

/**
 * Created by Rahul on 16/11/17.
 */

import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.content.*;
import android.os.*;
import android.widget.Toast;

import com.example.rahul.mibanddrinkwater.BLEMiBand2Helper;
import com.example.rahul.mibanddrinkwater.MainActivityJava;
import com.example.rahul.mibanddrinkwater.btle.GattCharacteristic;
import com.example.rahul.mibanddrinkwater.btle.GattService;
import com.example.rahul.mibanddrinkwater.profiles.Mi2NotificationStrategy;
import com.example.rahul.mibanddrinkwater.profiles.Mi2TextNotificationStrategy;

import java.util.UUID;

public class BackgroundService extends Service implements BLEMiBand2Helper.BLEAction{

    public Context context = this;
    public Handler handler = null;
    public static Runnable runnable = null;
    Handler handler1 = new Handler(Looper.getMainLooper());
    public static BLEMiBand2Helper helper = null;
    // Like network card, connect to all devices in Bluetooth (like PC in Netowrk)
    final BluetoothAdapter myBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    Mi2TextNotificationStrategy mi2TextNotificationStrategy;
    Mi2NotificationStrategy mi2NotificationStrategy;
    DrinkReminderReceiver mReceiver;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        mReceiver = new DrinkReminderReceiver();
        IntentFilter filter = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
        registerReceiver(mReceiver, filter);
        helper =BLEMiBand2Helper.getInstance(this);
        helper.addListener(this);
        // Setup Bluetooth:
        helper.findBluetoothDevice(myBluetoothAdapter, "MI");
        helper.ConnectToGatt();



        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Toast.makeText(this, "Service created!", Toast.LENGTH_LONG).show();

        handler = new Handler();
        runnable = new Runnable() {
            public void run() {
                mi2TextNotificationStrategy.startNotify();
                handler.postDelayed(runnable, 15000);
            }
        };
        mi2TextNotificationStrategy = new Mi2TextNotificationStrategy(helper);
        mi2NotificationStrategy = new Mi2NotificationStrategy(helper);
        helper.getNotifications(
                GattService.UUID_SERVICE_MIBAND_SERVICE,
                GattCharacteristic.UUID_BUTTON_TOUCH);
        handler.postDelayed(runnable, 15000);

    }

    @Override
    public void onDestroy() {
        /* IF YOU WANT THIS SERVICE KILLED WITH THE APP THEN UNCOMMENT THE FOLLOWING LINE */
        //handler.removeCallbacks(runnable);
        unregisterReceiver(mReceiver);
        Toast.makeText(this, "Service stopped", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onStart(Intent intent, int startid) {
        Toast.makeText(this, "Service started by user.", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDisconnect() {
       // handler.removeCallbacks(runnable);
    }

    @Override
    public void onConnect() {
//        Toast.makeText(this, "connected", Toast.LENGTH_LONG).show();
        mi2TextNotificationStrategy = new Mi2TextNotificationStrategy(helper);
        mi2NotificationStrategy = new Mi2NotificationStrategy(helper);
        mi2TextNotificationStrategy.startNotify();
        helper.getNotifications(
                GattService.UUID_SERVICE_MIBAND_SERVICE,
                GattCharacteristic.UUID_BUTTON_TOUCH);
        //handler.postDelayed(runnable, 15000);
    }

    @Override
    public void onRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {

    }

    @Override
    public void onWrite(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {

    }

    @Override
    public void onNotification(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
        UUID alertUUID = characteristic.getUuid();
        if (alertUUID.equals(GattCharacteristic.UUID_BUTTON_TOUCH)) {
            handler.post(new Runnable() {

                @Override
                public void run() {
                    Toast.makeText(context,
                            "Button Press! "
                            , Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}