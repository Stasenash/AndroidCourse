package com.example.animeapi

import com.example.animeapi.models.AnimeTitle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ApiServiceTest {
    private val animeTitle = "Cowboy Bebop"

    @Test fun readStringFromContext_LocalizedString() {
        GlobalScope.launch(Dispatchers.IO) {
            var title = ApiService.instance().getAnimeTitle("1").body()?.title
            assertEquals(title, animeTitle)
        }
    }
}