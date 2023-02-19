package com.example.inventory.model

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.inventory.R
import com.example.inventory.databinding.FragmentInventoryBinding
import com.example.inventory.model.adapter.ItemListAdapter
import com.example.inventory.viewmodel.MainViewModel


class InventoryFragment : Fragment() {
    private var _binding: FragmentInventoryBinding? = null
    private val binding: FragmentInventoryBinding
        get() = _binding ?: throw RuntimeException("FragmentInventoryBinding == null")

    private val sharedViewModel: MainViewModel by activityViewModels()
    private lateinit var itemListAdapter: ItemListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentInventoryBinding.inflate(inflater, container, false)
        itemListAdapter = ItemListAdapter { onListItemClick(it) }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedViewModel.itemsListLiveData.observe(this.viewLifecycleOwner) { items ->
            items?.let {
                itemListAdapter.submitList(it)
            }
        }
        binding.recyclerView.adapter = itemListAdapter

        binding.buttonAddProduct.setOnClickListener {
            goToAddProduct()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun goToAddProduct() {
        val action = InventoryFragmentDirections
            .actionInventoryFragmentToAddProductFragment(getString(R.string.add_product_title))
        findNavController().navigate(action)
    }

    private fun onListItemClick(position: Int) {
        val item = itemListAdapter.currentList[position]
        val id = item.id
        val action = InventoryFragmentDirections
            .actionInventoryFragmentToItemDetailFragment(id)
        findNavController().navigate(action)
    }
}