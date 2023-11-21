package com.example.inatwist.movie

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.inatwist.movie.recyclerMovie.MovieDataModel
import com.example.inatwist.movie.recyclerMovie.MovieMainPresenter

class MovieCardViewModel : ViewModel() {
    /** Controls for create new items and contain data */
    private val presenter by lazy { MovieMainPresenter() }
    /** List items on the screen */
    private val _data = MutableLiveData<List<MovieDataModel>>()
    val data: LiveData<List<MovieDataModel>> get() = _data
    fun getItem(itemCount: Int = 0) {
        _data.postValue(_data.value.orEmpty() + presenter.getItem(itemCount))
    }

    fun clicked(categoriesId: Int) {
        val cache = data.value?.find { it.categoriesId == categoriesId }
        Log.d("MovieViewModel", "click $categoriesId cache = ${cache.toString()}")
    }
}