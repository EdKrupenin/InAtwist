package com.example.inatwist.movie.recyclerMovie

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.inatwist.R


class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(model: MovieDataModel, listener: (Int) -> Unit) {
        /*itemView.findViewById<ImageView>(R.id.card_of_movie_poster)
            .setImageResource(model.poster)*/

        itemView.findViewById<TextView>(R.id.card_of_movie_title).apply {
            text = model.title
        }

        itemView.findViewById<TextView>(R.id.card_of_movie_discriptions).apply {
            text = model.descripon
        }
    }
}