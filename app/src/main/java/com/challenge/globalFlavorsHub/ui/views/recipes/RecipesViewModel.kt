package com.challenge.globalFlavorsHub.ui.views.recipes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.challenge.globalFlavorsHub.data.model.NetworkResource
import com.challenge.globalFlavorsHub.data.model.RecipesViewData
import com.challenge.globalFlavorsHub.data.repositories.GlobalFlavorsHubRecipesRepository
import com.challenge.globalFlavorsHub.utils.extensions.asLiveData
import com.challenge.globalFlavorsHub.utils.flatMapLatestResource
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import javax.inject.Inject

class RecipesViewModel @Inject constructor(
    private val globalFlavorsHubRecipesRepository: GlobalFlavorsHubRecipesRepository,
) : ViewModel() {

    var recipesList: List<RecipesViewData> = emptyList()

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
            flowOf(Success(recipes?.let(::generateList) ?: emptyList()))
        }.catch { emit(it.asNetworkResource()) }

    private fun generateList(notifications: List<RecipesViewData>): List<RecipesViewData> {
        val notSeenCount = notifications.count { it.seen.not() && it.id != "not_seen_header" }

        val unSeenList = listOf(
            RecipesViewData(id = "not_seen_header", title = notSeenCount.toString(), seen = false)
        ).takeIf { notSeenCount > 0 }?.let { header ->
            header + notifications.filter { it.seen.not() && it.id != "not_seen_header" }
        } ?: emptyList()

        val seenList = listOf(RecipesViewData(id = "seen_header", seen = true))
            .takeIf { notifications.any { it.seen } }?.let { header ->
                header + notifications.filter { it.seen && it.id != "seen_header" }
            } ?: emptyList()

        return unSeenList + seenList
    }

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
}
