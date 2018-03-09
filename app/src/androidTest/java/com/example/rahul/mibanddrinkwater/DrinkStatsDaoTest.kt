package com.example.rahul.mibanddrinkwater

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.example.rahul.mibanddrinkwater.data.AppDatabase
import com.example.rahul.mibanddrinkwater.data.DailyDrinkStats
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.robolectric.RobolectricTestRunner
import java.sql.Time

/**
 * Created by rahul.mohan on 09/03/18.
 */
@RunWith(RobolectricTestRunner::class)
class DrinkStatsDaoTest {
    private lateinit var appDatabase: AppDatabase

    @Before
    fun initDb(){
        appDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),AppDatabase::class.java).build()
    }

    @After
    fun closeDb(){
        appDatabase.close()
    }
    @Test
    fun insert_DrinkStats_Saves_Data(){
        var drinkStats = DailyDrinkStats(1000,750,"1234")
        appDatabase.drinkStatsDao().insert(drinkStats)

        val all = appDatabase.drinkStatsDao().getAll()
        assert(all.isNotEmpty())

    }
}