package com.example.productsshop.models

data class ProductItem(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val shortDescription: String,
    val longDescription: String,
    val price: Float,
    val discount: Int,
    val rating: Int,
    val quantity: Int,
    val color: List<ProductColor>
)
