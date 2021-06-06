package com.example.lclogin.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.lclogin.models.Unicorn


@Dao
interface UnicornDao {
    @Insert
    fun insert(unicorn: Unicorn)

    @Query("SELECT * FROM unicorn")
    fun getUnicorns() : List<Unicorn>

    @Query("DELETE FROM unicorn")
    fun deleteAll()
}
