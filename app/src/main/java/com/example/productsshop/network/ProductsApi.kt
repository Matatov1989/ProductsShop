package com.example.productsshop.network

import com.example.productsshop.models.ProductsResponse
import retrofit2.Response
import retrofit2.http.GET

interface ProductsApi {

    @GET("db")
    suspend fun getProducts(): Response<ProductsResponse>
}