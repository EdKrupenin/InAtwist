package com.example.inatwist.movie

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.inatwist.movie.recyclerMovie.MovieAdapter

class ItemTouchCallback : ItemTouchHelper.Callback() {
    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
    ): Int {
        return makeMovementFlags(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT /*or ItemTouchHelper.UP*/
        )
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder,
    ): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        viewHolder.movieAdapter.onItemActionSwipe(viewHolder.absoluteAdapterPosition, direction)
    }

    private val RecyclerView.ViewHolder.movieAdapter: MovieAdapter
        get() = bindingAdapter as? MovieAdapter ?: error("Not MovieAdapter")

}