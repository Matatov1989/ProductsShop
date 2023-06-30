package com.example.productsshop.fragments

import android.app.Dialog
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.example.productsshop.R
import com.example.productsshop.models.ProductItem

open class BaseFragment : Fragment() {

    lateinit var progressDialog: Dialog
    lateinit var productItem: ProductItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        progressDialog = Dialog(requireContext())

        val callback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                this.isEnabled = false
//                requireActivity().onBackPressedDispatcher.onBackPressed()
                requireActivity().supportFragmentManager.popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)

        arguments?.let {
            productItem = ProductInfoFragmentArgs.fromBundle(it).productItem
        }
    }

    fun hideProgressDialog() {
        progressDialog.dismiss()
    }

    fun showCustomProgressDialog() {
        progressDialog.setContentView(R.layout.dialog_custom_progress)
        progressDialog.show()
    }
}
