package com.example.clothesstore.domain.usecase

import com.example.clothesstore.domain.model.Product
import com.example.clothesstore.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductList @Inject constructor(
    private val repository: ProductRepository
) {

    suspend fun getAllProductList(): Flow<Result<List<Product>>> {
        return repository.getProductList()
    }
}
