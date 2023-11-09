package com.example.inatwist.categories.recyclerGrid

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.inatwist.R
import java.io.Serializable

data class CategoriesModel(val categoriesId: Int, val title: String, val poster: Int) : Serializable

class CategoriesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(model: CategoriesModel) {
        itemView.findViewById<ImageView>(R.id.card_of_category_poster)
            .setImageResource(model.poster)

        itemView.findViewById<TextView>(R.id.card_of_category_title).apply {
            text = model.title
        }
    }
}