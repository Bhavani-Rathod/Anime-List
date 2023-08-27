package com.bhavani.animelistapp.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Producer(
    val mal_id: Int?,
    val name: String?,
    val type: String?,
    val url: String?
) : Parcelable {
    // a no-argument constructor
    constructor() : this(0, "", "", "")
}