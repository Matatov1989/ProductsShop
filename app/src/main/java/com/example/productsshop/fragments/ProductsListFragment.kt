package com.example.productsshop.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.productsshop.adapters.ProductsListAdapter
import com.example.productsshop.databinding.FragmentProductsListBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProductsListFragment : Fragment() {

    private lateinit var binding: FragmentProductsListBinding
    private lateinit var productsViewModel: ProductsViewModel
    private lateinit var productsAdapter: ProductsListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentProductsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObserve()
    }

    private fun setObserve() {
        productsViewModel = ViewModelProvider(this).get(ProductsViewModel::class.java)

        productsViewModel.productsLiveData.observe(viewLifecycleOwner, Observer { products ->
            productsAdapter = ProductsListAdapter(products)
            binding.recyclerViewProducts.adapter = productsAdapter
        })
    }
}