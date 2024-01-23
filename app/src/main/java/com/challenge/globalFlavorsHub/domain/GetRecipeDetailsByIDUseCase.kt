package com.challenge.globalFlavorsHub.domain

import com.challenge.globalFlavorsHub.data.model.NetworkResource
import com.challenge.globalFlavorsHub.data.model.RecipeViewData
import com.challenge.globalFlavorsHub.data.repositories.GlobalFlavorsHubRecipesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetRecipeDetailsByIDUseCase @Inject constructor(
    private val globalFlavorsHubRecipesRepository: GlobalFlavorsHubRecipesRepository,
) {
    operator fun invoke(id: Int, dispatcher: CoroutineDispatcher = Dispatchers.Unconfined): Flow<NetworkResource<RecipeViewData>> = globalFlavorsHubRecipesRepository.getRecipesDetails().map { response ->
        response.map { data ->
            data?.first { it.id == id }
                ?.let { recipe ->
                    RecipeViewData(
                        id = recipe.id ?: 0,
                        dishName = recipe.dishName ?: "",
                        country = recipe.country ?: "",
                        latitude = recipe.latitude ?: 0.0,
                        longitude = recipe.longitude ?: 0.0,
                        dishImageUrl = recipe.dishImageUrl ?: "",
                        countryImageUrl = recipe.countryImageUrl ?: "",
                        shortsIngredients = recipe.shortsIngredients ?: "",
                        ingredients = recipe.ingredients ?: emptyList(),
                        procedure = recipe.procedure ?: emptyList(),
                    )
                } ?: RecipeViewData(
                id = 0,
                dishName = "",
                country = "",
                latitude = 0.0,
                longitude = 0.0,
                dishImageUrl = "",
                countryImageUrl = "",
                shortsIngredients = "",
                ingredients = emptyList(),
                procedure = emptyList(),
            )
        }
    }.flowOn(dispatcher)
}
