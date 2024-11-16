package com.example.clothesstore.presenter.navigation

import kotlinx.serialization.Serializable

@Serializable
object ClothesInfo

@Serializable
object CatalogueScreen

@Serializable
object WishlistScreen

@Serializable
object BasketScreen

@Serializable
data class ClothesDetailsScreen(
    val productId : String
)