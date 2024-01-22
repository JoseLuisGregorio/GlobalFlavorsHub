package com.challenge.globalFlavorsHub.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RecipeViewData(
    val id: Int,
    val dishName: String,
    val country: String,
    val latitude: Double,
    val longitude: Double,
    val dishImageUrl: String,
    val countryImageUrl: String,
    val shortsIngredients: String,
    val ingredients: List<String>,
    val instructions: List<String>,
) : Comparable<RecipeViewData>, Parcelable {
    override fun compareTo(other: RecipeViewData): Int =
        dishName.compareTo(other.dishName)
}
