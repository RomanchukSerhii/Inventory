package com.example.inventory.model

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.inventory.databinding.FragmentEditProductBinding


class EditProductFragment : Fragment() {
    private var _binding: FragmentEditProductBinding? = null
    private val binding: FragmentEditProductBinding
        get() = _binding ?: throw RuntimeException("FragmentEditProductBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentEditProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addRedAsterisk()
        binding.buttonSave.setOnClickListener {
            saveItem()
        }
        binding.buttonCancel.setOnClickListener {
            backToInventoryFragment()
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
        val action = EditProductFragmentDirections.actionEditProductFragmentToInventoryFragment()
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