package com.bhavani.animelistapp.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale

@Parcelize
data class Data(
    val content: String,
    val date: String,
    val entry: @RawValue List<Entry>,
    val mal_id: String,
    val user: @RawValue User
) : Parcelable {
    constructor() : this(
        "",
        "",
        emptyList(),
        "",
        User("","")
    )

    // Custom property to get the formatted date
    val formattedDate: String?
        get() {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX", Locale.getDefault())
            val outputFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())

            return try {
                val date = inputFormat.parse(date)
                date?.let { outputFormat.format(it) }
            } catch (e: ParseException) {
                e.printStackTrace()
                "Date not available"
            }
        }
}