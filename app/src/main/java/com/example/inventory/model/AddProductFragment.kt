package com.example.inventory.model

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.inventory.R
import com.example.inventory.databinding.FragmentAddProductBinding
import com.example.inventory.viewmodel.MainViewModel


class AddProductFragment : Fragment() {

    private var _binding: FragmentAddProductBinding? = null
    private val binding: FragmentAddProductBinding
        get() = _binding ?: throw RuntimeException("FragmentAddProductBinding == null")

    private val sharedViewModel: MainViewModel by activityViewModels()

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
        with(binding) {
            val name = editTextProductName.text.toString()
            val price = editTextProductPrice.text.toString()
            val quantity = editTextQuantity.text.toString()

            if ( name.isNotBlank() && price.isNotBlank() && quantity.isNotBlank() ) {
                sharedViewModel.addItem(
                    name = name,
                    price = price.toDouble(),
                    quantity = quantity.toInt()
                )
            } else {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.warning),
                    Toast.LENGTH_SHORT
                ).show()
                return
            }
        }
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