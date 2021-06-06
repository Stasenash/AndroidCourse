package com.example.animeapi.model.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.animeapi.model.Anime
import com.example.animeapi.model.User

@Database(entities = arrayOf(User::class, Anime::class), version = 5)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun animeDao(): AnimeDao

    companion object {
        fun createDb(contex: Context) =
            Room.databaseBuilder(contex, AppDatabase::class.java, "animeapi").build();
    }
}