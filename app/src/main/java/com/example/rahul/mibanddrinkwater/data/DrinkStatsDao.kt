package com.example.rahul.mibanddrinkwater.data

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

/**
 * Created by rahul on 22/11/17.
 */
@Dao interface DrinkStatsDao {

    @Insert
    fun insert(note: DailyDrinkStats)

    @Query("SELECT * FROM DrinkWater ORDER BY date desc")
    fun getAll(): List<DailyDrinkStats>

}