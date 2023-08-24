package com.bhavani.animelistapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bhavani.animelistapp.databinding.ActivitySavedItemsBinding
import com.bhavani.animelistapp.databinding.RvItemsBinding
import com.bhavani.animelistapp.databinding.SavedItemsLayoutBinding
import com.bhavani.animelistapp.models.Data
import com.squareup.picasso.Picasso

class SavedItemsAdapter(private val savedItemsList: List<Data>) : RecyclerView.Adapter<SavedItemsAdapter.ViewHolder>(){

    inner class ViewHolder(val binding: SavedItemsLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        // Define your views here
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = SavedItemsLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val currentItem = savedItemsList[position]
        holder.binding.apply {
            tvTitle.text = currentItem.title
            tvRating.text = currentItem.rating
            tvEpisodes.text = currentItem.episodes.toString()
            tvYear.text = currentItem.year.toString()
            tvStatus.text = currentItem.status
            Picasso.get().load(currentItem.images.jpg.image_url).into(imageView)

        }

    }

    override fun getItemCount(): Int {
        return savedItemsList.size
    }
}