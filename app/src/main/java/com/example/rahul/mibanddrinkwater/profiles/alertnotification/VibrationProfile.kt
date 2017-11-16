package com.example.rahul.mibanddrinkwater.profiles.alertnotification

/**
 * Created by rahul on 16/11/17.
 */
class VibrationProfile(var id: Int, var onOffSequence: IntArray, var repeat: Short) {
    var alertLevel = AlertLevel.MildAlert.id

    companion object {
        fun getProfile(id: Int, repeat: Short): VibrationProfile = when (id) {
            0 -> VibrationProfile(id, intArrayOf(100, 0), repeat)
            1 -> VibrationProfile(id, intArrayOf(200, 200), repeat)
            2 -> VibrationProfile(id, intArrayOf(500, 1000), repeat)
            3 -> VibrationProfile(id, intArrayOf(100, 1500), repeat)
            4 -> VibrationProfile(id, intArrayOf(300, 200, 600, 2000), repeat)
            5 -> VibrationProfile(id, intArrayOf(30, 35, 30, 35, 30, 35, 30, 800), repeat)
            else -> VibrationProfile(id, intArrayOf(300, 600), repeat)
        }
    }

}