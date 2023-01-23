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
import com.example.inventory.databinding.FragmentEditProductBinding
import com.example.inventory.viewmodel.MainViewModel
import kotlin.properties.Delegates.notNull


class EditProductFragment : Fragment() {
    private var _binding: FragmentEditProductBinding? = null
    private val binding: FragmentEditProductBinding
        get() = _binding ?: throw RuntimeException("FragmentEditProductBinding == null")

    private val sharedViewModel: MainViewModel by activityViewModels()

    private lateinit var name: String
    private var price: Float by notNull()
    private var quantity: Int by notNull()
    private var itemId: Int by notNull()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            name = it.getString("name").toString()
            price = it.getFloat("price")
            quantity = it.getInt("quantity")
            itemId = it.getInt("id")
        }
    }

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
        setEditTextField()
        binding.buttonSave.setOnClickListener {
            editItem()
        }
        binding.buttonCancel.setOnClickListener {
            backToInventoryFragment()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun editItem() {
        with(binding) {
            val name = editTextProductName.text.toString()
            val price = editTextProductPrice.text.toString()
            val quantity = editTextQuantity.text.toString()

            if ( name.isNotBlank() && price.isNotBlank() && quantity.isNotBlank() ) {
                sharedViewModel.replaceItem(
                    id = itemId,
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
        val action = EditProductFragmentDirections.actionEditProductFragmentToInventoryFragment()
        findNavController().navigate(action)
    }

    private fun setEditTextField() {
        with(binding) {
            editTextProductName.setText(name)
            editTextProductPrice.setText(price.toString())
            editTextQuantity.setText(quantity.toString())
        }
    }

    private fun addRedAsterisk() {
        with(binding) {
            tilProductName.markRequiredInRed()
            tilProductPrice.markRequiredInRed()
            tilQuantity.markRequiredInRed()
        }
    }
}