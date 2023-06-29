package com.example.productsshop.data

import com.example.productsshop.models.ProductItem
import java.lang.Exception

sealed class ProductsUiState {
    data class Success(val products: List<ProductItem>) : ProductsUiState()
    data class Error(val exception: Exception) : ProductsUiState()
    data class Loading(val isLoad: Boolean) : ProductsUiState()
}
