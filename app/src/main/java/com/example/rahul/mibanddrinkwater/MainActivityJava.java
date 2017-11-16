package com.example.rahul.mibanddrinkwater;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rahul.mibanddrinkwater.btle.GattCharacteristic;
import com.example.rahul.mibanddrinkwater.btle.GattService;
import com.example.rahul.mibanddrinkwater.profiles.Mi2TextNotificationStrategy;

import java.util.UUID;


public class MainActivityJava extends AppCompatActivity implements BLEMiBand2Helper.BLEAction {
    public static final String LOG_TAG = "Yoni";

    Handler handler = new Handler(Looper.getMainLooper());
    BLEMiBand2Helper helper = null;

    TextView txtPath;
    Mi2TextNotificationStrategy mi2TextNotificationStrategy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helper = new BLEMiBand2Helper(MainActivityJava.this, handler);
        helper.addListener(this);

        // Setup Bluetooth:
        helper.findBluetoothDevice(myBluetoothAdapter, "MI");
        helper.ConnectToGatt();

        mi2TextNotificationStrategy = new Mi2TextNotificationStrategy(helper);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        getTouchNotifications();
    }


    @Override
    protected void onDestroy() {
        if (helper != null)
            helper.DisconnectGatt();
        super.onDestroy();
    }

    // Like network card, connect to all devices in Bluetooth (like PC in Netowrk)
    final BluetoothAdapter myBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

    public void btnRun(View view) {
        helper.findBluetoothDevice(myBluetoothAdapter, "MI");
        helper.ConnectToGatt();
    }

    public void sendMessage(View view) {
        mi2TextNotificationStrategy.sendCustomNotification();
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
