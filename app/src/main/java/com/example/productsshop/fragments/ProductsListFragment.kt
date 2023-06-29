package com.example.productsshop.fragments

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.productsshop.R
import com.example.productsshop.adapters.ProductsListAdapter
import com.example.productsshop.data.ProductsUiState
import com.example.productsshop.databinding.FragmentProductsListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class ProductsListFragment : Fragment() {

    private lateinit var binding: FragmentProductsListBinding
    private lateinit var productsViewModel: ProductsViewModel
    private lateinit var productsAdapter: ProductsListAdapter
    private lateinit var progressDialog: Dialog

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentProductsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObserve(view)
    }

    private fun setObserve(view: View) {
        productsViewModel = ViewModelProvider(this).get(ProductsViewModel::class.java)

        lifecycleScope.launch {
            productsViewModel.productsLiveData.collect { uiState ->
                when(uiState) {
                    is ProductsUiState.Success -> {
                        val products = uiState.products
                        productsAdapter = ProductsListAdapter(products, onItemClick = { selectedProduct ->
                            Log.d("Click", "${selectedProduct}")

                            val action = ProductsListFragmentDirections.actionProductsListFragmentToProductInfoFragment(selectedProduct)
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

    private fun hideProgressDialog() {
        progressDialog.dismiss()
    }

    private fun showCustomProgressDialog() {
        progressDialog = Dialog(requireContext())
        progressDialog.setContentView(R.layout.dialog_custom_progress)
        progressDialog.show()
    }
}
