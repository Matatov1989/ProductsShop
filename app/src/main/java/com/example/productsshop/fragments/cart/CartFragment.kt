package com.example.productsshop.fragments.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.productsshop.R
import com.example.productsshop.adapters.CartAdapter
import com.example.productsshop.databinding.FragmentCartBinding
import com.example.productsshop.fragments.BaseFragment


class CartFragment : BaseFragment() {

    private lateinit var binding: FragmentCartBinding
    private lateinit var cartViewModel: CartViewModel
    private lateinit var cartAdapter: CartAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar(binding.toolbar, getString(R.string.titleCart), true)
        setObserve()
    }

    private fun setObserve() {
    //    cartViewModel = ViewModelProvider(this).get(CartViewModel::class.java)

        cartViewModel.cartProductsLiveData.observe(viewLifecycleOwner, Observer { cartProducts ->
            cartAdapter = CartAdapter(cartProducts)
            binding.recyclerViewCartProducts.adapter = cartAdapter
        })
    }
}
