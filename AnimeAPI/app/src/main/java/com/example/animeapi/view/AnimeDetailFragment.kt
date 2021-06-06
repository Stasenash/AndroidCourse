package com.example.animeapi.view

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.animeapi.R
import com.example.animeapi.model.Anime
import com.example.animeapi.model.User
import com.example.animeapi.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AnimeDetailFragment : Fragment() {
    lateinit var anime : Anime

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_anime_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fillContent(anime)
        view.findViewById<Button>(R.id.button_rate).setOnClickListener {
            val dialogView = LayoutInflater.from(view.context).inflate(R.layout.rating_dialog, null)
            val builder = AlertDialog.Builder(view.context)
                .setView(dialogView)
                .setTitle("Оценить аниме")
            val alertDialog = builder.show()

            dialogView.findViewById<Button>(R.id.button_rate_confirm).setOnClickListener {
                val rating = dialogView?.findViewById<RatingBar>(R.id.ratingBar)?.rating?.times(2)
                alertDialog.dismiss()
            }

            dialogView.findViewById<Button>(R.id.button_close).setOnClickListener {
                alertDialog.dismiss()
            }
        }
    }

    fun fillContent(anime : Anime) {
        val animeTitle = view?.findViewById<TextView>(R.id.anime_title)
        val animeImage = view?.findViewById<ImageView>(R.id.anime_image)
        val synopsis = view?.findViewById<TextView>(R.id.synopsis)
        val score = view?.findViewById<TextView>(R.id.score_field)

        animeTitle?.text = anime.title
        view?.let {
            if (animeImage != null) {
                Glide
                    .with(it)
                    .load(anime.image_url)
                    .into(animeImage)
            }
        }
        synopsis?.text = anime.synopsis
        score?.text = anime.score

    }
}