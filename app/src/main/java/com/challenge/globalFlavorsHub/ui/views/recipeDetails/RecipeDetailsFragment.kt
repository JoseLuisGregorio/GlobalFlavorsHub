package com.challenge.globalFlavorsHub.ui.views.recipeDetails

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.challenge.globalFlavorsHub.R
import com.challenge.globalFlavorsHub.data.model.NetworkResource
import com.challenge.globalFlavorsHub.databinding.FragmentRecipeDetailsBinding
import com.challenge.globalFlavorsHub.ui.views.main.GlobalFlavorsHubViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeDetailsFragment : Fragment(R.layout.fragment_recipe_details) {
    private val globalFlavorsHubViewModel: GlobalFlavorsHubViewModel by activityViewModels()
    private val recipeDetailsViewModel: RecipeDetailsViewModel by viewModels()
    private val navArgs: RecipeDetailsFragmentArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(FragmentRecipeDetailsBinding.bind(view)) {
            recipeDetailsViewModel.getDetailsOfRecipe(navArgs.recipeID)

            recipeDetailsViewModel.recipeDetails.observe(viewLifecycleOwner) { resource ->
                globalFlavorsHubViewModel.isLoading(resource is NetworkResource.Loading)
                when (resource) {
                    is NetworkResource.Loading -> Unit
                    is NetworkResource.Success -> {
                        globalFlavorsHubViewModel.finishRefreshRequest()
                        resource.data?.let { recipesList ->
                            nameRecipe.text = recipesList.dishName
                        }
                    }

                    else -> {}
                }
            }
        }
    }
}
