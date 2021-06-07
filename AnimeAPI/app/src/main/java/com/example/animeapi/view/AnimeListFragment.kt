package com.example.animeapi.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.animeapi.R
import com.example.animeapi.manager.NetworkManager
import com.example.animeapi.model.db.AnimeDao
import com.example.animeapi.model.db.AppDatabase
import com.example.animeapi.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class AnimeListFragment : Fragment() {

    var animeDao: AnimeDao? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        animeDao = AppDatabase.createDb(requireContext()).animeDao()

        val nm = context?.let { NetworkManager(it) }
        val connected = nm?.isConnectedToInternet

        if (connected!!) {
            GlobalScope.launch(Dispatchers.IO) {
                val listResponse = ApiService.instance().getListOf20AnimeByPopularity()
                withContext(Dispatchers.Main) {
                    val animes = listResponse.body()?.animeList!!
                    for (anime in animes!!) {
                        withContext(Dispatchers.IO) {
                            val animeList = animeDao?.getAnimeLikeTitle(anime.title)
                            if (animeList.isNullOrEmpty()) {
                                animeDao!!.insert(anime)
                            }
                        }
                        val rv = view?.findViewById<RecyclerView>(R.id.rec_view)

                        rv?.layoutManager = GridLayoutManager(view?.context, 2)
                        val adapter = AnimeAdapter()
                        rv?.adapter = adapter

                        adapter.update(animes)
                    }
                }
            }
        } else {
            GlobalScope.launch(Dispatchers.IO) {
                val animes = animeDao?.getAnimeSortedByScore()?.take(20)
                withContext(Dispatchers.Main) {
                    val rv = view?.findViewById<RecyclerView>(R.id.rec_view)

                    rv?.layoutManager = GridLayoutManager(view?.context, 2)
                    val adapter = AnimeAdapter()
                    rv?.adapter = adapter

                    if (animes != null) {
                        adapter.update(animes)
                    }
                }
            }
        }
        return inflater.inflate(R.layout.fragment_anime_list, container, false)
    }
}