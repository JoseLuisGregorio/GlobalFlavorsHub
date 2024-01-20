package com.challenge.globalFlavorsHub.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.text.SimpleDateFormat
import java.util.Locale

@Parcelize
data class RecipeDTO(
    @SerializedName("id") val id: Int? = 0,
    @SerializedName("country") val country: String? = "",
    @SerializedName("recipe") val recipe: String? = "",
    @SerializedName("latitude") val latitude: Double? = 0.0,
    @SerializedName("longitude") val longitude: Double? = 0.0,
    @SerializedName("dish_image_url") val dishImageUrl: String? = "",
    @SerializedName("country_image_url") val countryImageUrl: String? = "",
    @SerializedName("ingredients") val ingredients: List<String>? = emptyList(),
    @SerializedName("procedure") val procedure: List<String>? = emptyList(),
) : Parcelable {
    fun toRecipeViewData() =
        RecipesViewData(
            id = id ?: 0,
            country = country.orEmpty(),
            recipe = recipe.orEmpty(),
            latitude = latitude ?: 0.0,
            longitude = longitude ?: 0.0,
            dishImageUrl = dishImageUrl.orEmpty(),
            countryImageUrl = countryImageUrl.orEmpty(),
            ingredients = ingredients ?: emptyList(),
            procedure = procedure ?: emptyList(),
        )
}
