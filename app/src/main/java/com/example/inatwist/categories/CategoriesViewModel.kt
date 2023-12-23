package com.example.inatwist.categories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inatwist.categories.recyclerGrid.CategoriesMainPresenter
import com.example.inatwist.categories.recyclerGrid.CategoriesDataModel
import com.example.inatwist.dataCashe.DataCache
import kotlinx.coroutines.launch


class CategoriesViewModel : ViewModel() {
    /** Controls for create new items and contain data */
    private val presenter by lazy { CategoriesMainPresenter() }

    /** List items on the screen */
    private val _data = MutableLiveData<List<CategoriesDataModel>>()
    val data: LiveData<List<CategoriesDataModel>> get() = _data

    init {
        getItem()
    }

    private fun getItem() {
        viewModelScope.launch {
            _data.value = presenter.getItem()
        }
    }

    fun clicked(categoriesId: Int) {
        val cache = data.value?.find { it.id == categoriesId }
        Log.d("CategoriesViewModel", "click $categoriesId cache = ${cache.toString()}")
        if (cache != null) {
            DataCache.category = cache.id
        }
    }

}
