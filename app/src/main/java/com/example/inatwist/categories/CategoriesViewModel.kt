package com.example.inatwist.categories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.inatwist.categories.recyclerGrid.CategoriesMainPresenter
import com.example.inatwist.categories.recyclerGrid.CategoriesModel


class CategoriesViewModel : ViewModel() {
    private val presenter by lazy { CategoriesMainPresenter() }
    private val _data = MutableLiveData<List<CategoriesModel>>()
    val data: LiveData<List<CategoriesModel>> get() = _data
    fun getItem(itemCount: Int = 0) {
        _data.postValue(presenter.getItem(itemCount))

    }

    fun createCategoriesGrid(recyclerView: RecyclerView, manager: GridLayoutManager) {

    }

}
