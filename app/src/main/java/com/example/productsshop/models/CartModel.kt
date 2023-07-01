package com.example.productsshop.models

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CartTable")
data class CartModel(
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "price") var price: Float,
    @ColumnInfo(name = "imageUrl") val imageUrl: String,
    @ColumnInfo(name = "quantity") var quantity: Int,
    @ColumnInfo(name = "color") val color: String
)
