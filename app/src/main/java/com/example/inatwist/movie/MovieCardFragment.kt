package com.example.inatwist.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.motion.widget.OnSwipe
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.inatwist.R
import com.example.inatwist.categories.recyclerGrid.CategoriesAdapter
import com.example.inatwist.categories.recyclerGrid.PageScrollListener
import com.example.inatwist.movie.recyclerMovie.MovieAdapter

class MovieCardFragment : Fragment() {

    companion object {
        fun newInstance() = MovieCardFragment()
    }

    /** Controls loading data into the view model */
    private val moviesViewModelInstance: MovieCardViewModel by viewModels()

    /** Controls data and items into the recycler View */
    private val adapter by lazy {
        MovieAdapter()
        { categoriesId ->
            goToNextScreen(categoriesId)
            // Handle the click event for the item at the specified position
        }
    }

    /** Controls loading additional items into the recycler View */
    private val paging by lazy {

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_card, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewMovie)
        val manager = LinearLayoutManager(context)
        recyclerView.layoutManager = manager
        recyclerView.adapter = adapter
        recyclerView.addOnScrollListener(
            PageScrollListener(manager).apply {
                onLoadMore = {
                    moviesViewModelInstance.getItem(adapter.itemCount)
                    setLoaded()
                }
            }
        )
        // Callback for swiped cards
        ItemTouchHelper(object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder,
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                adapter.removeItem(viewHolder.adapterPosition)
            }
        }).attachToRecyclerView(recyclerView)
        moviesViewModelInstance.getItem()
        moviesViewModelInstance.data.value?.let { adapter.setItems(it) }
        moviesViewModelInstance.data.observe(viewLifecycleOwner) { data ->
            adapter.setItems(data)
        }
    }

    private fun goToNextScreen(categoriesId: Int) {
        moviesViewModelInstance.clicked(categoriesId)
        findNavController().navigate(R.id.movieCardFragment)
    }

}