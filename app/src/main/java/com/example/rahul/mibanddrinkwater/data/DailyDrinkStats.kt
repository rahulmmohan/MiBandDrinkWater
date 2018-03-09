package com.example.rahul.mibanddrinkwater.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import java.util.*

/**
 * Created by rahul on 22/11/17.
 */
@Entity(tableName = "DailyDrinkStats")
data class DailyDrinkStats(
        @ColumnInfo(name = "drink_target") val target:Int,
        @ColumnInfo(name = "drink_completion") val completion:Int,
        @ColumnInfo(name = "date")  val date: String)