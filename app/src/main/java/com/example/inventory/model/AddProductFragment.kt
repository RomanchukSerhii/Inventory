package com.example.inventory.model

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.inventory.databinding.FragmentAddProductBinding


class AddProductFragment : Fragment() {

    private var _binding: FragmentAddProductBinding? = null
    private val binding: FragmentAddProductBinding
        get() = _binding ?: throw RuntimeException("FragmentAddProductBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentAddProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addRedAsterisk()
        binding.buttonCancel.setOnClickListener {
            backToInventoryFragment()
        }

        binding.buttonSave.setOnClickListener {
            saveItem()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun saveItem() {
        backToInventoryFragment()
    }

    private fun backToInventoryFragment() {
        val action = AddProductFragmentDirections.actionAddProductFragmentToInventoryFragment()
        findNavController().navigate(action)
    }

    private fun addRedAsterisk() {
        with(binding) {
            tilProductName.markRequiredInRed()
            tilProductPrice.markRequiredInRed()
            tilQuantity.markRequiredInRed()
        }
    }
}