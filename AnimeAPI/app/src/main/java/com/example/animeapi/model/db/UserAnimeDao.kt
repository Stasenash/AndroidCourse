package com.example.animeapi.model.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.animeapi.model.UserAnime;


@Dao
interface UserAnimeDao {
    @Insert
    fun insert(userAnime: UserAnime)

    @Query("DELETE FROM user_anime")
    fun deleteAll()

    @Query("SELECT * FROM user_anime WHERE type = 'liked' and user_id = :user_id")
    fun getLikedAnimesByUser(user_id : Int) : List<UserAnime>

    @Query("SELECT * FROM user_anime WHERE type = 'watched' and user_id = :user_id")
    fun getWatchedAnimesByUser(user_id : Int) : List<UserAnime>
}
