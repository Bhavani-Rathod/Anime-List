package com.bhavani.animelistapp.data

import com.bhavani.animelistapp.models.AllAnimeData
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET("v4/anime")
    suspend fun getAnime():Response<AllAnimeData>
}