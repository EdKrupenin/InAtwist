package com.example.inatwist.movie.recyclerMovie

import com.example.inatwist.R

class MovieMainPresenter {
    private val posterList = listOf(null,null,null,null,null,null)
    private val titleList = listOf("Support","Drama","Friend","Work","Comedy","Action")

    fun getItem(startPosition: Int = 0): List<MovieDataModel> {
        val mutableList = mutableListOf<MovieDataModel>()
        for (i in 1 .. 10) {
            val model = MovieDataModel(
                categoriesId = i,
                poster = R.drawable.poster0,
                title = titleList.random(),
                descripon = titleList.random(),
                movieId = i*2
            )
            mutableList.add(model)
        }
        return mutableList
    }
}