package com.challenge.globalFlavorsHub.ui.views.recipeDetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.challenge.globalFlavorsHub.data.model.NetworkResource
import com.challenge.globalFlavorsHub.utils.extensions.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecipeDetailsViewModel @Inject constructor(
    // private val signInUseCase: SignInUseCase,
) : ViewModel() {

    private val _emailValidation = MutableLiveData<NetworkResource<Unit>>()
    val emailValidation = _emailValidation.asLiveData()
}
