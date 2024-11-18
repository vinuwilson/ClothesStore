package com.example.clothesstore.presenter.clothes_details.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.clothesstore.R
import com.example.clothesstore.domain.model.Product
import com.example.clothesstore.domain.model.toBasketEntity
import com.example.clothesstore.presenter.basket.BasketViewModel
import com.example.clothesstore.presenter.wish_list.WishListViewModel
import com.example.clothesstore.ui.theme.appColor

@Composable
fun DetailsBottomView(
    productDetails: Product,
    wishListViewModel: WishListViewModel? = hiltViewModel(),
    basketViewModel: BasketViewModel? = hiltViewModel()
) {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.Bottom
    ) {
        Button(
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.onPrimary),
            modifier = Modifier
                .padding(dimensionResource(R.dimen.app_padding))
                .weight(1f),
            onClick = {
                wishListViewModel?.addToFavourite(productDetails)
                Toast.makeText(
                    context,
                    context.getString(R.string.added_to_wishlist),
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
        ) {
            Text(
                text = stringResource(R.string.wish_list_btn),
                color =  MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(dimensionResource(R.dimen.button_text_padding))
            )
        }

        Button(
            colors = ButtonDefaults.buttonColors(appColor),
            modifier = Modifier
                .padding(dimensionResource(R.dimen.app_padding))
                .weight(1f),
            onClick = {
                basketViewModel?.insertOrUpdate(productDetails.toBasketEntity())
                Toast.makeText(
                    context,
                    context.getString(R.string.added_to_basket),
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
        ) {
            Text(
                modifier = Modifier.padding(dimensionResource(R.dimen.button_text_padding)),
                text = stringResource(R.string.add_to_cart_btn)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailsBottomViewPreview() {
    DetailsBottomView(
        productDetails = Product(
            category = "Tops",
            name = "Blue Shirt",
            image = "https://firebasestorage.googleapis.com/v0/b/techtest-1f1da.appspot.com/o/blue.jpg?alt=media\u0026token=5be5eadd-c006-4bb9-83af-d6afc496475a",
            price = 7.99,
            stock = 3,
            oldPrice = 8.99,
            productId = "1"
        ),
        wishListViewModel = null,
        basketViewModel = null
    )
}