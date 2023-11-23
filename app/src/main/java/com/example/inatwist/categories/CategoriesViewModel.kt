package com.example.inatwist.categories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inatwist.categories.recyclerGrid.CategoriesMainPresenter
import com.example.inatwist.categories.recyclerGrid.CategoriesDataModel
import kotlinx.coroutines.launch



class CategoriesViewModel : ViewModel() {
    /** Controls for create new items and contain data */
    private val presenter by lazy { CategoriesMainPresenter() }
    /** List items on the screen */
    private val _data = MutableLiveData<List<CategoriesDataModel>>()
    val data: LiveData<List<CategoriesDataModel>> get() = _data



    fun getItem(itemCount: Int = 0) {
        viewModelScope.launch {
            _data.postValue(_data.value.orEmpty() + presenter.getItem(itemCount))
        }
        //_data.postValue(_data.value.orEmpty() + presenter.getItem(itemCount))
    }

    fun clicked(categoriesId: Int) {
        var cashe = data.value?.find { it.id == categoriesId }
        Log.d("VIEWMODEL", "click $categoriesId cashe = ${cashe.toString()}")
    }

}
