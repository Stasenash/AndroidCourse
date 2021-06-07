package com.example.animeapi.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.animeapi.R
import com.example.animeapi.model.Anime
import com.example.animeapi.model.db.AnimeDao
import com.example.animeapi.model.db.AppDatabase
import com.example.animeapi.model.db.UserAnimeDao
import com.example.animeapi.model.db.UserDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class LikedFragment : Fragment() {

    var userAnimeDao: UserAnimeDao? = null
    var userDao: UserDao? = null
    var animeDao: AnimeDao? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        userAnimeDao = AppDatabase.createDb(requireContext()).userAnimeDao()
        userDao = AppDatabase.createDb(requireContext()).userDao()
        animeDao = AppDatabase.createDb(requireContext()).animeDao()

        GlobalScope.launch(Dispatchers.IO) {
            val user = userDao!!.getActiveUser()
            val userAnimes = userAnimeDao?.getLikedAnimesByUser(user.id)
            val animes = mutableListOf<Anime>()
            if (userAnimes != null) {
                for (anime in userAnimes) {
                    animes.add(animeDao!!.getAnimeById(anime.anime_id))
                }
            }
            withContext(Dispatchers.Main) {
                val rv = view?.findViewById<RecyclerView>(R.id.rec_view)

                rv?.layoutManager = GridLayoutManager(view?.context, 2)
                val adapter = AnimeAdapter()
                rv?.adapter = adapter

                adapter.update(animes)
            }
        }
        return inflater.inflate(R.layout.fragment_liked, container, false)
    }
}