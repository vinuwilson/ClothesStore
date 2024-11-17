package com.example.clothesstore.domain.usecase.wishlist

import com.example.clothesstore.data.db.repository.WishListRepository
import com.example.clothesstore.domain.model.Product
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavourites @Inject constructor(
    private val repository: WishListRepository
) {

    suspend fun getFavourites(): Flow<List<Product>> = repository.getFavourite()
}