package com.challenge.globalFlavorsHub.ui.views.recipes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.challenge.globalFlavorsHub.data.model.NetworkResource
import com.challenge.globalFlavorsHub.data.model.RecipesViewData
import com.challenge.globalFlavorsHub.data.repositories.GlobalFlavorsHubRecipesRepository
import com.challenge.globalFlavorsHub.utils.asNetworkResource
import com.challenge.globalFlavorsHub.utils.extensions.asLiveData
import com.challenge.globalFlavorsHub.utils.flatMapLatestResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(
    private val globalFlavorsHubRecipesRepository: GlobalFlavorsHubRecipesRepository,
) : ViewModel() {

    private val _recipes = MutableLiveData<NetworkResource<List<RecipesViewData>>>()
    val recipes = _recipes.asLiveData()

    init {
        viewModelScope.launch {
            getRecipes().collect { _recipes.postValue(it) }
        }
    }

    private fun getRecipes() = globalFlavorsHubRecipesRepository
        .getRecipes()
        .flatMapLatestResource { recipes ->
            flowOf(NetworkResource.Success(recipes ?: emptyList()))
        }.catch { emit(it.asNetworkResource()) }

/*
fun search(query: CharSequence?) {
    _recipes.value = (
            contactList.takeIf { query != null && query.isEmpty() }
                ?: query?.toString()?.curate()?.let { filter ->
                    contactList.filter {
                        it.fullName.curate().contains(filter) ||
                                it.alias.curate().contains(filter)
                    }
                }?.sortedBy { it.fullName }
            )
        .let(::Success)
}
 */
}
