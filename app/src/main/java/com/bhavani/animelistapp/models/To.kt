package com.bhavani.animelistapp.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class To(
    val day: Int?,
    val month: Int?,
    val year: Int?
) : Parcelable {
    //a no-argument constructor
    constructor() : this(0,0,0)
}