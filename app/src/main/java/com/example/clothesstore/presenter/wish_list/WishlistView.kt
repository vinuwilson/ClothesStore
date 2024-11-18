package com.example.clothesstore.presenter.wish_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.clothesstore.R
import com.example.clothesstore.domain.model.Product
import com.example.clothesstore.domain.model.toBasketEntity
import com.example.clothesstore.presenter.basket.BasketViewModel
import com.example.clothesstore.presenter.wish_list.components.WishlistItem
import com.example.clothesstore.ui.theme.appColor
import com.example.clothesstore.utils.SwipeToDeleteContainer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WishListView(
    wishlistState: List<Product>,
    wishListViewModel: WishListViewModel = hiltViewModel(),
    basketViewModel: BasketViewModel = hiltViewModel()
) {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.wish_list_title),
                        fontWeight = FontWeight.Bold,
                        color = appColor,
                        fontSize = dimensionResource(id = R.dimen.extra_large_font_size).value.sp
                    )
                }
            )
        }
    ) { innerPadding ->
        if (wishlistState.isNotEmpty() == true) {
            LazyColumn(
                modifier = Modifier.padding(innerPadding)
            ) {
                items(
                    items = wishlistState,
                    key = { it.productId }
                ) { wishlistItem ->
                    SwipeToDeleteContainer(
                        item = wishlistItem,
                        onDelete = { product ->
                            wishListViewModel.removeFromFavourite(product)
                        }
                    ) {
                        WishlistItem(
                            wishlistItem = wishlistItem
                        ) { product ->
                            wishListViewModel.removeFromFavourite(product)
                            basketViewModel.insertOrUpdate(product.toBasketEntity())
                        }
                    }
                }
            }
        } else {
            Box(
                modifier = Modifier.padding(innerPadding)
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = dimensionResource(R.dimen.message_padding_top)),
                    textAlign = TextAlign.Center,
                    color = appColor,
                    fontStyle = FontStyle.Italic,
                    text = stringResource(R.string.empty_wishlist_message)
                )
            }
        }
    }
}