package com.bhavani.animelistapp.utils

import com.bhavani.animelistapp.data.ApiInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    val api: ApiInterface by lazy {
        Retrofit.Builder()
            .baseUrl(Util.base)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)
    }
}