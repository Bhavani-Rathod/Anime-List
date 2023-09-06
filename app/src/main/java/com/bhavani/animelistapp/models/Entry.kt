package com.bhavani.animelistapp.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class Entry(
    val images: @RawValue Images,
    val mal_id: Int,
    val title: String,
    val url: String
) : Parcelable {
    constructor() : this(Images(Jpg("","",""), Webp("","","")),0,"","")
}