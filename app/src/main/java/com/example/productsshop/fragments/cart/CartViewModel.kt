package com.example.productsshop.fragments.cart

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.productsshop.models.CartModel
import com.example.productsshop.repository.CartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(private val repository: CartRepository) : ViewModel() {

    val cartProductsLiveData = MutableLiveData<List<CartModel>>()
    val totalSum = MutableLiveData<Float>()

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
        calculateTotalSum()
    }

    fun updateItemToCart(product: CartModel) = viewModelScope.launch {
        repository.updateProduct(product)
        calculateTotalSum()
    }

    fun clearCart() = viewModelScope.launch {
        repository.clearCart()
        cartProductsLiveData.value = emptyList()
        totalSum.postValue(0.0f)
    }

    fun calculateTotalSum() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.calculateTotalSum() ?: 0.0F
            totalSum.postValue(result)
        }
    }
}
