package com.bhavani.animelistapp.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class Trailer(
    val embed_url: String?,
    val images: @RawValue ImagesX?,
    val url: String?,
    val youtube_id: String?
) : Parcelable {
    // Add a no-argument constructor
    constructor() : this("", ImagesX("", "", "", "", ""), "", "")
}