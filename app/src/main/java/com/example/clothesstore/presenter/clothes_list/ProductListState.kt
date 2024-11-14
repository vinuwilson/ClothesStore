package com.example.clothesstore.presenter.clothes_list

import com.example.clothesstore.domain.model.Product

data class ProductListState(
    val isLoading: Boolean = false,
    val productList: List<Product>? = emptyList()
)