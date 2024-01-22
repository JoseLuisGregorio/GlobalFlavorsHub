package com.challenge.globalFlavorsHub.ui.views.recipes

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.challenge.globalFlavorsHub.R
import com.challenge.globalFlavorsHub.data.model.NetworkResource
import com.challenge.globalFlavorsHub.databinding.FragmentRecipesBinding
import com.challenge.globalFlavorsHub.ui.views.main.GlobalFlavorsHubViewModel
import com.challenge.globalFlavorsHub.ui.views.recipes.adapter.RecipesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipesFragment : Fragment(R.layout.fragment_recipes) {

    private val globalFlavorsHubViewModel: GlobalFlavorsHubViewModel by activityViewModels()
    private val recipesViewModel: RecipesViewModel by viewModels()

    private val recipesAdapter: RecipesAdapter by lazy { RecipesAdapter() }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(FragmentRecipesBinding.bind(view)) {
            recyclerViewRecipes.adapter = recipesAdapter
            recipesViewModel.getAllRecipes()
            globalFlavorsHubViewModel.refreshRequest = recipesViewModel::getAllRecipes
            recipesViewModel.recipes.observe(viewLifecycleOwner) { resource ->
                globalFlavorsHubViewModel.isLoading(resource is NetworkResource.Loading)
                if (resource !is NetworkResource.Loading) {
                    globalFlavorsHubViewModel.finishRefreshRequest()
                }
                when (resource) {
                    is NetworkResource.Loading -> Unit
                    is Error -> globalFlavorsHubViewModel.showErrorMessage(resource.message)
                    is NetworkResource.Success -> resource.data?.let { recipesList ->
                        recipesAdapter.submitList(recipesList)
                    }

                    else -> {}
                }
            }

            recipesAdapter.onRecipeClick.observe(viewLifecycleOwner) {
                findNavController().navigate(RecipesFragmentDirections.openRecipeDetails(it.id))
            }
        }
    }

    override fun onDestroyView() {
        globalFlavorsHubViewModel.refreshRequest = null
        super.onDestroyView()
    }
}
