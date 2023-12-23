package com.example.inatwist.categories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.inatwist.R
import com.example.inatwist.categories.recyclerGrid.CategoriesAdapter
import com.example.inatwist.categories.recyclerGrid.PageScrollListener

class CategoriesFragment : Fragment(R.layout.fragment_categories) {

    companion object {
        fun newInstance() = CategoriesFragment()
    }

    /** Controls loading data into the view model */
    private val categoriesViewModelInstance: CategoriesViewModel by viewModels()

    /** Controls data and items into the recycler View */
    private val adapter by lazy {
        CategoriesAdapter()
        { categoriesId ->
            goToNextScreen(categoriesId)
            // Handle the click event for the item at the specified position
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        val manager = GridLayoutManager(context, 2)
        recyclerView.layoutManager = manager
        recyclerView.adapter = adapter
        categoriesViewModelInstance.data.value?.let { adapter.setItems(it) }
        categoriesViewModelInstance.data.observe(viewLifecycleOwner) { data ->
            adapter.setItems(data)
        }
    }

    private fun goToNextScreen(categoriesId: Int) {
        categoriesViewModelInstance.clicked(categoriesId)
        findNavController().navigate(R.id.movieCardFragment)
    }
}