package com.example.clothesstore.domain.usecase.wishlist

import com.example.clothesstore.data.db.repository.WishListRepository
import com.example.clothesstore.domain.model.Product
import javax.inject.Inject

class AddToFavourite @Inject constructor(
    private val repository: WishListRepository
) {

    suspend fun addToFavourite(product: Product) = repository.insertFavourite(product)
}