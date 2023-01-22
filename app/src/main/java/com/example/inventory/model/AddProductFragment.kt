package com.example.inventory.model

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.inventory.R
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}