package com.example.clothesstore.domain.model

data class Product(
    val category: String,
    val image: String,
    val name: String,
    val oldPrice: Double,
    val price: Double,
    val productId: String,
    val stock: Int,
    val isFavourite: Boolean = false
)