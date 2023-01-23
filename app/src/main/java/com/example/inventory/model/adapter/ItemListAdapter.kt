package com.example.inventory.model.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.inventory.data.item.Item
import com.example.inventory.databinding.ItemBinding

class ItemListAdapter(
    private val onItemClick: (position: Int) -> Unit
) : ListAdapter<Item, ItemListAdapter.ItemViewHolder>(ItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ItemViewHolder(binding, onItemClick)
    }


    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.textViewName.text = item.name
        holder.textViewPrice.text = item.price.toString()
        holder.textViewQuantity.text = item.quantity.toString()
    }

    class ItemViewHolder(
        binding: ItemBinding,
        private val onItemClick: (id: Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        val textViewName = binding.textViewName
        val textViewPrice = binding.textViewPrice
        val textViewQuantity = binding.textViewQuantity

        init {
            itemView.setOnClickListener( this )
        }

        override fun onClick(v: View?) {
            onItemClick(adapterPosition)
        }

    }
}