package com.challenge.globalFlavorsHub.ui.views.recipes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.challenge.globalFlavorsHub.data.model.RecipesViewData
import com.challenge.globalFlavorsHub.databinding.ItemSimpleRecipeBinding
import com.challenge.globalFlavorsHub.utils.extensions.asLiveData
import com.challenge.globalFlavorsHub.utils.extensions.loadImageFromURL

class RecipesAdapter :
    ListAdapter<RecipesViewData, RecipesAdapter.RecipesViewHolder>(TurboListContactsDiffUtil) {

    private val _onRecipeClick = MutableLiveData<RecipesViewData>()
    val onRecipeClick = _onRecipeClick .asLiveData()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesViewHolder = parent
        .context
        .let(LayoutInflater::from)
        .let { ItemSimpleRecipeBinding.inflate(it, parent, false) }
        .let(::RecipesViewHolder)

    override fun onBindViewHolder(holder: RecipesViewHolder, position: Int) {
        val recipe = getItem(position)
        with(holder.binding) {
            nameRecipe.text = recipe.recipe
            imageRecipe.loadImageFromURL(recipe.dishImageUrl)
            itemSimpleRecipe.setOnClickListener {  _onRecipeClick.value = recipe  }
        }
    }

    internal object TurboListContactsDiffUtil : DiffUtil.ItemCallback<RecipesViewData>() {
        override fun areItemsTheSame(
            oldItem: RecipesViewData,
            newItem: RecipesViewData,
        ): Boolean = oldItem == newItem

        override fun areContentsTheSame(
            oldItem: RecipesViewData,
            newItem: RecipesViewData,
        ): Boolean = oldItem == newItem
    }

    class RecipesViewHolder(val binding: ItemSimpleRecipeBinding) : RecyclerView.ViewHolder(binding.root)
}