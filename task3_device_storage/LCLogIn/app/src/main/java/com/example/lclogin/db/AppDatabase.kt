package com.example.lclogin.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.lclogin.models.Unicorn

@Database(entities = arrayOf(Unicorn::class), version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun unicornDao(): UnicornDao

    companion object {
        fun createDb(contex: Context) =
            Room.databaseBuilder(contex, AppDatabase::class.java, "lclogin").build()

    }
}