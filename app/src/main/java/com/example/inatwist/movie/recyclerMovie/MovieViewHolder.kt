package com.example.inatwist.movie.recyclerMovie

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.inatwist.R
import com.squareup.picasso.Picasso


class MovieViewHolder(
    view: View,
    private val onItemActionSwiped: (Int) -> Unit,
) : RecyclerView.ViewHolder(view) {
    fun bind(model: MovieDataModel) {
        Picasso.get()
            .load(model.posterUrl)
            .into(itemView.findViewById<ImageView>(R.id.movie_poster))
        itemView.setOnClickListener{
            onItemActionSwiped.invoke(absoluteAdapterPosition)
        }
        itemView.findViewById<TextView>(R.id.movie_title).apply {
            text = model.nameRu
        }
        itemView.findViewById<TextView>(R.id.movie_IMBd_rating).apply {
            text = model.ratingImdb.toString()
        }
    }
}