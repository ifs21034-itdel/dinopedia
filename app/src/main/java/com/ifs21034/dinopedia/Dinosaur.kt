package com.ifs21034.dinopedia

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Dinosaur(
    var name: String,
    var icon: Int,
    var description: String,
    var review: String,
    var characteristic: String,
    var group: String,
    var habitat: String,
    var food: String,
    var length: String,
    var height: String,
    var weight: String,
    var weakness: String
) : Parcelable
