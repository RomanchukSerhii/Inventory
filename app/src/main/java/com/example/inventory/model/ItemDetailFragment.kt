package com.example.inventory.model

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.inventory.R
import com.example.inventory.databinding.FragmentItemDetailBinding
import com.example.inventory.viewmodel.MainViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlin.properties.Delegates

class ItemDetailFragment : Fragment() {

    private var _binding: FragmentItemDetailBinding? = null
    private val binding: FragmentItemDetailBinding
        get() = _binding ?: throw RuntimeException("FragmentItemDetailBinding == null")

    private var itemId: Int by Delegates.notNull()
    var quantity: Int = 0

    private val sharedViewModel: MainViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            itemId = it.getInt("id")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentItemDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedViewModel.getItem(itemId).observe(this.viewLifecycleOwner) { item ->
            item?.let {
                with(binding) {
                    textViewName.text = it.name
                    textViewPrice.text = getFormattedPrice(it.price)
                    textViewQuantity.text = getString(R.string.quantity_detail, it.quantity)
                    quantity = it.quantity
                    buttonSell.isEnabled = it.quantity != 0
                }
            }
        }
        binding.buttonSell.setOnClickListener {
            sellItem()
        }

        binding.buttonDelete.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Attention")
                .setMessage("Are you sure you want do delete?")
                .setCancelable(false)
                .setNegativeButton("No") { _, _ -> }
                .setPositiveButton("Yes") { _, _ ->
                    deleteItem()
                }.show()
        }

        binding.buttonEditItem.setOnClickListener {
            goToEditFragment()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun sellItem() {
        if (quantity > 0) {
            sharedViewModel.changeQuantityItem(itemId, quantity - 1)
        }
    }

    private fun deleteItem() {
        sharedViewModel.removeItem(itemId)
        findNavController().navigate(
            ItemDetailFragmentDirections.actionItemDetailFragmentToInventoryFragment()
        )
    }

    private fun goToEditFragment() {
        val action = ItemDetailFragmentDirections
            .actionItemDetailFragmentToAddProductFragment(
                getString(R.string.edit_product_title),
                itemId
            )
        findNavController().navigate(action)
    }
}