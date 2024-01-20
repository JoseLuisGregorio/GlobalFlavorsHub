package com.challenge.globalFlavorsHub.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RecipesViewData(
    val id: Int,
    val recipe: String,
    val country: String,
    val latitude: Double,
    val longitude: Double,
    val dishImageUrl: String,
    val countryImageUrl: String,
    val ingredients: List<String>,
    val procedure: List<String>,
) : Comparable<RecipesViewData>, Parcelable {
    override fun compareTo(other: RecipesViewData): Int =
        recipe.compareTo(other.recipe)
}
