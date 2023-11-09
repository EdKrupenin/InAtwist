package com.example.inatwist.categories

import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.inatwist.categories.recyclerGrid.CategoriesAdapter
import com.example.inatwist.categories.recyclerGrid.CategoriesMainPresenter
import com.example.inatwist.categories.recyclerGrid.PageScrollListener


class CategoriesViewModel : ViewModel() {
    private val adapter by lazy { CategoriesAdapter() }
    private val presenter by lazy { CategoriesMainPresenter() }

    fun createCategoriesGrid(recyclerView: RecyclerView, manager: GridLayoutManager) {

        recyclerView.layoutManager = manager
        recyclerView.adapter = adapter
        val paging = PageScrollListener(manager).apply {
            onLoadMore = {
                adapter.addItems(presenter.getItem(adapter.itemCount))
                isLoading = false
            }
        }
        recyclerView.addOnScrollListener(paging)
        adapter.setItems(presenter.getItem())
    }

}
