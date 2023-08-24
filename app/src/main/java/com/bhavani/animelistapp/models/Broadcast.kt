package com.bhavani.animelistapp.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Broadcast(
    val day: String?,
    val string: String?,
    val time: String?,
    val timezone: String?
) : Parcelable {
    // Add a no-argument constructor
    constructor() : this("", "", "", "")
}