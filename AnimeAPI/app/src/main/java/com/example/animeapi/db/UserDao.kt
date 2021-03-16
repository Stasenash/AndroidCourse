package com.example.animeapi.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.animeapi.models.User


@Dao
interface UserDao {
    @Insert
    fun insert(user: User)

    @Query("SELECT * FROM user")
    fun getUsers() : List<User>

    @Query("DELETE FROM user")
    fun deleteAll()

    @Query("SELECT * FROM user WHERE login = :login")
    fun getUserByLogin(login : String) : List<User>
}
