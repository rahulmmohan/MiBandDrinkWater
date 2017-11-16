package com.example.rahul.mibanddrinkwater.profiles;

import android.bluetooth.BluetoothGattCharacteristic;
import android.support.annotation.Nullable;

import com.example.rahul.mibanddrinkwater.BLEMiBand2Helper;
import com.example.rahul.mibanddrinkwater.btle.GattCharacteristic;
import com.example.rahul.mibanddrinkwater.btle.GattService;
import com.example.rahul.mibanddrinkwater.profiles.alertnotification.VibrationProfile;


public class Mi2NotificationStrategy {

    private final BluetoothGattCharacteristic alertLevelCharacteristic;
    private BLEMiBand2Helper helper;

    public Mi2NotificationStrategy(BLEMiBand2Helper helper) {
        this.helper = helper;
        alertLevelCharacteristic = helper.getCharacteristic(GattService.UUID_SERVICE_ALERT_NOTIFICATION,GattCharacteristic.UUID_CHARACTERISTIC_ALERT_LEVEL);
    }

    protected void sendCustomNotification(VibrationProfile vibrationProfile) {
        for (short i = 0; i < vibrationProfile.getRepeat(); i++) {
            int[] onOffSequence = vibrationProfile.getOnOffSequence();
            for (int j = 0; j < onOffSequence.length; j++) {
                int on = onOffSequence[j];
                on = Math.min(500, on); // longer than 500ms is not possible
                startNotify(vibrationProfile.getAlertLevel());
                helper.wait(on);
                stopNotify();
                if (++j < onOffSequence.length) {
                    int off = Math.max(onOffSequence[j], 25); // wait at least 25ms
                    helper.wait(off);
                }
            }
        }
    }

    protected void startNotify( int alertLevel) {
        helper.writeData(alertLevelCharacteristic, new byte[] {(byte) alertLevel});

    }

    protected void stopNotify() {
        helper.writeData(alertLevelCharacteristic, new byte[]{GattCharacteristic.NO_ALERT});
    }

}
