package com.example.rahul.mibanddrinkwater.btle

import com.example.rahul.mibanddrinkwater.btle.Const.BASE_UUID
import java.util.*

/**
 * Created by rahul on 16/11/17.
 */
object GattCharacteristic {

    @JvmField val UUID_CHARACTERISTIC_ALERT_LEVEL = UUID.fromString(String.format(BASE_UUID, "2A06"))
    @JvmField val UUID_CHARACTERISTIC_NEW_ALERT = UUID.fromString(String.format(BASE_UUID, "2A46"))
    @JvmField val UUID_BUTTON_TOUCH = UUID.fromString("00000010-0000-3512-2118-0009af100700")

    @JvmField val NO_ALERT: Byte = 0x0
    @JvmField val MILD_ALERT: Byte = 0x1
    @JvmField val HIGH_ALERT: Byte = 0x2
}