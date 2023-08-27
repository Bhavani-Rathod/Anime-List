package com.bhavani.animelistapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bhavani.animelistapp.R
import com.bhavani.animelistapp.databinding.SavedItemsLayoutBinding
import com.bhavani.animelistapp.models.Data
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso

class SavedItemsAdapter(private val savedItemsList: List<Data>) : RecyclerView.Adapter<SavedItemsAdapter.ViewHolder>(){

    inner class ViewHolder(val binding: SavedItemsLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(savedItem: Data) {
            binding.apply {

                //Long click listener to show delete popup menu
                itemView.setOnLongClickListener {
                    showDeletePopupMenu(itemView.context, binding, savedItem)
                    true
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedItemsAdapter.ViewHolder {

        return ViewHolder(SavedItemsLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    }

    private fun showDeletePopupMenu(context: Context, binding: SavedItemsLayoutBinding, savedItem: Data) {
        val popupMenu = PopupMenu(context, binding.root)
        popupMenu.menuInflater.inflate(R.menu.popup_menu, popupMenu.menu)

        popupMenu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.menu_delete -> {
                    deleteItemFromFirestore(context, savedItem)
                    return@setOnMenuItemClickListener true
                }
                else -> false
            }
        }

        popupMenu.show()
    }


    private fun deleteItemFromFirestore(context: Context, savedItem: Data) {
        val db = FirebaseFirestore.getInstance()
        val collectionRef = db.collection("saved_anime_items")

        collectionRef.whereEqualTo("mal_id", savedItem.mal_id)
            .get()
            .addOnSuccessListener { querySnapshot ->
                for (documentSnapshot in querySnapshot.documents) {
                    documentSnapshot.reference.delete()
                }
                Toast.makeText(context, "Item deleted", Toast.LENGTH_SHORT).show()

            }
            .addOnFailureListener {
                Toast.makeText(
                    context,
                    "Error deleting item",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }


    override fun onBindViewHolder(holder: SavedItemsAdapter.ViewHolder, position: Int) {

        val currentItem = savedItemsList[position]
        holder.bind(currentItem)
        holder.binding.apply {
            tvTitle.text = currentItem.title
            tvRating.text = currentItem.rating
            tvEpisodes.text = currentItem.episodes.toString()
            if (currentItem.episodes==null){
                tvEpisodes.text = 12.toString()
            }

            tvYear.text = currentItem.year.toString()
            if (currentItem.year==null){
                tvYear.text = 2000.toString()
            }
            tvStatus.text = currentItem.status
            Picasso.get().load(currentItem.images.jpg.image_url).into(imageView)

        }

    }

    override fun getItemCount(): Int {
        return savedItemsList.size
    }
}