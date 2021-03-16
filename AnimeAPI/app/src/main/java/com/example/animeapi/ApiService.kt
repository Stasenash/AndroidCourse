package com.example.animeapi;

import com.example.animeapi.models.AnimeTitle
import com.example.animeapi.models.AnimeTitleList
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("anime/{id}")
    suspend fun getAnimeTitle(@Path("id") id: String) : Response<AnimeTitle>

    @GET("search/anime?q=&order_by=members&sort=desc&limit=20")
    suspend fun getListAnimeTitlesByPopularity() : Response<AnimeTitleList>


    companion object {
        const val API_URL = "https://api.jikan.moe/v3/"

        fun instance() = Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}