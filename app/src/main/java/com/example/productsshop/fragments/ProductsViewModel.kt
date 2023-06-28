package com.example.productsshop.fragments

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.productsshop.models.ProductItem
import com.example.productsshop.repository.ProductsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(private val repository: ProductsRepository) : ViewModel() {

    val productsLiveData = MutableLiveData<List<ProductItem>>()

    init {
        viewModelScope.launch {
            try {
                val result = repository.getProducts()
                productsLiveData.value = result.body()?.products
            } catch (e: Exception) {
                Log.e("RESULT_EXCEPTION", "result: $e")
            }
        }
    }
}