package com.example.inatwist.dataCashe

import kotlin.properties.Delegates

/*import androidx.lifecycle.MutableLiveData
import java.util.Date
import javax.inject.Inject

data class DataCache(
    val category: Long = 0,
    val likedFilmsID: MutableList<Long>, // заменить на статичный обект с интерфейсом обновления
    val dislikedFilmsID: MutableList<Long>,
)
@ActivityRetainedScoped
class DataCacheStorage @Inject constructor() {
    val cache: MutableLiveData<DataCache> = MutableLiveData(DataCache())
}*/

object DataCache {
    var category by Delegates.notNull<Int>()
    var pageOfCategory = 1
    lateinit var likedFilmsID: MutableList<Long> // заменить на статичный обект с интерфейсом обновления
    lateinit var dislikedFilmsID: MutableList<Long>
}