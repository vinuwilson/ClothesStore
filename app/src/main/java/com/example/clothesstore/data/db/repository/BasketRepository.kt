package com.example.clothesstore.data.db.repository

import com.example.clothesstore.data.db.ProductDao
import com.example.clothesstore.domain.model.BasketEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class BasketRepository @Inject constructor(
    private val basketDao: ProductDao
) {

    fun getAllItems(): Flow<List<BasketEntity>> = basketDao.getAllItems()
    suspend fun insertToBasket(product: BasketEntity) = basketDao.insertToBasket(product)
    suspend fun deleteItem(product: BasketEntity) = basketDao.deleteItem(product)
}