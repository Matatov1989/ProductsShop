package com.example.productsshop.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.productsshop.databinding.FragmentProductInfoBinding
import com.example.productsshop.models.ProductItem


class ProductInfoFragment : Fragment() {

    private lateinit var binding: FragmentProductInfoBinding
    private lateinit var productItem: ProductItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            productItem = ProductInfoFragmentArgs.fromBundle(it).productItem
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        binding.product = productItem
    }
}
