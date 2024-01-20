package com.challenge.globalFlavorsHub.data.dataSources

import com.challenge.globalFlavorsHub.data.model.GlobalFlavorsHubResponse
import com.challenge.globalFlavorsHub.data.model.RecipesDTO
import retrofit2.Response
import retrofit2.http.GET

interface GlobalFlavorsHubApiClient {

    @GET("/recipes")
    suspend fun getRecipes(): Response<GlobalFlavorsHubResponse<RecipesDTO>>

    @GET("/recipesDetails")
    suspend fun getRecipesDetails(): Response<GlobalFlavorsHubResponse<RecipesDTO>>
}
