package com.example.inatwist.categories

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.inatwist.R

class CategoriesFragment : Fragment(R.layout.fragment_categories) {

    companion object {
        fun newInstance() = CategoriesFragment()
    }

    private val categoriesViewModelInstance: CategoriesViewModel by viewModels()
    private val manager by lazy { GridLayoutManager(context, 2) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_categories, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        categoriesViewModelInstance.createCategoriesGrid(recyclerView, manager)
    }
}