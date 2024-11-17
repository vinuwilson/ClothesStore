package com.example.clothesstore.domain.usecase.basket

import com.example.clothesstore.data.db.repository.BasketRepository
import com.example.clothesstore.domain.model.BasketEntity
import javax.inject.Inject

class AddToBasket @Inject constructor(
    private val repository: BasketRepository
) {

    suspend fun addToBasket(product: BasketEntity) = repository.insertToBasket(product)
}