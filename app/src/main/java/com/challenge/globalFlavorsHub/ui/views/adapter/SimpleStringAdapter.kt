package com.challenge.globalFlavorsHub.ui.views.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.challenge.globalFlavorsHub.databinding.ItemSimpleStringBinding
import com.challenge.globalFlavorsHub.ui.views.adapter.SimpleStringAdapter.SimpleStringViewHolder

class SimpleStringAdapter :
    ListAdapter<String, SimpleStringViewHolder>(ListStringDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleStringViewHolder = parent
        .context
        .let(LayoutInflater::from)
        .let { ItemSimpleStringBinding.inflate(it, parent, false) }
        .let(::SimpleStringViewHolder)

    override fun onBindViewHolder(holder: SimpleStringViewHolder, position: Int) {
        val recipe = getItem(position)
        with(holder.binding) {
            stringSimpleValue.text = "• $recipe"
        }
    }

    internal object ListStringDiffUtil : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(
            oldItem: String,
            newItem: String,
        ): Boolean = oldItem == newItem

        override fun areContentsTheSame(
            oldItem: String,
            newItem: String,
        ): Boolean = oldItem == newItem
    }

    class SimpleStringViewHolder(val binding: ItemSimpleStringBinding) : ViewHolder(binding.root)
}
