package com.example.clothesstore.presenter.clothes_list

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.clothesstore.presenter.clothes_list.components.SingleProductItemView

@Composable
fun ClothesList(
    navController: NavHostController,
    productList: ProductListState
) {

    if (productList.productList != null) {
        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize(),
            columns = GridCells.Fixed(2)
        ) {
            items(productList.productList) { product ->
                SingleProductItemView(product)
            }
        }
    }
}
