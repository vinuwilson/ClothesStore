package com.example.clothesstore.presenter.basket

import com.example.clothesstore.domain.model.BasketEntity

data class BasketState(
    val basket: List<BasketEntity>? = emptyList()
)