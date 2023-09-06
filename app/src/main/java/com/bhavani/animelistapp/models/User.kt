package com.bhavani.animelistapp.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val url: String,
    val username: String
) : Parcelable {
    constructor() : this("","")
}