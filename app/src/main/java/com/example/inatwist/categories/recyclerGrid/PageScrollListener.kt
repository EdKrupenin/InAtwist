package com.example.inatwist.categories.recyclerGrid

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PageScrollListener(private val layoutManager: LinearLayoutManager) :
    RecyclerView.OnScrollListener() {
    private var isLoading = false
    var onLoadMore: (() -> Unit)? = null

    fun setLoaded() {
        isLoading = false
    }

    fun getLoaded(): Boolean {
        return isLoading
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val totalItemCount = layoutManager.itemCount
        val visibleItemCount = layoutManager.childCount
        val firstVisiblePosition = layoutManager.findFirstVisibleItemPosition()

        if (!isLoading) {
            if ((visibleItemCount + firstVisiblePosition) >= totalItemCount) {
                isLoading = true
                onLoadMore?.invoke()
            }
        }
    }
}