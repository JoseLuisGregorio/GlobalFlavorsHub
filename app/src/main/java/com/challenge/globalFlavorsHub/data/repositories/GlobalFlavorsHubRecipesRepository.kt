package com.challenge.globalFlavorsHub.data.repositories

import com.challenge.globalFlavorsHub.data.dataSources.GlobalFlavorsHubApiClient
import com.challenge.globalFlavorsHub.utils.globalFlavorsHubResponse
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GlobalFlavorsHubRecipesRepository @Inject constructor(
    private val api: GlobalFlavorsHubApiClient,
) {

    fun getRecipes() = globalFlavorsHubResponse(block = api::getRecipes).map { resource ->
        resource.map { response ->
            response?.recipes?.map { recipes ->
                recipes.toRecipeViewData()
            }
        }
    }

    fun getRecipesDetails() = globalFlavorsHubResponse(block = api::getRecipesDetails).map { resource ->
        resource.map { response ->
            response?.recipes?.map { recipes ->
                recipes
            }
        }
    }
}
