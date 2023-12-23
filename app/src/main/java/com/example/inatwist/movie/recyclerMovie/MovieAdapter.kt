package com.example.inatwist.movie.recyclerMovie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.inatwist.R

class MovieAdapter : ListAdapter<MovieDataModel, MovieViewHolder>(DiffUtilItemCallBack()) {
    var onItemActionClick: (Int) -> Unit = {}
    var onItemActionSwipe: (position: Int, direction: Int) -> Unit = { _: Int, _: Int -> }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.card_of_movie, parent, false)
        return MovieViewHolder(view, onItemActionClick)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun removeItem(position: Int) {
        val newList = currentList.toMutableList().apply {
            removeAt(position)
        }
        submitList(newList)
    }

    /*fun setItems(newItems: List<MovieDataModel>) {
            items = newItems.toMutableList()
            notifyDataSetChanged()
        }

        fun addItems(newItems: List<MovieDataModel>) {
            val size = items.size
            items.addAll(newItems)
            notifyItemInserted(size)
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
        }*/
}