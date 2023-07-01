package com.example.productsshop.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.productsshop.models.CartModel

@Database(entities = [CartModel::class], version = 2, exportSchema = false)
abstract class CartDatabase : RoomDatabase() {

    abstract fun cartDao(): CartDao
}
