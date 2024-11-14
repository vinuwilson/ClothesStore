package com.example.clothesstore.presenter.clothes_list.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.clothesstore.domain.model.Product

@Composable
fun ProductListItemView(
    product: Product
) {
    Text(text = product.name)
}