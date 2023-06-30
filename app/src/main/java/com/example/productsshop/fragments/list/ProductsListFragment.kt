package com.example.productsshop.fragments.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.productsshop.R
import com.example.productsshop.adapters.ProductsListAdapter
import com.example.productsshop.data.ProductsUiState
import com.example.productsshop.databinding.FragmentProductsListBinding
import com.example.productsshop.fragments.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class ProductsListFragment : BaseFragment() {

    private lateinit var binding: FragmentProductsListBinding
    private lateinit var productsViewModel: ProductsViewModel
    private lateinit var productsAdapter: ProductsListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentProductsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar(binding.toolbar, getString(R.string.app_name))
        setObserve()
    }

    override fun onResume() {
        super.onResume()
        productsViewModel.getProducts()
    }

    private fun setObserve() {
        productsViewModel = ViewModelProvider(this).get(ProductsViewModel::class.java)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                productsViewModel.productsLiveData.collect { uiState ->
                    when(uiState) {
                        is ProductsUiState.Success -> {
                            val products = uiState.products
                            productsAdapter = ProductsListAdapter(products, onItemClick = { selectedProduct ->
                                val action =
                                    ProductsListFragmentDirections.actionProductsListFragmentToProductInfoFragment(
                                        selectedProduct
                                    )
                                findNavController().navigate(action)
                            })
                            binding.recyclerViewProducts.adapter = productsAdapter
                        }
                        is ProductsUiState.Error -> {
                            Toast.makeText(context, "Error: ${uiState.exception}", Toast.LENGTH_LONG).show()
                        }
                        is ProductsUiState.Loading -> {
                            if (uiState.isLoad)
                                showCustomProgressDialog()
                            else
                                hideProgressDialog()
                        }
                    }
                }
            }
        }
    }
}
