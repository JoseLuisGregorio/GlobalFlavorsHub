package com.challenge.globalFlavorsHub.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class RecipesDTO(
    @SerializedName("recipes") val recipes: List<RecipeDTO>? = emptyList(),
) : Parcelable
