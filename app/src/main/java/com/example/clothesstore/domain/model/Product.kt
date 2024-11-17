package com.example.clothesstore.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wishlist_tbl")
data class Product(
    val category: String,
    val image: String,
    val name: String,
    val oldPrice: Double,
    val price: Double,
    @PrimaryKey(autoGenerate = false)
    val productId: String,
    val stock: Int,
    val isFavourite: Boolean = false
)