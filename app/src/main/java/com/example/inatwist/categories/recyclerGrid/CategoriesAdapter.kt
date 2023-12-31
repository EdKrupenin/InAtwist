package com.example.inatwist.categories.recyclerGrid

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.inatwist.R
import com.example.inatwist.movie.recyclerMovie.MovieDataModel

class CategoriesAdapter(private var onItemClick: (Int) -> Unit) :
    RecyclerView.Adapter<CategoriesViewHolder>() {
    private var items = mutableListOf<CategoriesDataModel>()

    fun setItems(newItems: List<CategoriesDataModel>) {
        items = newItems.toMutableList()
        notifyDataSetChanged()
    }

    fun addItems(newItems: List<CategoriesDataModel>) {
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

    fun getItem(position: Int): CategoriesDataModel {
        return items[position]
    }

    override fun getItemCount(): Int {
        return items.size
    }

    /** Inflates the item views which is designed in xml layout file
     * create a new
     * ViewHolder and initializes some private fields to be used by RecyclerView.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.card_of_category, parent, false)
        return CategoriesViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.bind(items[position], onItemClick)

    }
}