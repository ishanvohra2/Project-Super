package com.ishanvohra.projectsuper.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ishanvohra.projectsuper.databinding.ItemComicBookBinding
import com.ishanvohra.projectsuper.models.SeriesCollection.SeriesCollectionResponse

class SeriesCollectionAdapter(
    private val context: Context,
    val dataSet: ArrayList<SeriesCollectionResponse.Result>
    ):RecyclerView.Adapter<SeriesCollectionAdapter.MyViewHolder>() {

    inner class MyViewHolder(val binding: ItemComicBookBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemComicBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        with(holder){
            with(dataSet[position]){
                Glide.with(context)
                    .load(this.thumbnail.path + "/portrait_xlarge." + this.thumbnail.extension)
                    .into(binding.profilePicImageView)

                binding.nameTextview.text = this.title
            }
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }


}