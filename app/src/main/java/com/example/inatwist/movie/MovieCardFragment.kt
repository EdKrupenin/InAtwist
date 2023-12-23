package com.example.inatwist.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.inatwist.R
import com.example.inatwist.categories.recyclerGrid.PageScrollListener
import com.example.inatwist.dataCashe.DataCache
import com.example.inatwist.movie.recyclerMovie.MovieAdapter

class MovieCardFragment : Fragment(R.layout.fragment_movie_card) {

    companion object {
        fun newInstance() = MovieCardFragment()
    }

    /** Controls loading data into the view model */
    private val moviesViewModelInstance: MovieCardViewModel by viewModels()

    /** Controls data and items into the recycler View */
    private val adapter by lazy {
        MovieAdapter()
    }

    /** Controls loading additional items into the recycler View */
    private val paging by lazy {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewMovie)
        val manager = object : LinearLayoutManager(context) {
            override fun canScrollVertically(): Boolean {
                return false
            }
        }
        recyclerView.layoutManager = manager
        recyclerView.adapter = adapter
        adapter.onItemActionClick = {
            TODO("Not Yet emplemented")
            //findNavController().navigate(R.id.movieCardFragment)
        }
        adapter.onItemActionSwipe = { position: Int, direction: Int ->
            when (direction) {
                ItemTouchHelper.LEFT -> {
                    moviesViewModelInstance.swipeLeft(adapter.getItemId(position))
                    adapter.removeItem(position)
                }
                ItemTouchHelper.RIGHT -> {
                    moviesViewModelInstance.swipeRight(adapter.getItemId(position))
                    adapter.removeItem(position)
                }
                //ItemTouchHelper.UP -> goToMovieDetails(adapter.getItemId(position))
                else -> print("x == 1")
            }
        }
        recyclerView.addOnScrollListener(
            PageScrollListener(manager).apply {
                onLoadMore = {
                    DataCache.pageOfCategory++
                    moviesViewModelInstance.getItem()
                    setLoaded()
                }
            }
        )
        ItemTouchHelper(ItemTouchCallback()).attachToRecyclerView(recyclerView)
        moviesViewModelInstance.data.value?.let { adapter.submitList(it) }
        moviesViewModelInstance.data.observe(viewLifecycleOwner) { data ->
            adapter.submitList(data)
        }
    }

    private fun goToMovieDetails(movieId: Long) {
        moviesViewModelInstance.clicked(movieId)
        findNavController().navigate(R.id.movieCardFragment)
    }

}