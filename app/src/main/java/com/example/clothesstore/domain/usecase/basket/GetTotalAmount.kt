package com.example.clothesstore.domain.usecase.basket

import com.example.clothesstore.data.db.repository.BasketRepository
import javax.inject.Inject

class GetTotalAmount @Inject constructor(
    private val repository: BasketRepository
) {

    fun getTotal() = repository.getTotal()

}