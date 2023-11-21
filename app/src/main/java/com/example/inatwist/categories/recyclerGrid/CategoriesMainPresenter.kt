package com.example.inatwist.categories.recyclerGrid

import com.example.inatwist.R

class CategoriesMainPresenter {
    private val posterList = listOf(null,null,null,null,null,null)
    private val titleList = listOf("Support","Drama","Friend","Work","Comedy","Action")

    fun getItem(startPosition: Int = 0): List<CategoriesDataModel> {
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
    }
}