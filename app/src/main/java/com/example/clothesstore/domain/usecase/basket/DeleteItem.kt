package com.example.clothesstore.domain.usecase.basket

import com.example.clothesstore.data.db.repository.BasketRepository
import com.example.clothesstore.domain.model.BasketEntity
import javax.inject.Inject

class DeleteItem @Inject constructor(
    private val repository: BasketRepository
) {

    suspend fun deleteItemFromBasket(product: BasketEntity) = repository.deleteItem(product)
}