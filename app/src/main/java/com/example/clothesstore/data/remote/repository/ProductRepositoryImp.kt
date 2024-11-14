package com.example.clothesstore.data.remote.repository

import com.example.clothesstore.domain.model.Product
import com.example.clothesstore.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductRepositoryImp @Inject constructor(
    private val service: RemoteProductListService
) : ProductRepository {

    override suspend fun getProductList(): Flow<Result<List<Product>>> {
        return service.getProductList()
    }
}