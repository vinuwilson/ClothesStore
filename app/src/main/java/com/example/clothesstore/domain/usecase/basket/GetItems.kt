package com.example.clothesstore.domain.usecase.basket

import com.example.clothesstore.data.db.repository.BasketRepository
import javax.inject.Inject

class GetItems @Inject constructor(
    private val repository: BasketRepository
) {

    fun getAllItemsFromBasket() = repository.getAllItems()

    suspend fun getItemById(productId: String) = repository.getItemById(productId)
}