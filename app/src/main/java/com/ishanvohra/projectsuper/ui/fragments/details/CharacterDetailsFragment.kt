package com.ishanvohra.projectsuper.ui.fragments.details

import android.animation.ValueAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.ishanvohra.projectsuper.R
import com.ishanvohra.projectsuper.databinding.FragmentCharacterDetailsBinding
import com.ishanvohra.projectsuper.databinding.FragmentHomeBinding
import com.ishanvohra.projectsuper.ui.adapters.ComicCollectionAdapter
import com.ishanvohra.projectsuper.ui.adapters.SeriesCollectionAdapter
import com.ishanvohra.projectsuper.util.Util

class CharacterDetailsFragment : Fragment() {

    private var _binding: FragmentCharacterDetailsBinding? = null
    private val binding get() = _binding!!

    private var viewModel = CharacterDetailsViewModel()

    private var comicCollectionAdapter: ComicCollectionAdapter? = null
    private var seriesCollectionAdapter: SeriesCollectionAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCharacterDetailsBinding.inflate(inflater)

        //get character details
        val comicUrl = arguments?.getString("comic_collection_url")
        val seriesUrl = arguments?.getString("series_collection_url")
        val name = arguments?.getString("name")
        val imageUrl = arguments?.getString("image_url")

        //display character details
        if(name != null && imageUrl != null){
            renderCharacterDetails(name, imageUrl)
        }

        //init view model
        viewModel = ViewModelProvider(this).get(CharacterDetailsViewModel::class.java)

        //get details -> comics and series
        val ts = Util.timeStamp()

        if (comicUrl != null) {
            viewModel.getComicCollections(
                comicUrl,
                getString(R.string.public_key),
                ts,
                Util.md5(ts+getString(R.string.private_key)+getString(R.string.public_key))
            )
        }

        if(seriesUrl != null){
            viewModel.getSeriesCollections(
                seriesUrl,
                getString(R.string.public_key),
                ts,
                Util.md5(ts+getString(R.string.private_key)+getString(R.string.public_key))
            )
        }

        //init recycler view
        initRecyclerViews()

        //attach adapters to recycler views
        attachAdapters()

        //attach observers
        attachObservers()

        binding.comicLayout.setOnClickListener {
            if(binding.comicsRecyclerView.height > 0){
                collapseHeight(binding.comicsRecyclerView, 300, 0)
            }
            else{
                expandHeight(binding.comicsRecyclerView, 300, 1000)
            }
        }

        binding.seriesLayout.setOnClickListener {
            if(binding.seriesRecyclerView.height > 0){
                collapseHeight(binding.seriesRecyclerView, 300, 0)
            }
            else{
                expandHeight(binding.seriesRecyclerView, 300, 1000)
            }
        }

        return binding.root
    }

    private fun renderCharacterDetails(name: String, imageUrl: String) {
        binding.characterNameTextview.text = name

        Glide
            .with(requireContext())
            .load(imageUrl)
            .into(binding.thumbnail)
    }

    private fun attachObservers() {
        viewModel.comicResult.observe(viewLifecycleOwner){
            if(it != null){
                if(!it.data.results.isNullOrEmpty()){
                    comicCollectionAdapter?.dataSet?.clear()
                    comicCollectionAdapter?.dataSet?.addAll(it.data.results)
                    comicCollectionAdapter?.notifyDataSetChanged()
                }
            }
        }

        viewModel.seriesResult.observe(viewLifecycleOwner){
            if(it != null){
                if(!it.data.results.isNullOrEmpty()){
                    seriesCollectionAdapter?.dataSet?.clear()
                    seriesCollectionAdapter?.dataSet?.addAll(it.data.results)
                    seriesCollectionAdapter?.notifyDataSetChanged()
                }
            }
        }
    }

    private fun attachAdapters() {
        comicCollectionAdapter = ComicCollectionAdapter(requireContext(), ArrayList())
        binding.comicsRecyclerView.adapter = comicCollectionAdapter

        seriesCollectionAdapter = SeriesCollectionAdapter(requireContext(), ArrayList())
        binding.seriesRecyclerView.adapter = seriesCollectionAdapter
    }

    private fun initRecyclerViews() {
        binding.comicsRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.seriesRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }

    private fun expandHeight(v: View, duration: Int, targetHeight: Int) {
        val prevWidth = v.height
        v.visibility = View.VISIBLE
        val valueAnimator = ValueAnimator.ofInt(prevWidth, targetHeight)
        valueAnimator.addUpdateListener { animation ->
            v.layoutParams.height = animation.animatedValue as Int
            v.requestLayout()
        }
        valueAnimator.interpolator = DecelerateInterpolator()
        valueAnimator.duration = duration.toLong()
        valueAnimator.start()
    }

    private fun collapseHeight(v: View, duration: Int, targetHeight: Int) {
        val prevWidth = v.height
        val valueAnimator = ValueAnimator.ofInt(prevWidth, targetHeight)
        valueAnimator.interpolator = DecelerateInterpolator()
        valueAnimator.addUpdateListener { animation ->
            v.layoutParams.height = animation.animatedValue as Int
            v.requestLayout()
        }
        valueAnimator.interpolator = DecelerateInterpolator()
        valueAnimator.duration = duration.toLong()
        valueAnimator.start()
    }


}