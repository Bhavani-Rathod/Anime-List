package com.bhavani.animelistapp.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Pagination(
    val has_next_page: Boolean,
    val last_visible_page: Int
) : Parcelable {
    constructor() : this(false,0)
}