package com.challenge.globalFlavorsHub.ui.views.recipeDetails

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.challenge.globalFlavorsHub.R
import com.challenge.globalFlavorsHub.databinding.FragmentRecipeDetailsBinding
import com.challenge.globalFlavorsHub.ui.views.main.GlobalFlavorsHubViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeDetailsFragment : Fragment(R.layout.fragment_recipe_details) {
    private val globalFlavorsHubViewModel: GlobalFlavorsHubViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(FragmentRecipeDetailsBinding.bind(view)) {}
    }
}
