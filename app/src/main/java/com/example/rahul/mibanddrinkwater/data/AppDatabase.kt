package com.example.rahul.mibanddrinkwater.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.Room
import android.content.Context


/**
 * Created by rahul on 22/11/17.
 */
@Database(entities = arrayOf(DailyDrinkStats::class), version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun drinkStatsDao(): DrinkStatsDao

    companion object {
        lateinit  var INSTANCE: AppDatabase
        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.inMemoryDatabaseBuilder(context.applicationContext, AppDatabase::class.java)
                        .allowMainThreadQueries()
                        .build()
            }
            return INSTANCE
        }
    }
}