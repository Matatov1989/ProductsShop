package com.example.productsshop.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.productsshop.R
import com.example.productsshop.databinding.FragmentProductInfoBinding
import com.example.productsshop.fragments.cart.CartViewModel
import com.example.productsshop.models.CartModel


class ProductInfoFragment : BaseFragment() {

    private lateinit var binding: FragmentProductInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar(binding.toolbar, getString(R.string.titleInformation), true)
        initClickListeners()
        binding.lifecycleOwner = this
        binding.product = productItem

    }

    private fun initClickListeners() {

        binding.buttonAddToCart.setOnClickListener {

            val cartProduct = CartModel(
                id = productItem.id,
                name = productItem.name,
                price = productItem.price,
                imageUrl = productItem.imageUrl
            )
          //  cartViewModel.addProductToCart(cartProduct)
        }
    }
}
