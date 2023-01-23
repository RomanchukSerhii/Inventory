package com.example.inventory.model

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.inventory.R
import com.example.inventory.databinding.FragmentInventoryBinding
import com.example.inventory.model.adapter.ItemListAdapter
import com.example.inventory.viewmodel.MainViewModel


class InventoryFragment : Fragment() {
    private var _binding: FragmentInventoryBinding? = null
    private val binding: FragmentInventoryBinding
        get() = _binding ?: throw RuntimeException("FragmentInventoryBinding == null")

    private val sharedViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentInventoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val itemListAdapter = ItemListAdapter {
            onListItemClick(it)
        }
        sharedViewModel.itemsListLiveData.observe(requireActivity()) {
            itemListAdapter.submitList(it)
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
        val action = InventoryFragmentDirections.actionInventoryFragmentToAddProductFragment()
        findNavController().navigate(action)
    }

    private fun onListItemClick(position: Int) {
        var id = 0
        var name = "Name"
        var price = 0.0f
        var quantity = 0
        sharedViewModel.itemsListLiveData.observe(requireActivity()) {
            val item = it[position]
            id = item.id
            name = item.name
            price = item.price.toFloat()
            quantity = item.quantity
        }
        val action = InventoryFragmentDirections
            .actionInventoryFragmentToEditProductFragment(name, price, quantity, id)
        findNavController().navigate(action)
    }
}