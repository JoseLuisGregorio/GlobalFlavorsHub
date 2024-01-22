package com.challenge.globalFlavorsHub.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class RecipeDTO(
    @SerializedName("id") val id: Int? = 0,
    @SerializedName("country") val country: String? = "",
    @SerializedName("dish_name") val dishName: String? = "",
    @SerializedName("latitude") val latitude: Double? = 0.0,
    @SerializedName("longitude") val longitude: Double? = 0.0,
    @SerializedName("dish_image_url") val dishImageUrl: String? = "",
    @SerializedName("country_image_url") val countryImageUrl: String? = "",
    @SerializedName("shorts_ingredients") val shortsIngredients: String? = "",
    @SerializedName("ingredients") val ingredients: List<String>? = emptyList(),
    @SerializedName("instructions") val instructions: List<String>? = emptyList(),
) : Parcelable {
    fun toRecipeViewData() =
        RecipeViewData(
            id = id ?: 0,
            country = country.orEmpty(),
            dishName = dishName.orEmpty(),
            latitude = latitude ?: 0.0,
            longitude = longitude ?: 0.0,
            dishImageUrl = dishImageUrl.orEmpty(),
            countryImageUrl = countryImageUrl.orEmpty(),
            shortsIngredients = shortsIngredients.orEmpty(),
            ingredients = ingredients ?: emptyList(),
            instructions = instructions ?: emptyList(),
        )
}
