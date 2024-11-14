package com.example.clothesstore.domain.repository

import com.example.clothesstore.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    suspend fun getProductList(): Flow<Result<List<Product>>>
}