package com.example.clothesstore.domain.usecase.wishlist

import com.example.clothesstore.data.db.repository.WishListRepository
import com.example.clothesstore.domain.model.Product
import javax.inject.Inject

class DeleteFavourite @Inject constructor(
    private val repository: WishListRepository
) {

    suspend fun removeFromFavourite(product: Product) = repository.deleteFavourite(product)
}