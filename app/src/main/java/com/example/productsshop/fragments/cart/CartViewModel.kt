package com.example.productsshop.fragments.cart

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.productsshop.models.CartModel
import com.example.productsshop.repository.CartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(private val repository: CartRepository) : ViewModel() {

    val cartProductsLiveData = MutableLiveData<List<CartModel>>()

    fun fetchProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = repository.getProducts()
                cartProductsLiveData.postValue(result)
            } catch (e: Exception) {
                Log.e("ERROR", "${e.message}")
            }
        }
    }

    fun addProductToCart(product: CartModel) = viewModelScope.launch {
        repository.insertProduct(product)
    }

}
