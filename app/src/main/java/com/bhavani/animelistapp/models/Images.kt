package com.bhavani.animelistapp.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class  Images(
    val jpg: @RawValue Jpg,
    val webp: @RawValue Webp
) : Parcelable {
    constructor() : this(Jpg("","",""), Webp("","",""))
}