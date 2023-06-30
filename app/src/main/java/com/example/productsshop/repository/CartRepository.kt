package com.example.productsshop.repository

import com.example.productsshop.data.CartDao
import com.example.productsshop.models.CartModel
import javax.inject.Inject

class CartRepository @Inject constructor(private val cartDao: CartDao) {

    suspend fun getProducts() = cartDao.getProducts()

    suspend fun insertProduct(product: CartModel) = cartDao.insertProduct(product)

    suspend fun updateProduct(product: CartModel) = cartDao.updateProduct(product)

    suspend fun deleteProduct(product: CartModel) = cartDao.deleteProduct(product)

    suspend fun clearCart() = cartDao.deleteAllProducts()

    suspend fun calculateTotalSum() = cartDao.calculateTotalSum()
}
