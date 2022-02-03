package com.ishanvohra.projectsuper.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ishanvohra.projectsuper.databinding.ItemSuperHeroSearchBinding
import com.ishanvohra.projectsuper.models.SearchResult.SearchResultResponse

class SearchItemAdapter(
    private val context: Context,
    val dataSet: ArrayList<SearchResultResponse.Result>,
    private val listener: SearchResultActionListener
) : RecyclerView.Adapter<SearchItemAdapter.MyViewHolder>() {

    interface SearchResultActionListener{
        fun onSearchResultClicked(position: Int, itemView: View, item: SearchResultResponse.Result)
    }

    inner class MyViewHolder(val binding: ItemSuperHeroSearchBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemSuperHeroSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        with(holder){
            with(dataSet[position]){
                binding.nameTextview.text = this.name

                Glide.with(context)
                    .load(this.thumbnail.path + "/portrait_xlarge." + this.thumbnail.extension)
                    .into(binding.profilePicImageView)

                if(this.comics != null){
                    binding.comicsTextview.text = this.comics.items.size.toString()
                }
                else
                    binding.comicsTextview.visibility = View.GONE

                if(this.stories != null){
                    binding.storiesTextview.text = this.stories.items.size.toString()
                }
                else
                    binding.storiesTextview.visibility = View.GONE

                if(this.series != null){
                    binding.seriesTextview.text = this.series.items.size.toString()
                }
                else
                    binding.seriesTextview.visibility = View.GONE

                binding.root.setOnClickListener {
                    listener.onSearchResultClicked(position, binding.root, this)
                }

            }
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}