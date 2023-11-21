package com.example.inatwist.categories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.inatwist.categories.recyclerGrid.CategoriesMainPresenter
import com.example.inatwist.categories.recyclerGrid.CategoriesDataModel


class CategoriesViewModel : ViewModel() {
    /** Controls for create new items and contain data */
    private val presenter by lazy { CategoriesMainPresenter() }
    /** List items on the screen */
    private val _data = MutableLiveData<List<CategoriesDataModel>>()
    val data: LiveData<List<CategoriesDataModel>> get() = _data
    fun getItem(itemCount: Int = 0) {
        _data.postValue(_data.value.orEmpty() + presenter.getItem(itemCount))
    }

    fun clicked(categoriesId: Int) {
        var cashe = data.value?.find { it.categoriesId == categoriesId }
        Log.d("VIEWMODEL", "click $categoriesId cashe = ${cashe.toString()}")
    }

}
