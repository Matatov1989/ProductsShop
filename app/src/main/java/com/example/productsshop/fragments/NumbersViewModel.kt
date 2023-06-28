package com.example.productsshop.fragments

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.productsshop.repository.ProductsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class NumbersViewModel @Inject constructor(private val repository: ProductsRepository) : ViewModel() {

    init {
        viewModelScope.launch {
            try {
                val result = repository.getProducts()
            } catch (e: Exception) {
                Log.e("RESULT_EXCEPTION", "result: $e")
            }
        }
    }
}