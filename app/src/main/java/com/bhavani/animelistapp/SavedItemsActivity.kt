package com.bhavani.animelistapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bhavani.animelistapp.adapter.SavedItemsAdapter
import com.bhavani.animelistapp.databinding.ActivitySavedItemsBinding
import com.bhavani.animelistapp.models.Data
import com.google.firebase.firestore.FirebaseFirestore

class SavedItemsActivity : AppCompatActivity() {

    private lateinit var firestore: FirebaseFirestore
    private lateinit var binding: ActivitySavedItemsBinding
    private lateinit var savedItemsAdapter: SavedItemsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySavedItemsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firestore = FirebaseFirestore.getInstance()
        val collectionRef = firestore.collection("saved_anime_items") // Same collection name

        val savedItemsList = mutableListOf<Data>() // Data class or appropriate type
        savedItemsAdapter = SavedItemsAdapter(savedItemsList)

        binding.savedRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.savedRecyclerView.adapter = savedItemsAdapter


        collectionRef.get()
            .addOnSuccessListener { querySnapshot ->
                savedItemsList.clear() // Clear the list before adding items

                for (documentSnapshot in querySnapshot.documents) {
                    val savedItem = documentSnapshot.toObject(Data::class.java)
                    savedItem?.let {
                        // Check if the item already exists in the list
                        if (!savedItemsList.contains(savedItem)) {
                            savedItemsList.add(savedItem)
                        }
                    }
                }
                savedItemsAdapter.notifyDataSetChanged()
            }
            .addOnFailureListener {
                // Handle failure
            }

        val savedItem = intent.getParcelableExtra<Data>("SAVED_ITEM")
        savedItem?.let { savedData ->
            val db = FirebaseFirestore.getInstance()
            val collectionRef = db.collection("saved_anime_items") // Same collection name

            // Check if the savedData doesn't already exist in the collection
            collectionRef.whereEqualTo("mal_id", savedData.mal_id).get()
                .addOnSuccessListener { querySnapshot ->
                    if (querySnapshot.isEmpty) {
                        // If the document doesn't exist, add it to the collection
                        collectionRef.add(savedData)

                    } else {
                        // The document already exists in the collection
                        Toast.makeText(
                            applicationContext,
                            "Item already exists",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                .addOnFailureListener { e ->
                    Toast.makeText(
                        applicationContext,
                        "Error checking document in",
                        Toast.LENGTH_SHORT
                    ).show()
                }
        }
    }
}