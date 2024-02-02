package com.example.inatwist.movie.recyclerMovie

import android.util.Log
import com.example.inatwist.categories.recyclerGrid.CategoriesDataModel
import com.example.inatwist.dataCashe.DataCache
import com.example.inatwist.retrofit.KinopoiskApiUnofficial
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.http.Query

class MovieMainPresenter {
    // https://kinopoiskapiunofficial.tech/api/v2.2/films?genres=3&order=RATING&type=ALL&ratingFrom=0&ratingTo=10&yearFrom=1000&yearTo=3000&page=1
    private suspend fun getCategoriesFromApi(paging: Int): List<MovieDataModel> {
        return withContext(Dispatchers.IO) {
            try {
                val response = KinopoiskApiUnofficial.movieApiService.getMovies(
                    DataCache.category,
                    //"RATING",
                    "FILM",
                    //0,
                    //10,
                   // 1000,
                   // 3000,
                    paging
                )
                response.movie
            } catch (e: Exception) {
                Log.e("CategoriesMainPresenter", "Error fetching categories data", e)
                emptyList()
            }
        }
    }

    suspend fun getItem(paging: Int): List<MovieDataModel> {
        return getCategoriesFromApi(paging)
    }
    /*fun getItem(startPosition: Int = 0): List<MovieDataModel> {
        val mutableList = mutableListOf<MovieDataModel>()
        for (i in 1 .. 10) {
            val model = MovieDataModel(
                categoriesId = i,
                poster = R.drawable.poster0,
                title = titleList.random(),
                description = titleList.random(),
                movieId = (i*2).toLong()
            )
            mutableList.add(model)
        }
        return mutableList
    }*/
}