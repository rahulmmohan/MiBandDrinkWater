package com.example.rahul.mibanddrinkwater;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rahul.mibanddrinkwater.alarmmanager.BackgroundService;
import com.example.rahul.mibanddrinkwater.alarmmanager.DrinkReminderReceiver;
import com.example.rahul.mibanddrinkwater.btle.GattCharacteristic;
import com.example.rahul.mibanddrinkwater.btle.GattService;
import com.example.rahul.mibanddrinkwater.profiles.Mi2NotificationStrategy;
import com.example.rahul.mibanddrinkwater.profiles.Mi2TextNotificationStrategy;
import com.example.rahul.mibanddrinkwater.profiles.alertnotification.VibrationProfile;

import java.util.UUID;


public class MainActivityJava extends AppCompatActivity implements BLEMiBand2Helper.BLEAction {

    Handler handler = new Handler(Looper.getMainLooper());
    BLEMiBand2Helper helper = null;

    Mi2TextNotificationStrategy mi2TextNotificationStrategy;
    Mi2NotificationStrategy mi2NotificationStrategy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*
        helper = new BLEMiBand2Helper(MainActivityJava.this, handler);
        helper.addListener(this);

        // Setup Bluetooth:
        helper.findBluetoothDevice(myBluetoothAdapter, "MI");
        helper.ConnectToGatt();



        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mi2TextNotificationStrategy = new Mi2TextNotificationStrategy(helper);
        mi2NotificationStrategy = new Mi2NotificationStrategy(helper);
        getTouchNotifications();
        startAlert();*/
        startService(new Intent(this, BackgroundService.class));
    }

    public void startAlert() {
        int timeInSec = 2;

        Intent intent = new Intent(this, DrinkReminderReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                this.getApplicationContext(), 234, intent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);
        //alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (timeInSec * 1000),10000, pendingIntent);
    }
/*    @Override
    protected void onDestroy() {
        if (helper != null)
            helper.DisconnectGatt();
        super.onDestroy();
    }*/

    // Like network card, connect to all devices in Bluetooth (like PC in Netowrk)
    final BluetoothAdapter myBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

    public void btnRun(View view) {
        helper.findBluetoothDevice(myBluetoothAdapter, "MI");
        helper.ConnectToGatt();
    }

    public void sendMessage(View view) {
        mi2TextNotificationStrategy.startNotify();
    //mi2TextNotificationStrategy.sendCustomNotification();
      // mi2NotificationStrategy.sendCustomNotification(VibrationProfile.Companion.getProfile(5, (short) 2));
    }

    public void getTouchNotifications() {
        helper.getNotifications(
                GattService.UUID_SERVICE_MIBAND_SERVICE,
                GattCharacteristic.UUID_BUTTON_TOUCH);
    }

    public void btnTest(View view) throws InterruptedException {
        getTouchNotifications();
    }


    /* ===========  EVENTS (background thread) =============== */

    @Override
    public void onDisconnect() {

    }

    @Override
    public void onConnect() {

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
                    Toast.makeText(MainActivityJava.this,
                            "Button Press! "
                            , Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}

/*
Credit and thanks:

https://github.com/lwis/miband-notifier/
http://allmydroids.blogspot.co.il/2014/12/xiaomi-mi-band-ble-protocol-reverse.html
https://github.com/Freeyourgadget/Gadgetbridge
http://stackoverflow.com/questions/20043388/working-with-ble-android-4-3-how-to-write-characteristics

// Available services\Characteristics:
http://jellygom.com/2016/09/30/Mi-Band-UUID.html
https://github.com/Freeyourgadget/Gadgetbridge/blob/master/app/src/main/java/nodomain/freeyourgadget/gadgetbridge/devices/miband/MiBand2Service.java
https://devzone.nordicsemi.com/question/310/notificationindication-difference/

// Heartbeat info
http://stackoverflow.com/questions/36311874/heart-rate-measuring-using-xiaomi-miband-and-ble
https://github.com/AlexanderHryk/MiFood/
again: http://stackoverflow.com/questions/20043388/working-with-ble-android-4-3-how-to-write-characteristics
https://github.com/dkhmelenko/miband-android/blob/master/miband-sdk/src/main/java/com/khmelenko/lab/miband/model/Protocol.java

http://stackoverflow.com/questions/7378936/how-to-show-toast-message-from-background-thread
http://stackoverflow.com/questions/6270132/create-a-custom-event-in-java

http://stackoverflow.com/questions/6537023/how-can-i-perform-arithmetic-operation-with-dates-in-java
*/
