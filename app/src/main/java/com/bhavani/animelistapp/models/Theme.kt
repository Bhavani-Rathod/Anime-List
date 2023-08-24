package com.bhavani.animelistapp.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Theme(
    val mal_id: Int?,
    val name: String?,
    val type: String?,
    val url: String?
) : Parcelable {
    // Add a no-argument constructor
    constructor() : this(0,"", "", "")
}