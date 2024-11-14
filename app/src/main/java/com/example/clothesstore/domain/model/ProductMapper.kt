package com.example.clothesstore.domain.model

import com.example.clothesstore.data.remote.dto.ProductDto
import com.example.clothesstore.data.remote.dto.toProduct
import javax.inject.Inject

class ProductMapper @Inject constructor() : Function1<List<ProductDto>, List<Product>> {
    override fun invoke(productItem: List<ProductDto>): List<Product> {
        return productItem.map {
            it.toProduct()
        }
    }
}