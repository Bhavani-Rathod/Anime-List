package com.bhavani.animelistapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bhavani.animelistapp.adapter.RvAdapter
import com.bhavani.animelistapp.databinding.ActivityMainBinding
import com.bhavani.animelistapp.models.Data
import com.bhavani.animelistapp.utils.RetrofitInstance
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var rvAdapter: RvAdapter
    private var animeList = ArrayList<Data>()

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        FirebaseFirestore.getInstance()



        //Listener for Favourites Button
        binding.favouritesBtn.setOnClickListener {
            val intent = Intent(this@MainActivity, SavedItemsActivity::class.java)
            startActivity(intent)
        }

        //Response from Api
        GlobalScope.launch(Dispatchers.IO) {
            val response = try {
                RetrofitInstance.api.getAnime()

            } catch (e: IOException) {
                Toast.makeText(applicationContext, "app error ${e.message}", Toast.LENGTH_SHORT)
                    .show()
                return@launch
            } catch (e: HttpException) {
                Toast.makeText(applicationContext, "http error ${e.message}", Toast.LENGTH_SHORT)
                    .show()
                return@launch
            }

            if (response.isSuccessful && response.body() != null) {
                withContext(Dispatchers.Main) {
                    val animeList: List<Data> = response.body()!!.data
                    binding.apply {
                        progressBar.visibility = View.GONE
                        rvAdapter = RvAdapter(animeList)
                        recyclerView.adapter = rvAdapter
                        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                    }

                    //SearchView
                    binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                        override fun onQueryTextSubmit(p0: String?): Boolean {
                            return false
                        }

                        override fun onQueryTextChange(newText: String?): Boolean {
                            if (newText != null) {
                                val filteredList = ArrayList<Data>()
                                for (i in animeList) {
                                    if (i.title?.lowercase(Locale.ROOT)?.contains(newText) == true) {
                                        filteredList.add(i)
                                    }
                                    else {
                                        rvAdapter.setFilteredList(filteredList)
                                    }
                                }
                            }
                            return true
                        }
                    })
                }
            }
        }
    }

}