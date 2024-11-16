package com.example.clothesstore.presenter.clothes_list

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.clothesstore.R
import com.example.clothesstore.presenter.clothes_list.components.SingleProductItemView
import com.example.clothesstore.presenter.navigation.ClothesDetailsScreen

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ClothesList(
    navController: NavHostController,
    productList: ProductListState
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.catalogue_top_bar),
                        fontWeight = FontWeight.Bold,
                        color = Color.Red,
                        fontSize = dimensionResource(id = R.dimen.large_font_size).value.sp
                    )
                }
            )
        }
    ) { innerPadding ->
        if (productList.productList != null) {
            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                columns = GridCells.Fixed(2)
            ) {
                items(productList.productList) { product ->
                    SingleProductItemView(product) { productId ->
                        navController.navigate(
                            ClothesDetailsScreen(
                                productId = productId
                            )
                        )
                    }
                }
            }
        }
    }
}
