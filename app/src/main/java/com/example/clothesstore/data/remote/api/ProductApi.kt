package com.example.clothesstore.data.remote.api

import com.example.clothesstore.data.remote.dto.ProductListDto
import retrofit2.http.GET

interface ProductApi {

    @GET(".")
    suspend fun getProductList(): ProductListDto
}