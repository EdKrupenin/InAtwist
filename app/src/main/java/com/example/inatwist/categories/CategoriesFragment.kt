package com.example.inatwist.categories

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.inatwist.R
import com.example.inatwist.categories.recyclerGrid.CategoriesAdapter
import com.example.inatwist.categories.recyclerGrid.PageScrollListener

class CategoriesFragment : Fragment(R.layout.fragment_categories) {

    companion object {
        fun newInstance() = CategoriesFragment()
    }

    private val categoriesViewModelInstance: CategoriesViewModel by viewModels()
    private val manager by lazy { GridLayoutManager(context, 2) }
    private val adapter by lazy { CategoriesAdapter() }
    private val paging by lazy {
        PageScrollListener(manager).apply {
            onLoadMore = {
                categoriesViewModelInstance.getItem(adapter.itemCount)
                // categoriesViewModelInstance.data.observe(viewLifecycleOwner) { data ->
                //     adapter.setItems(data)
                //     Log.d("paging", adapter.itemCount.toString());
                // }
                //setLoaded()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_categories, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = manager
        recyclerView.adapter = adapter
        recyclerView.addOnScrollListener(paging)
        categoriesViewModelInstance.getItem()
        categoriesViewModelInstance.data.value?.let { adapter.setItems(it) }
        categoriesViewModelInstance.data.observe(viewLifecycleOwner) { data ->
            adapter.setItems(data)
            Log.d("observe", "first call");
            //paging.setLoaded()
        }
    }
}