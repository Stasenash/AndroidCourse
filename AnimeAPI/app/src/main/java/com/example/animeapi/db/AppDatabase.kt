package com.example.animeapi.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.animeapi.models.User

@Database(entities = arrayOf(User::class), version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        fun createDb(contex: Context) =
            Room.databaseBuilder(contex, AppDatabase::class.java, "animeapi").build()
    }
}