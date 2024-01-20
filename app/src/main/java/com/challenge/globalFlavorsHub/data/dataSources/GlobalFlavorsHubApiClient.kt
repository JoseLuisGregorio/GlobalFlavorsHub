package com.challenge.globalFlavorsHub.data.dataSources

import com.challenge.globalFlavorsHub.data.model.GlobalFlavorsHubResponse
import com.challenge.globalFlavorsHub.data.model.RecipesDTO
import retrofit2.Response
import retrofit2.http.GET

interface GlobalFlavorsHubApiClient {

    @GET("/35f44e12-835d-4c/recipes")
    suspend fun getRecipes(): Response<GlobalFlavorsHubResponse<RecipesDTO>>

    @GET("/35f44e12-835d-4c/recipesDetails")
    suspend fun getRecipesDetails(): Response<GlobalFlavorsHubResponse<RecipesDTO>>
}
