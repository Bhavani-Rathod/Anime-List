package com.bhavani.animelistapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.bhavani.animelistapp.R
import com.bhavani.animelistapp.SavedItemsActivity
import com.bhavani.animelistapp.databinding.RvItemsBinding
import com.bhavani.animelistapp.models.Data
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso

class RvAdapter(private val animeList:List<Data>): RecyclerView.Adapter<RvAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: RvItemsBinding): RecyclerView.ViewHolder(binding.root) {

        init {
            binding.readMoreBtn.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val currentItem = animeList[position]
                    showReadMoreDialog(binding.root.context, currentItem)
                }
            }

            binding.saveBtn.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val currentItem = animeList[position]
                    val intent =
                        Intent(binding.root.context, SavedItemsActivity::class.java).apply {
                            putExtra("SAVED_ITEM", currentItem)
                        }
                    binding.root.context.startActivity(intent)
                }
            }
        }

    }

    private fun showReadMoreDialog(context: Context, item: Data) {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_layout, null)
        val dialogTextView = dialogView.findViewById<TextView>(R.id.dialogTextView)
        dialogTextView.text = item.synopsis

        val builder = AlertDialog.Builder(context)
            .setView(dialogView)
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }

        val alertDialog = builder.create()
        alertDialog.show()
    }

    private fun saveItemToFirestore(context: Context, item: Data) {
        val db = FirebaseFirestore.getInstance()
        val collectionRef = db.collection("saved_anime_items") // Create or use your own collection name

        collectionRef.add(item)
            .addOnSuccessListener { documentReference ->
                Toast.makeText(
                    context,
                    "Item saved to Firestore with ID: ${documentReference.id}",
                    Toast.LENGTH_SHORT
                ).show()
            }
            .addOnFailureListener { e ->
                Toast.makeText(
                    context,
                    "Error saving item to Firestore: ${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(RvItemsBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = animeList[position]
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
        return animeList.size
    }

}