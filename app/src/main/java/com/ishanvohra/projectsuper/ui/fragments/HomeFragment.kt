package com.ishanvohra.projectsuper.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ishanvohra.projectsuper.databinding.FragmentHomeBinding
import com.ishanvohra.projectsuper.databinding.FragmentSplashBinding
import com.ishanvohra.projectsuper.ui.adapters.SearchItemAdapter

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private var searchItemAdapter: SearchItemAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater)

        //attach adapter to recycler view
        attachAdapterToRecyclerView()

        //attach listener to search view
        addQueryListener()

        return binding.root
    }

    private fun addQueryListener() {
        binding.searchView.setOnSearchClickListener {
            //hide place holder image and texts
            hideEmptyLayout()
        }

        binding.searchView.setOnCloseListener(SearchView.OnCloseListener {
            //show empty layout
            showEmptyLayout()
            false
        })
    }

    private fun showEmptyLayout() {
        binding.titleTextview.animate()
            .setDuration(500)
            .translationYBy(-500f)
            .alpha(1f)
            .start()

        binding.superHeroImageView.animate()
            .setDuration(500)
            .translationYBy(-500f)
            .alpha(1f)
            .start()
    }

    private fun hideEmptyLayout() {
        binding.titleTextview.animate()
            .setDuration(500)
            .translationYBy(500f)
            .alpha(0f)
            .start()

        binding.superHeroImageView.animate()
            .setDuration(500)
            .translationYBy(500f)
            .alpha(0f)
            .start()
    }

    private fun attachAdapterToRecyclerView() {
        try{
            //init recycler view
            binding.searchResultRecyclerView.layoutManager = LinearLayoutManager(requireContext())

            searchItemAdapter = SearchItemAdapter(requireContext())
            binding.searchResultRecyclerView.adapter = searchItemAdapter
        }
        catch (e: Exception){
            e.printStackTrace()
        }
    }
}