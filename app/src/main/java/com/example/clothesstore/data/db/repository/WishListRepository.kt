package com.example.clothesstore.data.db.repository

import com.example.clothesstore.data.db.ProductDao
import com.example.clothesstore.domain.model.Product
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WishListRepository @Inject constructor(
    private val wishListDao: ProductDao
) {

    fun getFavourite(): Flow<List<Product>> = wishListDao.getFavourites()
    suspend fun insertFavourite(product: Product) = wishListDao.insertFavourite(product)
    suspend fun deleteFavourite(product: Product) = wishListDao.deleteFavourite(product)

}