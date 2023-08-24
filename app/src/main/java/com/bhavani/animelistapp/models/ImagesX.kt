package com.bhavani.animelistapp.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ImagesX(
    val image_url: String?,
    val large_image_url: String?,
    val maximum_image_url: String?,
    val medium_image_url: String?,
    val small_image_url: String?
) : Parcelable {
    // Add a no-argument constructor
    constructor() : this("", "", "", "", "")
}