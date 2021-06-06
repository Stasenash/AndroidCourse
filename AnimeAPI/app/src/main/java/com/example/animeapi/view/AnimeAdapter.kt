package com.example.animeapi.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.animeapi.R
import com.example.animeapi.model.Anime


class AnimeAdapter : RecyclerView.Adapter<AnimeAdapter.AnimeTitleHolder>() {
    var list = listOf<Anime>()
    lateinit var mContext : Context

    fun update(list: List<Anime>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeTitleHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.anime_item, parent, false)
        this.mContext = parent.context
        return AnimeTitleHolder(view)
    }

    override fun getItemCount(): Int = list.count()

    override fun onBindViewHolder(holder: AnimeTitleHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            fragmentJump(item)
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