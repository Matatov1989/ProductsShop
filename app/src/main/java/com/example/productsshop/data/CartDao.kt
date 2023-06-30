package com.example.productsshop.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.productsshop.models.CartModel

@Dao
interface CartDao {

    @Query("SELECT * FROM CartTable")
    suspend fun getProducts() : List<CartModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: CartModel)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateProduct(product: CartModel)

    @Delete
    suspend fun deleteProduct(product: CartModel)

    @Query("DELETE FROM CartTable")
    suspend fun deleteAllProducts()

    @Query("SELECT SUM(price) FROM CartTable")
    suspend fun calculateTotalSum(): Float
}
