package com.example.productsshop.fragments.cart

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.productsshop.models.CartModel
import com.example.productsshop.repository.CartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(private val repository: CartRepository) : ViewModel() {

    val cartProductsLiveData = MutableLiveData<List<CartModel>>()

    private val cartProducts: Flow<List<CartModel>> = flow {
        try {
            repository.getProducts().distinctUntilChanged().collect {
                if (it.isNotEmpty())
                    emit(it)
            }
        } catch (error: Throwable) {
            throw error
        }
    }.flowOn(Dispatchers.IO)


    fun fetchProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            cartProducts.collect {
                cartProductsLiveData.postValue(it)
            }
        }
    }

    fun addProductToCart(product: CartModel) = viewModelScope.launch {
        repository.insertProduct(product)
    }

    fun removeProductFromCart(product: CartModel) = viewModelScope.launch {
        repository.deleteProduct(product)
    }

    fun clearCart() = viewModelScope.launch {
        repository.clearCart()
        cartProductsLiveData.value = emptyList()
    }
}
