package com.example.rahul.mibanddrinkwater.profiles;

import android.bluetooth.BluetoothGattCharacteristic;
import android.support.annotation.NonNull;

import com.example.rahul.mibanddrinkwater.BLEMiBand2Helper;
import com.example.rahul.mibanddrinkwater.btle.GattCharacteristic;
import com.example.rahul.mibanddrinkwater.btle.GattService;
import com.example.rahul.mibanddrinkwater.profiles.alertnotification.AlertCategory;
import com.example.rahul.mibanddrinkwater.profiles.alertnotification.NewAlert;
import com.example.rahul.mibanddrinkwater.profiles.alertnotification.OverflowStrategy;
import com.example.rahul.mibanddrinkwater.util.BLETypeConversions;
import com.example.rahul.mibanddrinkwater.util.StringUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Mi2TextNotificationStrategy {
    private final BluetoothGattCharacteristic newAlertCharacteristic;
    private BLEMiBand2Helper helper;
    private int maxLength = 18; // Mi2-ism?

    public Mi2TextNotificationStrategy(BLEMiBand2Helper helper) {
        this.helper = helper;
        newAlertCharacteristic = helper.getCharacteristic(GattService.UUID_SERVICE_ALERT_NOTIFICATION, GattCharacteristic.UUID_CHARACTERISTIC_NEW_ALERT);
    }

    public void startNotify() {
        if(helper.writeData(newAlertCharacteristic, getNotifyMessage())) {
            helper.wait(4500);
            sendCustomNotification();
        }
    }

    protected byte[] getNotifyMessage() {
        int numAlerts = 1;
       /* if (simpleNotification != null && simpleNotification.getNotificationType() != null && simpleNotification.getAlertCategory() != AlertCategory.SMS) {
            byte customIconId = HuamiIcon.mapToIconId(simpleNotification.getNotificationType());
            if (customIconId == HuamiIcon.EMAIL) {
                // unfortunately. the email icon breaks the notification, fall back to a standard AlertCategory
                return new byte[]{BLETypeConversions.fromUint8(AlertCategory.Email.getId()), BLETypeConversions.fromUint8(numAlerts)};
            }
            return new byte[]{BLETypeConversions.fromUint8(AlertCategory.CustomHuami.getId()), BLETypeConversions.fromUint8(numAlerts), customIconId};
        }*/
        return new byte[] { BLETypeConversions.fromUint8(-6), BLETypeConversions.fromUint8(numAlerts),21};
    }

    public void sendCustomNotification() {
        AlertCategory category = AlertCategory.SMS;
        NewAlert alert = new NewAlert(category, 2, "test message");
        newAlert(alert, OverflowStrategy.MAKE_MULTIPLE);
    }
    public void newAlert( NewAlert alert, OverflowStrategy strategy) {

        if (newAlertCharacteristic != null) {
            String message = StringUtils.ensureNotNull(alert.getMessage());
            if (message.length() > maxLength && strategy == OverflowStrategy.TRUNCATE) {
                message = StringUtils.truncate(message, maxLength);
            }

            int numChunks = message.length() / maxLength;
            if (message.length() % maxLength > 0) {
                numChunks++;
            }

            try {
                boolean hasAlerted = false;
                for (int i = 0; i < numChunks; i++) {
                    int offset = i * maxLength;
                    int restLength = message.length() - offset;
                    message = message.substring(offset, offset + Math.min(maxLength, restLength));
                    if (hasAlerted && message.length() == 0) {
                        // no need to do it again when there is no text content
                        break;
                    }
                    helper.writeData(newAlertCharacteristic, getAlertMessage(alert, message, 1));
                    hasAlerted = true;
                }
                if (!hasAlerted) {
                    helper.writeData(newAlertCharacteristic, getAlertMessage(alert, "", 1));
                }
            } catch (IOException ex) {
                // ain't gonna happen
               // LOG.error("Error writing alert message to ByteArrayOutputStream");
            }
        } else {
           // LOG.warn("NEW_ALERT characteristic not available");
        }
    }
    protected byte[] getAlertMessage(NewAlert alert, String message, int chunk) throws IOException {
        ByteArrayOutputStream stream = new ByteArrayOutputStream(100);
        stream.write(BLETypeConversions.fromUint8(alert.getCategory().getId()));
        stream.write(BLETypeConversions.fromUint8(alert.getNumAlerts()));
        if (alert.getCategory() == AlertCategory.CustomHuami) {
            stream.write(BLETypeConversions.fromUint8(alert.getCustomIcon()));
        }

        if (message.length() > 0) {
            stream.write(BLETypeConversions.toUtf8s(message));
        } else {
            // some write a null byte instead of leaving out this optional value
//                stream.write(new byte[] {0});
        }
        return stream.toByteArray();
    }
}
