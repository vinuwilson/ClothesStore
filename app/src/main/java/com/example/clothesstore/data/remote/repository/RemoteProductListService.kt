package com.example.clothesstore.data.remote.repository

import com.example.clothesstore.data.remote.api.ProductApi
import com.example.clothesstore.data.remote.dto.ProductDto
import com.example.clothesstore.data.remote.dto.ProductListDto
import com.example.clothesstore.domain.model.Product
import com.example.clothesstore.domain.model.ProductMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteProductListService @Inject constructor(
    private val api: ProductApi,
    private val mapper: ProductMapper
) {

    suspend fun getProductList(): Flow<Result<List<Product>>> {
        return flow {
            emit(Result.success(mapper.invoke(api.getProductList().products)))
        }.catch {
            emit(Result.failure(RuntimeException("Something went wrong")))
        }
    }
}