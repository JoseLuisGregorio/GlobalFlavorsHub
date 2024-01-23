package com.challenge.globalFlavorsHub.ui.views.recipeDetails

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.challenge.globalFlavorsHub.R
import com.challenge.globalFlavorsHub.data.model.NetworkResource
import com.challenge.globalFlavorsHub.data.model.RecipeViewData
import com.challenge.globalFlavorsHub.databinding.FragmentRecipeDetailsBinding
import com.challenge.globalFlavorsHub.ui.views.adapter.SimpleStringAdapter
import com.challenge.globalFlavorsHub.ui.views.main.GlobalFlavorsHubViewModel
import com.challenge.globalFlavorsHub.utils.extensions.loadImageFromURL
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeDetailsFragment : Fragment(R.layout.fragment_recipe_details) {
    private val globalFlavorsHubViewModel: GlobalFlavorsHubViewModel by activityViewModels()
    private val recipeDetailsViewModel: RecipeDetailsViewModel by viewModels()
    private val navArgs: RecipeDetailsFragmentArgs by navArgs()
    private lateinit var binding: FragmentRecipeDetailsBinding
    private val ingredientsAdapter: SimpleStringAdapter by lazy { SimpleStringAdapter() }
    private val procedureAdapter: SimpleStringAdapter by lazy { SimpleStringAdapter() }
    private var recipeDetail: RecipeViewData? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentRecipeDetailsBinding.bind(view)
        recipeDetailsViewModel.getDetailsOfRecipe(navArgs.recipeID)
        binding.apply {
            recyclerViewIngredients.adapter = ingredientsAdapter
            recyclerViewProcedure.adapter = procedureAdapter
        }
        setUpObservers()
        onClickListener()
    }

    private fun setUpObservers() {
        recipeDetailsViewModel.recipeDetails.observe(viewLifecycleOwner) { resource ->
            globalFlavorsHubViewModel.isLoading(resource is NetworkResource.Loading)
            when (resource) {
                is NetworkResource.Loading -> Unit
                is NetworkResource.Success -> {
                    globalFlavorsHubViewModel.finishRefreshRequest()
                    resource.data?.let { dataRecipeDetail ->
                        recipeDetail = dataRecipeDetail
                        showData()
                    }
                }
                else -> {}
            }
        }
    }

    private fun showData() {
        binding.apply {
            recipeDetail?.let { safeRecipeDetail ->
                materialTextViewNameRecipe.text = safeRecipeDetail.dishName
                shapeableImageViewDishImage.loadImageFromURL(safeRecipeDetail.dishImageUrl)
                ingredientsAdapter.submitList(safeRecipeDetail.ingredients)
                procedureAdapter.submitList(safeRecipeDetail.procedure)
            }
        }
    }

    private fun onClickListener() {
        binding.materialButtonOpenInMap.setOnClickListener {
            recipeDetail?.let { safeRecipeDetail ->
                globalFlavorsHubViewModel.isLoading(true)
                findNavController().navigate(
                    RecipeDetailsFragmentDirections.openRecipeLocationMap(
                        (safeRecipeDetail.latitude).toFloat(),
                        (safeRecipeDetail.longitude).toFloat(),
                        safeRecipeDetail.country,
                    ),
                )
            }
        }
    }
}
