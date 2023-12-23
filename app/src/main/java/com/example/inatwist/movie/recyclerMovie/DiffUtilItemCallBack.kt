package com.example.inatwist.movie.recyclerMovie

import androidx.recyclerview.widget.DiffUtil

class DiffUtilItemCallBack : DiffUtil.ItemCallback<MovieDataModel>() {

    override fun areItemsTheSame(oldItem: MovieDataModel, newItem: MovieDataModel): Boolean {
        return oldItem.getItemId() == newItem.getItemId()
    }

    override fun areContentsTheSame(oldItem: MovieDataModel, newItem: MovieDataModel): Boolean {
        return oldItem == newItem
    }
}