package com.bhavani.animelistapp.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class Prop(
    val from: @RawValue From?,
    val to: @RawValue To?
) : Parcelable {
    // Add a no-argument constructor
    constructor() : this(From(0, 0, 0), To(0, 0, 0))
}

