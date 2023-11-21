package com.example.inatwist.categories.recyclerGrid

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.inatwist.R

class CategoriesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(model: CategoriesDataModel, listener: (Int) -> Unit) {
        itemView.findViewById<ImageView>(R.id.card_of_category_poster)
            .setImageResource(model.poster)

        itemView.findViewById<TextView>(R.id.card_of_category_title).apply {
            text = model.title
        }

        itemView.setOnClickListener { listener(model.categoriesId) }
    }
}