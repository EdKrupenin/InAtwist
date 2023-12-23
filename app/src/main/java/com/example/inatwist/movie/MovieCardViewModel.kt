package com.example.inatwist.movie

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inatwist.dataCashe.DataCache
import com.example.inatwist.movie.recyclerMovie.MovieDataModel
import com.example.inatwist.movie.recyclerMovie.MovieMainPresenter
import kotlinx.coroutines.launch

class MovieCardViewModel : ViewModel() {
    /** Controls for create new items and contain data */
    private val presenter by lazy { MovieMainPresenter() }
    /** List items on the screen */
    private val _data = MutableLiveData<List<MovieDataModel>>()
    val data: LiveData<List<MovieDataModel>> get() = _data

    init {
        getItem()
    }
    fun getItem() {
        viewModelScope.launch {
            _data.value = _data.value.orEmpty() + presenter.getItem(DataCache.pageOfCategory)
        }
    }

    fun clicked(movieId: Long) {
        val cache = data.value?.find { it.kinopoiskId == movieId }
        Log.d("MovieViewModel", "click $movieId cache = ${cache.toString()}")
    }

    fun swipeLeft(itemId: Long) {

    }

    fun swipeRight(itemId: Long) {

    }
}