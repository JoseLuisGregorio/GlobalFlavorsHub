package com.challenge.globalFlavorsHub.ui.views.recipes

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
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

    private val globalFlavorsHubViewModel: GlobalFlavorsHubViewModel by viewModels()
    private val recipesViewModel: RecipesViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(FragmentRecipesBinding.bind(view)) {
            val adapter = RecipesAdapter()
            recyclerViewRecipes.adapter = adapter
            recipesViewModel.recipes.observe(viewLifecycleOwner) { resource ->
                if (resource == null) return@observe
                globalFlavorsHubViewModel.isLoading(resource is NetworkResource.Loading)
                resource.data?.let { contactList ->
                    adapter.submitList(contactList)
                    // isFirstTimeHere = false
                }
            }

            adapter.onRecipeClick.observe(viewLifecycleOwner) {
                // findNavController().navigate(openRecipesDetails(id = it.id))
            }
        }
    }
}
