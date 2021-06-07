package com.example.animeapi.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.animeapi.R
import com.example.animeapi.model.Anime
import com.example.animeapi.model.User
import com.example.animeapi.model.UserAnime
import com.example.animeapi.model.db.AnimeDao
import com.example.animeapi.model.db.AppDatabase
import com.example.animeapi.model.db.UserAnimeDao
import com.example.animeapi.model.db.UserDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class AnimeAdapter : RecyclerView.Adapter<AnimeAdapter.AnimeTitleHolder>() {
    var list = listOf<Anime>()
    lateinit var mContext : Context
    var userAnimeDao: UserAnimeDao? = null
    var userDao: UserDao? = null
    var animeDao: AnimeDao? = null

    fun update(list: List<Anime>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeTitleHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.anime_item, parent, false)
        this.mContext = parent.context
        userDao = AppDatabase.createDb(mContext).userDao()
        userAnimeDao = AppDatabase.createDb(mContext).userAnimeDao()
        animeDao = AppDatabase.createDb(mContext).animeDao()
        return AnimeTitleHolder(view)
    }

    override fun getItemCount(): Int = list.count()

    override fun onBindViewHolder(holder: AnimeTitleHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            fragmentJump(item)
        }
        val likeButton = holder.view.findViewById<Button>(R.id.heart_card_button)
        likeButton.setOnClickListener{
            GlobalScope.launch(Dispatchers.IO) {
                withContext(Dispatchers.IO) {
                    val user = userDao?.getActiveUser()
                    val anime = animeDao?.getAnimeById(item.mal_id)
                    if (user != null && anime != null) {
                        userAnimeDao?.insert(UserAnime(user.id, anime.id, "liked"))
                    }
                }
            }
        }
        val watchedButton = holder.view.findViewById<Button>(R.id.eye_card_button)
        watchedButton.setOnClickListener{
            GlobalScope.launch(Dispatchers.IO) {
                withContext(Dispatchers.IO) {
                    val user = userDao?.getActiveUser()
                    val anime = animeDao?.getAnimeById(item.mal_id)
                    if (user != null && anime != null) {
                        userAnimeDao?.insert(UserAnime(user.id, anime.id, "watched"))
                    }
                }
            }
        }
    }

    private fun fragmentJump(selectedAnime: Anime) {
        val newFragment = AnimeDetailFragment()

        if (mContext == null) return
        if (mContext is MainActivity) {
            val mainActivity = mContext as MainActivity
            mainActivity.animeSelected = selectedAnime
            mainActivity.switchContent(R.id.searchFragment, newFragment)
        }
    }

    class AnimeTitleHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(item: Anime) {
            view.setOnClickListener {

            }
            val image_view = view.findViewById<ImageView>(R.id.image_view)
            Glide
                .with(view)
                .load(item.image_url)
                .into(image_view);
            val title = view.findViewById<TextView>(R.id.title)
            title.setOnClickListener {  }
            view.findViewById<TextView>(R.id.title).text = item.title
            view.findViewById<TextView>(R.id.score).text = item.score
        }

    }
}