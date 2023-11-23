package com.example.inatwist.movie.recyclerMovie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.inatwist.R

class MovieAdapter(private var onItemClick: (Int) -> Unit) :
    RecyclerView.Adapter<MovieViewHolder>() {
    private var items = mutableListOf<MovieDataModel>()

    fun setItems(newItems: List<MovieDataModel>) {
        items = newItems.toMutableList()
        notifyDataSetChanged()
    }

    fun addItems(newItems: List<MovieDataModel>) {
        val size = items.size
        items.addAll(newItems)
        notifyItemInserted(size)
    }

    fun removeItem(position: Int) {
        val newItems = items.toMutableList()
        newItems.removeAt(position)
        items = newItems
        notifyItemRemoved(position)
    }

    fun getItem(position: Int): MovieDataModel {
        return items[position]
    }

    override fun getItemCount(): Int {
        return items.size
    }

    /** Inflates the item views which is designed in xml layout file
     * create a new
     * ViewHolder and initializes some private fields to be used by RecyclerView.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.card_of_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(items[position], onItemClick)
    }
}