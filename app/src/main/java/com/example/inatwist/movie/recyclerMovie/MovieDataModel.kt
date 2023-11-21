package com.example.inatwist.movie.recyclerMovie

import java.io.Serializable

data class MovieDataModel(
    val categoriesId: Int,
    val title: String,
    val poster: Int,
    val descripon: String,
    val movieId: Int,
) :
    Serializable