package com.example.clothesstore.domain.usecase.basket

import com.example.clothesstore.data.db.repository.BasketRepository
import javax.inject.Inject

class GetAllItems @Inject constructor(
    private val repository: BasketRepository
) {

    suspend fun getAllItemsFromBasket() = repository.getAllItems()
}