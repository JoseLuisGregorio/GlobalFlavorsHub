package com.challenge.globalFlavorsHub.ui.views.recipeDetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.challenge.globalFlavorsHub.data.model.NetworkResource
import com.challenge.globalFlavorsHub.data.model.RecipeViewData
import com.challenge.globalFlavorsHub.domain.GetRecipeDetailsByIDUseCase
import com.challenge.globalFlavorsHub.utils.extensions.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeDetailsViewModel @Inject constructor(
    private val getRecipeDetailsByIDUseCase: GetRecipeDetailsByIDUseCase,
) : ViewModel() {

    private val _recipeDetails = MutableLiveData<NetworkResource<RecipeViewData>>()
    val recipeDetails = _recipeDetails.asLiveData()

    fun getDetailsOfRecipe(recipeID: Int) {
        viewModelScope.launch {
            getRecipeDetailsByIDUseCase(recipeID).collect(_recipeDetails::postValue)
        }
    }
}
