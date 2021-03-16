package com.example.animeapi.models
import com.google.gson.annotations.SerializedName

data class AnimeTitle(
    val title: String,
    val id: String,
    val synopsis: String,
    @SerializedName("title_japanese") val title_japanese: String,
    @SerializedName("result") val animeTitleList: List<AnimeTitle>
)