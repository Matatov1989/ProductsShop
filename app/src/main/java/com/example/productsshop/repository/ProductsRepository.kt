package com.example.productsshop.repository

import com.example.productsshop.models.ProductsResponse
import com.example.productsshop.network.ProductsApi
import retrofit2.Response
import javax.inject.Inject

class ProductsRepository @Inject constructor(private val api: ProductsApi) {

    suspend fun getProducts(): Response<ProductsResponse> {
        return api.getProducts()
    }
}
