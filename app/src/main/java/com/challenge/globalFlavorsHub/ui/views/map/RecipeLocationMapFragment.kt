package com.challenge.globalFlavorsHub.ui.views.map

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.challenge.globalFlavorsHub.R
import com.challenge.globalFlavorsHub.databinding.FragmentRecipeLocationMapBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeLocationMapFragment : Fragment(R.layout.fragment_recipe_location_map) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(FragmentRecipeLocationMapBinding.bind(view)) {}
    }
}
