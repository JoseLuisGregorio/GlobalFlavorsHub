package com.challenge.globalFlavorsHub.ui.views.recipes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.challenge.globalFlavorsHub.data.model.NetworkResource
import com.challenge.globalFlavorsHub.data.model.NetworkResource.Success
import com.challenge.globalFlavorsHub.data.model.RecipeViewData
import com.challenge.globalFlavorsHub.data.repositories.GlobalFlavorsHubRecipesRepository
import com.challenge.globalFlavorsHub.utils.asNetworkResource
import com.challenge.globalFlavorsHub.utils.extensions.asLiveData
import com.challenge.globalFlavorsHub.utils.flatMapLatestResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(
    private val globalFlavorsHubRecipesRepository: GlobalFlavorsHubRecipesRepository,
) : ViewModel() {

    var recipesList: List<RecipeViewData> = emptyList()
    private val _recipes = MutableLiveData<NetworkResource<List<RecipeViewData>>>()
    val recipes = _recipes.asLiveData()

    fun getAllRecipes() {
        viewModelScope.launch {
            getRecipes().collect { _recipes.postValue(it) }
        }
    }

    private fun getRecipes() = globalFlavorsHubRecipesRepository
        .getRecipes()
        .onEach {
            recipesList = it.data ?: emptyList()
        }
        .flatMapLatestResource { recipes ->
            flowOf(NetworkResource.Success(recipes ?: emptyList()))
        }.catch { emit(it.asNetworkResource()) }

    fun search(query: CharSequence?) {
        _recipes.value = (
                recipesList.takeIf { query != null && query.isEmpty() }
                    ?: query?.toString()?.curate()?.let { filter ->
                        recipesList.filter {
                            it.dishName.curate().contains(filter) ||
                                    it.shortsIngredients.curate().contains(filter)
                        }
                    }?.sortedBy { it.dishName }
                )
            .let(::Success)
    }
    private fun String.curate() = lowercase().map {
        hashMapOf(
            'á' to 'a',
            'é' to 'e',
            'í' to 'i',
            'ó' to 'o',
            'ú' to 'u',
            'ñ' to 'n',
        )[it] ?: it
    }.joinToString("")
}
