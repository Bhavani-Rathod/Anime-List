package com.bhavani.animelistapp.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class Images(
    val jpg: @RawValue Jpg,
    val webp: @RawValue Webp
) : Parcelable {
    // a no-argument constructor
    constructor() : this(Jpg("", "", ""), Webp("", "", ""))
}