package com.challenge.globalFlavorsHub.ui.views.recipes

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.challenge.globalFlavorsHub.R
import com.challenge.globalFlavorsHub.data.model.NetworkResource.Error
import com.challenge.globalFlavorsHub.data.model.NetworkResource.Loading
import com.challenge.globalFlavorsHub.data.model.NetworkResource.Success
import com.challenge.globalFlavorsHub.databinding.FragmentRecipesBinding
import com.challenge.globalFlavorsHub.ui.views.main.GlobalFlavorsHubViewModel
import com.challenge.globalFlavorsHub.ui.views.recipes.adapter.RecipesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipesFragment : Fragment(R.layout.fragment_recipes) {

    private lateinit var binding: FragmentRecipesBinding
    private val globalFlavorsHubViewModel: GlobalFlavorsHubViewModel by activityViewModels()
    private val recipesViewModel: RecipesViewModel by viewModels()

    private val recipesAdapter: RecipesAdapter by lazy { RecipesAdapter() }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentRecipesBinding.bind(view)
        setUpObservers()
        binding.apply {
            recyclerViewRecipes.adapter = recipesAdapter
            recipesViewModel.getAllRecipes()
            globalFlavorsHubViewModel.refreshRequest = recipesViewModel::getAllRecipes
        }
    }

    private fun setUpObservers() {
        recipesViewModel.recipes.observe(viewLifecycleOwner) { resource ->
            globalFlavorsHubViewModel.isLoading(resource is Loading)
            if (resource !is Loading) {
                globalFlavorsHubViewModel.finishRefreshRequest()
            }
            when (resource) {
                is Loading -> Unit
                is Error -> globalFlavorsHubViewModel.showErrorMessage(resource.message)
                is Success -> resource.data?.let { recipesList ->
                    recipesAdapter.submitList(recipesList)
                }

                else -> {}
            }
        }

        recipesAdapter.setOnRecipeClick {
            findNavController().navigate(RecipesFragmentDirections.openRecipeDetails(it.id))
        }
    }

    override fun onDestroyView() {
        globalFlavorsHubViewModel.refreshRequest = null
        super.onDestroyView()
    }
}
