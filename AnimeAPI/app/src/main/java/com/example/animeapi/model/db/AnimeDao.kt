package com.example.animeapi.model.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.animeapi.model.Anime
import com.example.animeapi.model.User


@Dao
interface AnimeDao {
    @Insert
    fun insert(anime: Anime)

    @Query("SELECT * FROM anime")
    fun getAnimes() : List<Anime>

    @Query("SELECT * FROM anime WHERE mal_id = :malId")
    fun getAnimeById(malId : String) : Anime

    @Query("DELETE FROM anime")
    fun deleteAll()

    @Query("SELECT * FROM anime WHERE title LIKE '%' || :title || '%'")
    fun getAnimeLikeTitle(title : String) : List<Anime>

    @Query("SELECT * FROM anime ORDER BY score desc")
    fun getAnimeSortedByScore() : List<Anime>
}
