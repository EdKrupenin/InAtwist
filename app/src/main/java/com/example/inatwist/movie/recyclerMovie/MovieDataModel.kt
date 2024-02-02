package com.example.inatwist.movie.recyclerMovie

import java.io.Serializable

sealed interface MovieItem {
    fun getItemId() : Long
}
data class MovieDataModel(
    val kinopoiskId: Long,
    val imdbId: String,
    val nameRu: String,
    val nameEn: String,
    val nameOriginal: String,
    val ratingKinopoisk: Float,
    val ratingImdb: Float,
    val year: Int,
    val type: String,
    val posterUrl: String,
    val posterUrlPreview: String
) :  Serializable, MovieItem {
    override fun getItemId() = kinopoiskId
}