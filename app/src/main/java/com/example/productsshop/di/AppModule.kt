package com.example.productsshop.di

import android.content.Context
import androidx.room.Room
import com.example.productsshop.data.CartDao
import com.example.productsshop.data.CartDatabase
import com.example.productsshop.network.ProductsApi
import com.example.productsshop.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideProductsApi(): ProductsApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProductsApi::class.java)
    }

    @Singleton
    @Provides
    fun provideCartDao(cartDatabase: CartDatabase): CartDao =
        cartDatabase.cartDao()

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): CartDatabase =
        Room.databaseBuilder(
            context,
            CartDatabase::class.java,
            "WeatherDatabase"
        ).fallbackToDestructiveMigration().build()
}
