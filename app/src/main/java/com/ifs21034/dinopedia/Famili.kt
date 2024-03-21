package com.ifs21034.dinopedia

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Famili(
    var name: String,
    var icon: Int,
    var review : String,
    var description: String,
    var period: String,
    var characteristic: String,
    var habitat: String,
    var behavior: String,
    var classification: String
) : Parcelable