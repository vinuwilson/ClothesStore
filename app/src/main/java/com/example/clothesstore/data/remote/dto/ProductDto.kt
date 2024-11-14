package com.example.clothesstore.data.remote.dto

import com.example.clothesstore.domain.model.Product

data class ProductDto(
    val category: String,
    val image: String,
    val name: String,
    val oldPrice: Double,
    val price: Double,
    val productId: String,
    val stock: Int
)

fun ProductDto.toProduct() = Product(
    category = category,
    image = image,
    name = name,
    oldPrice = oldPrice,
    price = price,
    productId = productId,
    stock = stock
)