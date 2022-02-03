package com.ishanvohra.projectsuper.ui.fragments.home

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ishanvohra.projectsuper.R
import com.ishanvohra.projectsuper.databinding.FragmentHomeBinding
import com.ishanvohra.projectsuper.models.SearchResult.SearchResultResponse
import com.ishanvohra.projectsuper.ui.adapters.SearchItemAdapter
import com.ishanvohra.projectsuper.util.Util
import java.math.BigInteger
import java.security.MessageDigest

class HomeFragment : Fragment(), SearchItemAdapter.SearchResultActionListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private var searchItemAdapter: SearchItemAdapter? = null

    private var viewModel = HomeViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater)

        //init view model
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        //attach adapter to recycler view
        attachAdapterToRecyclerView()

        //attach listener to edit text
        addQueryListener()

        return binding.root
    }

    private fun addQueryListener() {
        binding.editText.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                hideEmptyLayout()
            }

            override fun afterTextChanged(p0: Editable?) {
                if(p0.toString().isNotEmpty()){
                    val ts = Util.timeStamp()
                    viewModel.getSearchResult(
                        p0.toString(),
                        getString(R.string.public_key),
                        ts,
                        Util.md5(ts+getString(R.string.private_key)+getString(R.string.public_key))
                    )
                }
            }

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

        hideRecyclerView()
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

    private fun showRecyclerView(){
        binding.searchResultRecyclerView.animate()
            .setDuration(500)
            .alpha(1f)
            .start()
    }

    private fun hideRecyclerView(){
        binding.searchResultRecyclerView.animate()
            .setDuration(500)
            .alpha(0f)
            .start()
    }

    private fun attachAdapterToRecyclerView() {
        try{
            //init recycler view
            binding.searchResultRecyclerView.layoutManager = LinearLayoutManager(requireContext())

            searchItemAdapter = SearchItemAdapter(requireContext(), ArrayList(), this)
            binding.searchResultRecyclerView.adapter = searchItemAdapter

            //attaching an observer -> updating the value of dataSet in searchItemAdapter
            viewModel.searchResult.observe(viewLifecycleOwner){
                if(it != null){
                    if(!it.data.results.isNullOrEmpty()){
                        showRecyclerView()

                        searchItemAdapter?.dataSet?.clear()
                        searchItemAdapter?.dataSet?.addAll(it.data.results)
                        searchItemAdapter?.notifyDataSetChanged()
                    }
                }
            }
        }
        catch (e: Exception){
            e.printStackTrace()
        }
    }

    //override method from SearchResultActionListener in SearchItemAdapter
    override fun onSearchResultClicked(
        position: Int,
        itemView: View,
        item: SearchResultResponse.Result
    ) {
        findNavController().navigate(R.id.characterDetailsFragment)
    }
}