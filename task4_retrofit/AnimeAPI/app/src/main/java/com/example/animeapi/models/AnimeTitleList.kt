package com.example.animeapi.models

import com.google.gson.annotations.SerializedName

data class AnimeTitleList(
    @SerializedName("results") val animeTitleList: List<AnimeTitle>
)