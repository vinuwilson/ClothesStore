package com.example.clothesstore.data.db.repository

import com.example.clothesstore.data.db.ProductDao
import com.example.clothesstore.domain.model.BasketEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class BasketRepository @Inject constructor(
    private val basketDao: ProductDao
) {

    fun getAllItems(): Flow<List<BasketEntity>> = basketDao.getAllItems()
    fun getTotal(): Flow<Double> = basketDao.getTotal()
    suspend fun insertToBasket(product: BasketEntity) = basketDao.insertToBasket(product)
    suspend fun deleteItem(product: BasketEntity) = basketDao.deleteItem(product)
    suspend fun getItemById(productId: String): List<BasketEntity> = basketDao.getItemById(productId)
    suspend fun updateQuantity(productId: String) = basketDao.updateQuantity(productId)
}