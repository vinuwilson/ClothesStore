package com.example.clothesstore.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "basket_tbl")
data class BasketEntity(
    val category: String,
    val image: String,
    val name: String,
    val oldPrice: Double,
    val price: Double,
    @PrimaryKey(autoGenerate = false)
    val productId: String,
    val stock: Int,
    val isFavourite: Boolean = false,
    val quantity: Int = 1
)

fun Product.toBasketEntity() = BasketEntity(
    category = category,
    image = image,
    name = name,
    oldPrice = oldPrice,
    price = price,
    productId = productId,
    stock = stock,
    isFavourite = isFavourite
)