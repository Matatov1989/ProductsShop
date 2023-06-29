package com.example.productsshop.fragments

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.productsshop.data.ProductsUiState
import com.example.productsshop.repository.ProductsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(private val repository: ProductsRepository) : ViewModel() {

    private val productsUiState = MutableStateFlow<ProductsUiState>(ProductsUiState.Loading(true))
    val productsLiveData: StateFlow<ProductsUiState> get() = productsUiState

    init {
        viewModelScope.launch {
            delay(3000L)
            try {
                val response = repository.getProducts()
                val list = response.body()?.products?.sortedBy { it.discount == 0 }

                list?.let {
                    productsUiState.value = ProductsUiState.Success(it)
                }
            } catch (e: Exception) {
                Log.e("RESULT_EXCEPTION", "result: $e")
                productsUiState.value = ProductsUiState.Error(e)
            } finally {
                productsUiState.value = ProductsUiState.Loading(false)
            }
        }
    }
}