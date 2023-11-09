package com.example.inatwist.categories.recyclerGrid

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.inatwist.R

class CategoriesAdapter : RecyclerView.Adapter<CategoriesViewHolder>() {
    private var items = mutableListOf<CategoriesModel>()

    fun setItems(newItems: List<CategoriesModel>) {
        items = newItems.toMutableList()
        notifyDataSetChanged()
    }

    fun addItems(newItems: List<CategoriesModel>) {
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

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_of_category, parent, false)
        return CategoriesViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.bind(items[position])
    }
}