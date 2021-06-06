package com.example.animeapi.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.animeapi.R
import com.example.animeapi.model.db.AnimeDao
import com.example.animeapi.model.db.AppDatabase
import com.example.animeapi.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchFragment : Fragment() {

    var animeDao: AnimeDao? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        animeDao = AppDatabase.createDb(requireContext()).animeDao()
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.search_button).setOnClickListener {
            val textField = view.findViewById<EditText>(R.id.search_field)
            GlobalScope.launch(Dispatchers.IO) {
                val listResponse = ApiService.instance().getAnimeByName(textField.text.toString())

                withContext(Dispatchers.Main) {
                    val animes = listResponse.body()?.animeList!!
                    for (anime in animes) {
                        withContext(Dispatchers.IO) {
                            val animeList = animeDao?.getAnimeByTitle(anime.title)
                            if (animeList.isNullOrEmpty()) {
                                animeDao!!.insert(anime)
                            }
                        }
                    }

                    val rv = view.findViewById<RecyclerView>(R.id.rec_view)

                    rv?.layoutManager = GridLayoutManager(view.context, 2)
                    val adapter = AnimeAdapter()
                    rv?.adapter = adapter

                    adapter.update(animes)
                }
            }
        }
    }
}