package com.challenge.globalFlavorsHub.ui.views.recipes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.challenge.globalFlavorsHub.data.model.RecipeViewData
import com.challenge.globalFlavorsHub.databinding.ItemSimpleRecipeBinding
import com.challenge.globalFlavorsHub.ui.views.recipes.adapter.RecipesAdapter.RecipesViewHolder
import com.challenge.globalFlavorsHub.utils.extensions.loadImageFromURL

typealias OnClickSelectRecipe = (RecipeViewData) -> Unit
class RecipesAdapter :
    ListAdapter<RecipeViewData, RecipesViewHolder>(ListRecipesDiffUtil) {

    private var onClickSelectRecipe: OnClickSelectRecipe? = null

    fun setOnRecipeClick(onClickRadio: OnClickSelectRecipe) {
        this.onClickSelectRecipe = onClickRadio
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesViewHolder = parent
        .context
        .let(LayoutInflater::from)
        .let { ItemSimpleRecipeBinding.inflate(it, parent, false) }
        .let(::RecipesViewHolder)

    override fun onBindViewHolder(holder: RecipesViewHolder, position: Int) {
        val recipe = getItem(position)
        with(holder.binding) {
            nameRecipe.text = recipe.dishName
            imageRecipe.loadImageFromURL(recipe.dishImageUrl)
            itemSimpleRecipe.setOnClickListener { onClickSelectRecipe?.invoke(recipe) }
        }
    }

    internal object ListRecipesDiffUtil : DiffUtil.ItemCallback<RecipeViewData>() {
        override fun areItemsTheSame(
            oldItem: RecipeViewData,
            newItem: RecipeViewData,
        ): Boolean = oldItem == newItem

        override fun areContentsTheSame(
            oldItem: RecipeViewData,
            newItem: RecipeViewData,
        ): Boolean = oldItem == newItem
    }

    class RecipesViewHolder(val binding: ItemSimpleRecipeBinding) : ViewHolder(binding.root)
}
