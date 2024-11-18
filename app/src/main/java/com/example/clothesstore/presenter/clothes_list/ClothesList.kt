package com.example.clothesstore.presenter.clothes_list

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.clothesstore.R
import com.example.clothesstore.presenter.clothes_list.components.SingleProductItemView
import com.example.clothesstore.presenter.navigation.ClothesDetailsScreen
import com.example.clothesstore.ui.theme.appColor

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ClothesList(
    navController: NavHostController,
    productList: ProductListState
) {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.catalogue_top_bar),
                        fontWeight = FontWeight.Bold,
                        color = appColor,
                        fontSize = dimensionResource(id = R.dimen.extra_large_font_size).value.sp
                    )
                }
            )
        }
    ) { innerPadding ->
        if (productList.productList != null) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                if (productList.isLoading)
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.onBackground
                    )
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
        } else {
            LaunchedEffect(Unit) {
                Toast.makeText(
                    context,
                    "No internet connection...",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
