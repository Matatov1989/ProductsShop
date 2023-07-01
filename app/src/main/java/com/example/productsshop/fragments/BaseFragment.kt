package com.example.productsshop.fragments

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.productsshop.R
import com.example.productsshop.fragments.cart.CartViewModel
import com.example.productsshop.fragments.list.ProductsListFragmentDirections
import com.example.productsshop.models.ProductItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
open class BaseFragment : Fragment() {

    lateinit var progressDialog: Dialog
    lateinit var productItem: ProductItem
    lateinit var cartViewModel: CartViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        cartViewModel = ViewModelProvider(this).get(CartViewModel::class.java)

        progressDialog = Dialog(requireContext())

        val callback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                this.isEnabled = false
//                requireActivity().onBackPressedDispatcher.onBackPressed()
                requireActivity().supportFragmentManager.popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)
    }

    fun initToolbar(toolbar: Toolbar, title: String, isBackButton: Boolean = false) {
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        (activity as AppCompatActivity).supportActionBar?.title = title

        if (isBackButton) {
            (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

            toolbar.setNavigationOnClickListener {
                requireActivity().supportFragmentManager.popBackStack()
            }
        }
    }

    fun initMenuToolBar(menuFragment: Int) {
        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(menuFragment, menu)
            }

            @SuppressLint("NonConstantResourceId")
            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                when (menuItem.itemId) {
                    R.id.action_clear_cart -> {
                        cartViewModel.clearCart()
                    }
                    R.id.action_open_cart -> {
                        val action = ProductsListFragmentDirections.actionProductsListFragmentToCartFragment()
                        findNavController().navigate(action)
                    }
                }
                return false
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    fun hideProgressDialog() {
        progressDialog.dismiss()
    }

    fun showCustomProgressDialog() {
        progressDialog.setContentView(R.layout.dialog_custom_progress)
        progressDialog.show()
    }
}
