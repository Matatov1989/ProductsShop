package com.example.productsshop.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.productsshop.R
import com.example.productsshop.databinding.FragmentProductInfoBinding
import com.example.productsshop.models.CartModel
import com.google.android.material.snackbar.Snackbar


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
        arguments?.let {
            productItem = ProductInfoFragmentArgs.fromBundle(it).productItem
        }
        initToolbar(binding.toolbar, getString(R.string.titleInformation), true)
        initClickListeners()
        binding.lifecycleOwner = this
        binding.product = productItem

    }

    private fun initClickListeners() {
        binding.buttonAddToCart.setOnClickListener {

            val amount = binding.textViewAmount.text.toString().toInt()
            val price = if (productItem.discount == 0)
                productItem.price * amount
            else (productItem.price - (productItem.price / productItem.discount)) * amount

            val cartProduct = CartModel(
                id = productItem.id,
                name = productItem.name,
                price = price,
                imageUrl = productItem.imageUrl,
                quantity = amount,
                color = binding.spinnerColors.selectedItem.toString()
            )
            cartViewModel.addProductToCart(cartProduct)

            Snackbar.make(it, getString(R.string.snackBarItemInCart), Snackbar.LENGTH_LONG).show()
        }

        binding.floatingActionButtonInc.setOnClickListener {
            var amount = binding.textViewAmount.text.toString().toInt()
            if (amount.inc() <= productItem.quantity)
                binding.textViewAmount.text = amount.inc().toString()
        }

        binding.floatingActionButtonDec.setOnClickListener {
            var amount = binding.textViewAmount.text.toString().toInt()
            if (amount.dec() >= 1)
                binding.textViewAmount.text = amount.dec().toString()
        }
    }
}
