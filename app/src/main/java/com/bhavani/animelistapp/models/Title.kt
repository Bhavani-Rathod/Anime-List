package com.bhavani.animelistapp.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Title(
    val title: String,
    val type: String
) : Parcelable {
    // Add a no-argument constructor
    constructor() : this("", "")
}