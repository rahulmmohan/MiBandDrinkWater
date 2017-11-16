package com.example.rahul.mibanddrinkwater.btle

import com.example.rahul.mibanddrinkwater.btle.Const.BASE_UUID
import java.util.*

/**
 * Created by rahul on 16/11/17.
 */
object GattService {

    @JvmField val UUID_SERVICE_ALERT_NOTIFICATION = UUID.fromString(String.format(BASE_UUID, "1811"))
    @JvmField val UUID_SERVICE_ALERT = UUID.fromString(String.format(BASE_UUID, "1802"))
    @JvmField val UUID_SERVICE_MIBAND_SERVICE = UUID.fromString(String.format(BASE_UUID, "FEE0"))

}