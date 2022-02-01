package com.ishanvohra.projectsuper.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ishanvohra.projectsuper.databinding.ItemSuperHeroSearchBinding

class SearchItemAdapter(
    val context: Context,
) : RecyclerView.Adapter<SearchItemAdapter.MyViewHolder>() {

    inner class MyViewHolder(binding: ItemSuperHeroSearchBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemSuperHeroSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 3
    }
}