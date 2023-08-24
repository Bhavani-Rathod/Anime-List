package com.bhavani.animelistapp.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class Aired(
    val from: String?,
    val prop: @RawValue Prop?,
    val string: String?,
    val to: String?
) : Parcelable {
    // Add a no-argument constructor
    constructor() : this("", Prop(From(0, 0, 0), To(0, 0, 0)), "", "")
}