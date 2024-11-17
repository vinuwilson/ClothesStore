package com.example.clothesstore.presenter.wish_list

import com.example.clothesstore.domain.model.Product

data class WishlistState(
    val wishlist: List<Product>? = emptyList()
)