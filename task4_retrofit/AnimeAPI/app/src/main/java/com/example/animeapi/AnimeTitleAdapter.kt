package com.example.animeapi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.animeapi.models.AnimeTitle

class AnimeTitleAdapter : RecyclerView.Adapter<AnimeTitleAdapter.AnimeTitleHolder>() {
    var list = listOf<AnimeTitle>()

    fun update(list: List<AnimeTitle>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeTitleHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.anime_item, parent, false)
        return AnimeTitleHolder(view)
    }

    override fun getItemCount(): Int = list.count()

    override fun onBindViewHolder(holder: AnimeTitleHolder, position: Int) {
        holder.bind(list[position])
    }

    class AnimeTitleHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(item: AnimeTitle) {
            view.setOnClickListener {

            }
            val title = view.findViewById<TextView>(R.id.title)
            title.setOnClickListener {  }
            view.findViewById<TextView>(R.id.title).text = item.title
            view.findViewById<TextView>(R.id.synopsis).text = item.synopsis
        }

    }
}