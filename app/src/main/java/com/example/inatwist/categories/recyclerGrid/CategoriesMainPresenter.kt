package com.example.inatwist.categories.recyclerGrid

import android.util.Log
import com.example.inatwist.retrofit.KinopoiskApiUnofficial
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class CategoriesMainPresenter() {
    private val posterList = listOf(null, null, null, null, null, null)
    private val titleList = listOf("Support", "Drama", "Friend", "Work", "Comedy", "Action")

    private suspend fun getCategoriesFromApi(): List<CategoriesDataModel> {
        return withContext(Dispatchers.IO) {
            try {
                val response = KinopoiskApiUnofficial.categoriesApiService.getCategories()
                response.categories
            } catch (e: Exception) {
                Log.e("CategoriesMainPresenter", "Error fetching categories data", e)
                emptyList()
            }
        }
    }

    suspend fun getItem(): List<CategoriesDataModel> {
        return getCategoriesFromApi()
    }

    /*fun getItem(startPosition: Int = 0): List<CategoriesDataModel> {
        val mutableList = mutableListOf<CategoriesDataModel>()
        for (i in 1 .. 10) {
            val model = CategoriesDataModel(
                categoriesId = i,
                poster = R.drawable.poster0,
                title = titleList.random()
            )
            mutableList.add(model)
        }
        return mutableList
    }*/
}