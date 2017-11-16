package com.example.rahul.mibanddrinkwater.profiles.alertnotification


/**
 * https://www.bluetooth.com/specifications/gatt/viewer?attributeXmlFile=org.bluetooth.characteristic.alert_level.xml
 */
// 3-255 reserved

/**
 * The alert level ID
 * To be used as uint8 value
 * @return
 */
enum class AlertLevel  constructor(val id: Int) {
    NoAlert(0),
    MildAlert(1),
    HighAlert(2)
}
